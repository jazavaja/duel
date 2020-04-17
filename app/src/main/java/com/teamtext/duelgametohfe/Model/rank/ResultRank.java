package com.teamtext.duelgametohfe.Model.rank;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultRank {

  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("picture")
  @Expose
  private String picture;
  @SerializedName("league_id")
  @Expose
  private String leagueId;
  @SerializedName("point")
  @Expose
  private String point;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getLeagueId() {
    return leagueId;
  }

  public void setLeagueId(String leagueId) {
    this.leagueId = leagueId;
  }

  public String getPoint() {
    return point;
  }

  public void setPoint(String point) {
    this.point = point;
  }
}
