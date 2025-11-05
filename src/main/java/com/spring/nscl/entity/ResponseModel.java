package com.spring.nscl.entity;

import com.spring.nscl.common.HttpStatusConstants;
import com.spring.nscl.common.NscMessageConstants;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseModel {

    private String statusCode;
    private String message;
    private String status;
    private Object data;

    public ResponseModel() {

    }

    public ResponseModel(int status, String message) {
        this.status = status + "";
        this.message = message;
    }

    public ResponseModel(String status, String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
    }

    public ResponseModel(String status, String statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ResponseModel(String status, String statusCode, Object data) {
        this.statusCode = statusCode;
        this.status = status;
        this.data = data;
    }

    public ResponseModel(int status, String message, Object data, String statusCode) {
        this.status = status + "";
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    public static ResponseModel ok(Object data) {
        return new ResponseModel(NscMessageConstants.SUCCESS, HttpStatusConstants.OK, "Success", data);
    }

    public static ResponseModel ok(String message, Object data) {
        return new ResponseModel(NscMessageConstants.SUCCESS, HttpStatusConstants.OK, message, data);
    }

    public static ResponseModel ok(String message) {
        return new ResponseModel(NscMessageConstants.SUCCESS, HttpStatusConstants.OK, message, null);
    }

    public static ResponseModel error(String message) {
        return new ResponseModel(NscMessageConstants.FAILED, HttpStatusConstants.INTERNAL_SERVER_ERROR,  message);
    }

    public static ResponseModel notFound(String message) {
        return new ResponseModel(NscMessageConstants.NOT_FOUND, HttpStatusConstants.NOT_FOUND,  message);
    }
}
