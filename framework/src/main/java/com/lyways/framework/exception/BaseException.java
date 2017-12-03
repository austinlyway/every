package com.lyways.framework.exception;

/**
 * Created by austin on 9/25/17.
 */
public class BaseException extends RuntimeException {
    public BaseException(){
    }

    public BaseException(String message) {
        super(message);
    }
    public BaseException(Throwable e) {
        super(e);
    }
    public BaseException(String message, Throwable e) {
        super(message, e);
    }
}
