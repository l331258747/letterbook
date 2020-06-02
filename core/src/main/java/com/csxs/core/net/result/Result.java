package com.csxs.core.net.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SenYe on 2018/1/26.
 */
public class Result<T> implements Serializable {

    private int code;
    private String msg;
    private boolean error;

    @SerializedName("data")
    private T results;

  //  private T result;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResults() {

        return results;
    }
    public void setResults(T results) {
        this.results = results;
    }




}
