package com.teamtext.duelgametohfe.Model.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultComments {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("content")
  @Expose
  private String content;
  @SerializedName("user_id")
  @Expose
  private Integer userId;
  @SerializedName("reply")
  @Expose
  private String reply;
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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
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
