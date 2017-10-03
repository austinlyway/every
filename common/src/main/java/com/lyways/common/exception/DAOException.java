package com.lyways.common.exception;

/**
 * Created by austin on 9/25/17.
 */
public class DAOException extends BaseException {

    public DAOException(){
    }

    public DAOException(String message) {
        super(message);
    }
    public DAOException(Throwable e) {
        super(e);
    }
    public DAOException(String message, Throwable e) {
        super(message, e);
    }
}
