package com.teamtext.duelgametohfe.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultReq {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("user_id")
  @Expose
  private Integer userId;
  @SerializedName("amount")
  @Expose
  private Integer amount;
  @SerializedName("request_id")
  @Expose
  private String requestId;
  @SerializedName("status")
  @Expose
  private String status;
  @SerializedName("transaction")
  @Expose
  private String transaction;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("created_at")
  @Expose
  private Object createdAt;
  @SerializedName("updated_at")
  @Expose
  private Object updatedAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTransaction() {
    return transaction;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Object getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Object createdAt) {
    this.createdAt = createdAt;
  }

  public Object getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Object updatedAt) {
    this.updatedAt = updatedAt;
  }

}
