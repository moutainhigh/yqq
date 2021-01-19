package com.yqq.framework.shiro.csrf;

import org.apache.shiro.ShiroException;

/**
 * Created by zhangq on 4/11/18.
 */
public class CsrfException extends ShiroException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8153438856806978682L;

    public CsrfException(String message) {
        super(message);
    }

    public CsrfException(String message, Throwable t) {
        super(message, t);
    }
}