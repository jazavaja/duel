package com.teamtext.duelgametohfe.Model.infobank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultInfo {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("user_id")
  @Expose
  private Integer userId;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("card_number")
  @Expose
  private String cardNumber;
  @SerializedName("shaba")
  @Expose
  private String shaba;
  @SerializedName("name_bank")
  @Expose
  private String nameBank;
  @SerializedName("funds")
  @Expose
  private String funds;
  @SerializedName("created_at")
  @Expose
  private Object createdAt;
  @SerializedName("updated_at")
  @Expose
  private String updatedAt;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getShaba() {
    return shaba;
  }

  public void setShaba(String shaba) {
    this.shaba = shaba;
  }

  public String getNameBank() {
    return nameBank;
  }

  public void setNameBank(String nameBank) {
    this.nameBank = nameBank;
  }

  public String getFunds() {
    return funds;
  }

  public void setFunds(String funds) {
    this.funds = funds;
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

}
