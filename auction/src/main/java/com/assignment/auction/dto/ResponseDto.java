/**
 * @author yukti.gupta
 * @date 28/04/23
 * @project_name auction
 **/

package com.assignment.auction.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDto<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean status;
    private String message;

    private String id;
    private String httpCode;
    private T data;
    private ResponseDto(boolean status) {
        this.status = status;
    }

    protected ResponseDto(boolean status, String message) {
        this(status);
        this.message = message;
    }

    protected ResponseDto(boolean status, String message, T data) {
        this(status, message);
        this.data = data;
    }

    protected ResponseDto(boolean status, String message,  T data, String httpCode) {
        this(status, message, data);
        this.httpCode = httpCode;
    }

    protected ResponseDto(boolean status, String message, String id, String httpCode) {
        this(status, message);
        this.httpCode = httpCode;
    }
    public static <T> ResponseDto<T> success(String message) {
        return new ResponseDto<>(true, message, null);
    }

    public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>(true, message, data);
    }

    public static <T> ResponseDto<T> success(String message, T data, String httpCode) {
        return new ResponseDto<>(true, message, data, httpCode);
    }

    public static <T> ResponseDto<T> success(String message, String id, String httpCode) {
        return new ResponseDto<>(true, message, id, httpCode);
    }

    public static <T> ResponseDto<T> failure(String message) {
        return new ResponseDto<>(false, message);
    }

    public static <T> ResponseDto<T> failure(String message, T data) {
        return new ResponseDto<>(false, message, data);
    }

    public static <T> ResponseDto<T> failure(String message, String id, String httpCode) {
        return new ResponseDto<>(false, message, id, httpCode);
    }


    public static <T> ResponseDto<T> failure(String message, T data, String httpCode) {
        return new ResponseDto<>(false, message, data, httpCode);
    }
}
