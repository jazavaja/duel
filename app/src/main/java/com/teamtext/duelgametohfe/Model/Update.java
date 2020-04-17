package com.teamtext.duelgametohfe.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Update {

  @SerializedName("errors")
  @Expose
  private String errors;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("result")
  @Expose
  private String result;
  @SerializedName("status")
  @Expose
  private Integer status;

  public String getErrors() {
    return errors;
  }

  public void setErrors(String errors) {
    this.errors = errors;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}