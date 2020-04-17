package com.teamtext.duelgametohfe.Model.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultShop {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("price")
  @Expose
  private String price;
  @SerializedName("new_price")
  @Expose
  private String newPrice;
  @SerializedName("picture")
  @Expose
  private String picture;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("coin")
  @Expose
  private String coin;
  @SerializedName("ticket")
  @Expose
  private Integer ticket;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getNewPrice() {
    return newPrice;
  }

  public void setNewPrice(String newPrice) {
    this.newPrice = newPrice;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCoin() {
    return coin;
  }

  public void setCoin(String coin) {
    this.coin = coin;
  }

  public Integer getTicket() {
    return ticket;
  }

  public void setTicket(Integer ticket) {
    this.ticket = ticket;
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
