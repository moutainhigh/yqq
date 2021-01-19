package com.yqq.framework.web.result;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Result : 响应的结果对象
 *
 * @author StarZou
 * @since 2014-09-27 16:28
 */
@ApiModel(value="统一返回")
public class Result implements Serializable {

    public static final String SUCCESS = "1";

    public static final String FAIL = "0";

    public static final String ERROR = "2";

    public static final String UNAUTHORIZED = "-1";

    private static final long serialVersionUID = 6288374846131788743L;

    /**
     * 信息
     */
    @ApiModelProperty(value = "信息")
    private String message;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private String code;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private boolean success;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result() {
        this.success = true;
        this.code = SUCCESS;
        this.message = "操作成功";
    }
}
