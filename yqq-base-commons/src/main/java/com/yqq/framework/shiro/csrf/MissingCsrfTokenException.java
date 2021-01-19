package com.yqq.framework.shiro.csrf;

/**
 * Thrown when no expected {@link CsrfToken} is found but is required.
 *
 * @author Rob Winch
 * @since 3.2
 */
@SuppressWarnings("serial")
public class MissingCsrfTokenException extends CsrfException {

    public MissingCsrfTokenException() {
        super("Could not verify the provided CSRF token because your session was not found.");
    }
}