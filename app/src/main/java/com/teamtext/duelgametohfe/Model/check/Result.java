package com.teamtext.duelgametohfe.Model.check;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("mobile")
  @Expose
  private String mobile;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("api_token")
  @Expose
  private String apiToken;
  @SerializedName("coin")
  @Expose
  private Integer coin;
  @SerializedName("ticket")
  @Expose
  private Integer ticket;
  @SerializedName("picture")
  @Expose
  private Object picture;
  @SerializedName("code")
  @Expose
  private Integer code;
  @SerializedName("created_at")
  @Expose
  private Object createdAt;
  @SerializedName("updated_at")
  @Expose
  private String updatedAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getApiToken() {
    return apiToken;
  }

  public void setApiToken(String apiToken) {
    this.apiToken = apiToken;
  }

  public Integer getCoin() {
    return coin;
  }

  public void setCoin(Integer coin) {
    this.coin = coin;
  }

  public Integer getTicket() {
    return ticket;
  }

  public void setTicket(Integer ticket) {
    this.ticket = ticket;
  }

  public Object getPicture() {
    return picture;
  }

  public void setPicture(Object picture) {
    this.picture = picture;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Object getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Object createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

}
