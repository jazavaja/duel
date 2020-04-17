package com.teamtext.duelgametohfe.Model;


import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelKoli {

    @SerializedName("errors")
    @Expose
    private JsonElement errors;
    @SerializedName("message")
    @Expose
    private JsonElement message;
    @SerializedName("result")
    @Expose
    private JsonElement result;
    @SerializedName("status")
    @Expose
    private Integer status;

    public JsonElement getErrors() {
        return errors;
    }

    public void setErrors(JsonElement errors) {
        this.errors = errors;
    }

    public JsonElement getMessage() {
        return message;
    }

    public void setMessage(JsonElement message) {
        this.message = message;
    }

    public JsonElement getResult() {
        return result;
    }

    public void setResult(JsonElement result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}