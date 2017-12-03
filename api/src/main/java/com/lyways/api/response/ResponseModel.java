package com.lyways.api.response;

/**
 * @author: austin
 * @createDate: 10/25/17
 */
public class ResponseModel<T> {

    private Integer status;

    private String message;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseModel status(Integer status){
        this.status = status;
        return this;
    }

    public ResponseModel message(String message){
        this.message = message;
        return this;
    }

    public ResponseModel data(T data){
        this.data = data;
        return this;
    }

    public ResponseModel success(){
        return new ResponseModel().status(0).message("ok");
    }

    public ResponseModel fail(){
        return new ResponseModel().status(-1).message("fail");
    }
}
