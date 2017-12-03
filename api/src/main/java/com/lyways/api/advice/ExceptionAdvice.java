package com.lyways.api.advice;

import com.lyways.api.response.ResponseModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author austin
 * @createDate 2017/11/28.
 */
@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseModel handleException(Exception ex){
        logger.error(ex.getMessage(), ex);
        return new ResponseModel().fail().message(ex.getMessage());
    }
}
