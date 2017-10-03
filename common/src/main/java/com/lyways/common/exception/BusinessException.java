package com.lyways.common.exception;

/**
 * Created by austin on 9/25/17.
 */
public class BusinessException extends BaseException {
    public BusinessException(){
    }

    public BusinessException(String message) {
        super(message);
    }
    public BusinessException(Throwable e) {
        super(e);
    }
    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}
