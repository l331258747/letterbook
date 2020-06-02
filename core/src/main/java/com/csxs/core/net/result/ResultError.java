package com.csxs.core.net.result;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by SenYe on 2018/1/26.
 */
public class ResultError implements Serializable {

    private String statusCode;
    private String message;
    @SerializedName("data")
    private String results;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
