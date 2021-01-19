/*
 * 文 件 名:  KryoRedisSerializer.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2017年10月13日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.framework.utils;

import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * kryo 序列化工具
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public class KryoRedisSerializer implements RedisSerializer<Object>{
    private static Logger logger = LoggerFactory.getLogger(KryoRedisSerializer.class);

    /**
     * 
     * 序列化
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return null;
        }
        Kryo kryo = KRYOS.get();
        Output output = new Output(64, -1);
        try {
            kryo.writeClassAndObject(output, t);
            return output.toBytes();
        }
        finally {
            closeOutputStream(output);
        }
    }

    /**
     * 
     * 反序列化
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        Kryo kryo = KRYOS.get();
        Input input = null;
        try {
            input = new Input(bytes);
            return kryo.readClassAndObject(input);
        }
        finally {
            closeInputStream(input);
        }
    }

    private static void closeOutputStream(OutputStream output) {
        if (output != null) {
            try {
                output.flush();
                output.close();
            }
            catch (Exception e) {
                logger.error("serialize object close outputStream exception", e);
            }
        }
    }

    private static void closeInputStream(InputStream input) {
        if (input != null) {
            try {
                input.close();
            }
            catch (Exception e) {
                logger.error("serialize object close inputStream exception", e);
            }
        }
    }

    private static final ThreadLocal<Kryo> KRYOS = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();
            return kryo;
        }
    };
}
