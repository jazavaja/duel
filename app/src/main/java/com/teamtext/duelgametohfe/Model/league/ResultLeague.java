package com.teamtext.duelgametohfe.Model.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLeague {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("cost_coin")
  @Expose
  private String costCoin;
  @SerializedName("cost_ticket")
  @Expose
  private String costTicket;
  @SerializedName("picture")
  @Expose
  private String picture;
  @SerializedName("prize")
  @Expose
  private String prize;
  @SerializedName("max_user")
  @Expose
  private String maxUser;
  @SerializedName("time_reg_start")
  @Expose
  private String timeRegStart;
  @SerializedName("time_reg_end")
  @Expose
  private String timeRegEnd;
  @SerializedName("time_end_league")
  @Expose
  private String timeEndLeague;
  @SerializedName("calculate")
  @Expose
  private String calculate;
  @SerializedName("game")
  @Expose
  private Object game;
  @SerializedName("created_at")
  @Expose
  private String createdAt;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCostCoin() {
    return costCoin;
  }

  public void setCostCoin(String costCoin) {
    this.costCoin = costCoin;
  }

  public String getCostTicket() {
    return costTicket;
  }

  public void setCostTicket(String costTicket) {
    this.costTicket = costTicket;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getPrize() {
    return prize;
  }

  public void setPrize(String prize) {
    this.prize = prize;
  }

  public String getMaxUser() {
    return maxUser;
  }

  public void setMaxUser(String maxUser) {
    this.maxUser = maxUser;
  }

  public String getTimeRegStart() {
    return timeRegStart;
  }

  public void setTimeRegStart(String timeRegStart) {
    this.timeRegStart = timeRegStart;
  }

  public String getTimeRegEnd() {
    return timeRegEnd;
  }

  public void setTimeRegEnd(String timeRegEnd) {
    this.timeRegEnd = timeRegEnd;
  }

  public String getTimeEndLeague() {
    return timeEndLeague;
  }

  public void setTimeEndLeague(String timeEndLeague) {
    this.timeEndLeague = timeEndLeague;
  }

  public String getCalculate() {
    return calculate;
  }

  public void setCalculate(String calculate) {
    this.calculate = calculate;
  }

  public Object getGame() {
    return game;
  }

  public void setGame(Object game) {
    this.game = game;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

}
