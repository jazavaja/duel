package com.teamtext.duelgametohfe.Model.infobank;

import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoBank {

  @SerializedName("errors")
  @Expose
  private JsonElement errors;
  @SerializedName("message")
  @Expose
  private String message;
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

