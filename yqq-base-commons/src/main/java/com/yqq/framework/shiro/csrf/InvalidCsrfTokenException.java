package com.yqq.framework.shiro.csrf;

/**
 * Thrown when an expected {@link CsrfToken} exists, but it does not match the value
 * present on the {@link javax.servlet.http.HttpServletRequest HttpServletRequest}
 *
 * @author Rob Winch
 * @since 3.2
 */
@SuppressWarnings("serial")
public class InvalidCsrfTokenException extends com.yqq.framework.shiro.csrf.CsrfException {


    public InvalidCsrfTokenException(String message) {
        super(message);
    }

    public InvalidCsrfTokenException(String message, Throwable t) {
        super(message, t);
    }

    /**
     * @param expectedAccessToken
     * @param actualAccessToken
     */
    public InvalidCsrfTokenException(com.yqq.framework.shiro.csrf.CsrfToken expectedAccessToken,
                                     String actualAccessToken) {
        super("Invalid CSRF Token '" + actualAccessToken
                + "' was found on the request parameter '"
                + expectedAccessToken.getParameterName() + "' or header '"
                + expectedAccessToken.getHeaderName() + "'.");
    }
}