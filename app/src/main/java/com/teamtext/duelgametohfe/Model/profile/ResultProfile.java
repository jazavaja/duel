package com.teamtext.duelgametohfe.Model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultProfile {

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
    @SerializedName("coin")
    @Expose
    private Integer coin;
    @SerializedName("ticket")
    @Expose
    private Integer ticket;
    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("nameBank")
    @Expose
    private String nameBank;

    @SerializedName("shaba")
    @Expose
    private String shaba;

    @SerializedName("card")
    @Expose
    private String card;


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

//    public String getApiToken() {
//        return apiToken;
//    }

//    public void setApiToken(String apiToken) {
//        this.apiToken = apiToken;
//    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNameBank() {
        return nameBank;
    }

    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

    public String getShaba() {
        return shaba;
    }

    public void setShaba(String shaba) {
        this.shaba = shaba;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
