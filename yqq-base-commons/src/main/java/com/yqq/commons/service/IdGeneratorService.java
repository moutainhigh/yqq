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
package com.yqq.commons.service;

import java.util.List;

/**
 * <功能描述>
 * 
 * @author  yangchuan
 * @see  [相关类/方法]
 */
public interface IdGeneratorService {
    
    /**
     * 
     * 获取java uuid 去除 减号 
     * @return
     */
    public String getUUID();
    
    /**
     * 
     * 获取java uuid 去除 减号 
     * @return
     */
    public List<String> getUUIDs(int number);
    
    /**
     * 
     * 根据 seq 类型编码获取编码
     * @param seqType 使用    {@link com.yqq.goods.enums.BizSeqTypeEnum BizSeqTypeEnum} 枚举中类型编码<p>
     * 如 获取 品牌编码 则在业务调用时通过<blockquote><pre>  BizSeqTypeEnum.BRAND_NUM.toString() </pre></blockquote><p> 获取编码传入
     * @param minLength 最小长度 如从 1 开始 <p> 举例： 传入参数值为 3 时不足3则左补0，  则 返回编码为：   业务编码前缀 +  001
     * @return string 类型 纯数字编码  业务编码 + 序列
     */
    public String getBizNextSeq(String seqType, int minLength);

    /**
     * 
     * 根据 seq 类型编码获取编码 
     * @param seqType 使用    {@link com.yqq.goods.enums.BizSeqTypeEnum BizSeqTypeEnum} 枚举中类型编码<p>
     * 如 获取 品牌编码 则在业务调用时通过<blockquote><pre>  BizSeqTypeEnum.BRAND_NUM.toString() </pre></blockquote><p> 获取编码传入
     * @param minLength 最小长度 如从 1 开始  <p>举例 ：传入参数值为 3 时不足3则左补0，  则 返回编码为：   业务编码前缀 +  001
     * @param number 返回id个数
     * 
     * @return list string 类型 纯数字编码  
     */
    public List<String> getBizNextSeqs(String seqType, int minLength, int number);
    
    /**
     * 
     * 根据 seq 类型编码获取编码 
     * @param billSeqType 使用    {@link com.yqq.goods.enums.BillSeqTypeEnum BillSeqTypeEnum} 枚举中类型编码<p>
     * 如 获取 品牌编码 则在业务调用时通过<blockquote><pre>  BillSeqTypeEnum.PUBLIC_NUM.toString() </pre></blockquote><p> 获取编码传入
     * <p> billSeqType 若不传则默认使用  SEQ_PUBLIC_NUM 
     * 若使用DB模式 则需要保证创建的 seq 必须为 4位 可循环
     * @return seqType.split("_")[0] + YYYYMMDDHH24MISS +  4位seq
     */
    public String getBillNumSeq(String billSeqType);
    
    /**
     * 
     * 日志ID获取
     * @return  YYYYMMDDHH24MISS +  6位seq
     */
    public String getLogNumSeq();
    
    /**
     * 
     * 订单号获取
     * @param userId 订单下单人ID
     * @return 
     */
    public String getOrderId(String userId);
}
