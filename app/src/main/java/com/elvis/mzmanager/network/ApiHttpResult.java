package com.elvis.mzmanager.network;

import java.io.Serializable;

/**
 * ApiHttpResult
 *
 * @author elvis
 */
public class ApiHttpResult<T> implements Serializable {


    private int code;
    private int system_result_key;
    private String system_result_message_key;
    private T data;

    public int getCode() {

        if (system_result_key != 0) return system_result_key;
        else return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return 0 == system_result_key;
    }

    public String getErrMsg() {
        return system_result_message_key;
    }
}
