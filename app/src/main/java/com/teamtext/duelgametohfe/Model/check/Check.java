package com.teamtext.duelgametohfe.Model.check;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Check {

  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("errors")
  @Expose
  private Object errors;

  @SerializedName("result")
  @Expose
  private JsonElement result;

  @SerializedName("status")
  @Expose
  private Integer status;


  public void setErrors(Object errors) {
    this.errors = errors;
  }

  public Object getErrors() {
    return errors;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
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



