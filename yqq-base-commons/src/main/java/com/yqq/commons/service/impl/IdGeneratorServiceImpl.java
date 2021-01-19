/*
 * 文 件 名:  IdGeneratorService.java
 * 版    权:  Nanjing Xinwang Tech Co.,Ltd.Copyright 2013-2018,All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  yangchuan
 * 修改时间:  2018年8月8日
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.yqq.commons.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yqq.commons.enums.BillSeqTypeEnum;
import com.yqq.commons.enums.BizSeqTypeEnum;
import com.yqq.commons.service.BaseService;
import com.yqq.commons.service.IdGeneratorService;
import com.yqq.framework.utils.DateTimeUtils;

/**
 * id 生成实现
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
@Service("adminIdGeneratorService")
public class IdGeneratorServiceImpl extends BaseService implements IdGeneratorService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 系统配置文件参数配置 application.yml中
     */
    @Value("${xw.idtype}")
    private String idType;
    
    private static final LocalDate SYS_START = new LocalDate(2018, 8, 1);

    @Override
    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public List<String> getUUIDs(int number) {
        if (number < 1) {
            return null;
        }
        List<String> uuids = new ArrayList<String>();
        for (int i = 0; i < number; i++) {
            uuids.add(getUUID());
        }
        return uuids;
    }
    
    @Override
    public String getBizNextSeq(String seqType, int minLength) {
        switch (idType) {
            case "oracle":
                return getBizDBSeq(seqType, minLength);
            case "redis":
                return getBizRedisSeq(seqType, minLength);
            default:
                return getBizRedisSeq(seqType, minLength);
        }
    }

    /**
     * 
     * 业务档案 seq Oracle获取方式
     * @param seqType 业务 seq 编码
     * @return
     */
    private synchronized String getBizDBSeq(String seqType, int minLength) {
        String format = "%0" + minLength + "d";
        String enumVal = BizSeqTypeEnum.valueOf(BizSeqTypeEnum.class, seqType).geNumVal();
        Integer dbseq = jdbcTemplate.queryForObject("select SEQ_" + seqType + ".NEXTVAL from dual", Integer.class);
        return enumVal + String.format(format, dbseq);
    }

    
    /**
     * 
     * 业务档案 seq redis 获取方式
     * @param seqType 业务 seq 编码
     * @return
     */
    private synchronized String getBizRedisSeq(String seqType, int minLength) {
        String enumVal = BizSeqTypeEnum.valueOf(BizSeqTypeEnum.class, seqType).geNumVal();
        String format = "%0" + minLength + "d";
        String str = enumVal + String.format(format, this.generate(seqType));
        return str;
    }
    
    @Override
    public List<String> getBizNextSeqs(String seqType, int minLength, int number) {
        if (number < 1) {
            return null;
        }
        switch (idType) {
            case "oracle":
                return getBizDBSeqs(seqType, minLength, number);
            case "redis":
                return getBizRedisSeqs(seqType, minLength, number);
            default:
                return getBizRedisSeqs(seqType, minLength, number);
        }
    }
    
    /**
     * 
     * 业务档案 seq Oracle 批量获取方式
     * @param seqType 业务 seq 编码
     * @return
     */
    private synchronized List<String> getBizDBSeqs(String seqType, int minLength, int number) {
        String format = "%0" + minLength + "d";
        List<String> retIds = new ArrayList<>();
        String enumVal = BizSeqTypeEnum.valueOf(BizSeqTypeEnum.class, seqType).geNumVal();
        List<Integer> ids = jdbcTemplate.queryForList(
                "select SEQ_" + seqType + ".NEXTVAL from (select 1 from all_objects where rownum <= " + number + ")",
                Integer.class);
        ids.forEach(item -> retIds.add(enumVal +String.format(format, item)));
        return retIds;
    }

    /**
     * 
     * 业务档案 seq redis 批量获取方式
     * @param seqType 业务 seq 编码
     * @return
     */
    private synchronized List<String> getBizRedisSeqs(String seqType, int minLength, int number) {
        String enumVal = BizSeqTypeEnum.valueOf(BizSeqTypeEnum.class, seqType).geNumVal();
        String format = "%0" + minLength + "d";
        List<String> uuids = new ArrayList<String>();
        for (int i = 0; i < number; i++) {
            String str = enumVal + String.format(format, this.generate(seqType));
            uuids.add(str);
        }
        return uuids;
    }

    @Override
    public String getBillNumSeq(String billSeqType) {
        switch (idType) {
            case "oracle":
                return getBillNumDBSeq(billSeqType);
            case "redis":
                return getBillNumRedisSeq(billSeqType);
            default:
                return getBillNumRedisSeq(billSeqType);
        }
    }

    /**
     * 从db获取单据号序列
     */
    private synchronized String getBillNumDBSeq(String billSeqType) {
        if(StringUtils.isBlank(billSeqType)) {
            billSeqType = BillSeqTypeEnum.PUBLIC_NUM.toString();
        }
        String dbseq = jdbcTemplate.queryForObject("select to_char(sysdate,'YYYYMMDDHH24MISS') || LPAD(SEQ_" + billSeqType + ".NEXTVAL, 4 , '0') from dual", String.class);
        return billSeqType.split("_")[0] + dbseq;
    }
    /**
     * 
     * 从redis获取单据序列
     * @param seqType
     * @return
     */
    private synchronized String getBillNumRedisSeq(String billSeqType) {
        if(StringUtils.isBlank(billSeqType)) {
            billSeqType = BillSeqTypeEnum.PUBLIC_NUM.toString();
        }
        String seq = billSeqType.split("_")[0] + DateTimeUtils.getTodayChar14() + String.valueOf(String.format("%04d",this.generateCycle(billSeqType, 4)));
        return seq;
    }

    @Override
    public String getLogNumSeq() {
        switch (idType) {
            case "oracle":
                return getLogNumDBSeq();
            case "redis":
                return getLogNumRedisSeq();
            default:
                return getLogNumRedisSeq();
        }
    }

    /**
     * 从db获取日志号序列
     */
    private synchronized String getLogNumDBSeq() {
        String dbseq = jdbcTemplate.queryForObject("select to_char(sysdate,'YYYYMMDDHH24MISS') || LPAD(SEQ_LOG_NUM.NEXTVAL, 6 , '0') from dual", String.class);
        return dbseq;
    }
    /**
     * 
     * 从redis获取日志序列
     * @return
     */
    private synchronized String getLogNumRedisSeq() {
        String seq =  DateTimeUtils.getTodayChar14() + String.valueOf(String.format("%06d",this.generateCycle("LOG_NUM", 6)));
        return seq;
    }

    @Override
    public String getOrderId(String userId) {
        LocalDate end = new LocalDate();
        int days = Days.daysBetween(SYS_START, end).getDays();
        String suserId = userId.substring(userId.length() - 4, userId.length());
        String id = days + String.format("%05d",this.generate(suserId)) + suserId;
        return id;
    }

    private Long generate(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }

    private Long generateCycle(String key, int maxLength) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long cur = counter.get();
        long max = getCompareAndSetVal(maxLength);
        if(cur == max) {
           counter.set(1);
           return 1L;
        }
        return counter.incrementAndGet();
    }

    /**
     * 
     * 根据 用户请求序列限制长度大小 获取最大循环值
     * 当超过18位时则返回long 最大值
     * @param size 长度 最大不超过 18 超过 18返回 long最大值
     * @return 限长最大值
     */
    private long getCompareAndSetVal(int size) {
        int maxLength = 18;
        if (size < maxLength) {
            String format = "%0" + size + "d";
            return Long.valueOf(String.format(format, 9).replace("0", "9"));
        }
        return Long.MAX_VALUE;
    }
    
}
