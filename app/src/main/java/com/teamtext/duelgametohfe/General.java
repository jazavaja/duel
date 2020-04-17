package com.teamtext.duelgametohfe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.android.volley.VolleyError;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.Model.Version;
import com.teamtext.duelgametohfe.Model.infobank.InfoBank;
import com.teamtext.duelgametohfe.Model.infobank.ResultInfo;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class General {
    private SharedPreferences preferences;
    private Context context;

    public  String getTokenCafeBazzar(){
        return "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwDss0RsT7kE/ffaw1gwvjNIdBQThYngdpxjmFUkuW8lnus6s/PAH1npN+HX5vTedY+r5E+UjTXbzy4Uxh12Z2URWbWsv8Q8/dXD+50LhkZxZO5jSd3Kz1Sd7Ksu0D056GoduzoQV7S+dJlOni36i53fv4bwScaKzraf877A6SdzXa/I6kYiMvAhkjJU6bywaJS+PrZWuQU7rOCGjohGZTSxIfv3K1JFJTbqf/7j7cMCAwEAAQ==";
    }

    public void setNewVersionRequired() {

        preferences.edit().putBoolean("newVersion",true).apply();
    }
    public boolean getNewVersionRequired(){
        return preferences.getBoolean("newVersion",false);
    }
    public void saveVersionInfo(Version version){
        Gson gson=new Gson();
        String j=gson.toJson(version);
        preferences.edit().putString("infoVersion",j).apply();
    }
    public Version getVersionInfo(){
        Gson gson=new Gson();
        String s=preferences.getString("infoVersion","");
        return gson.fromJson(s,Version.class);
    }
    public String convertToHttp(String s){
        if ((!s.contains("http://")|| !s.contains("http"))){
            s="http://"+s;
        }
        return s;
    }

    public void setEmail(String email) {
        preferences.edit().putString("email",email).apply();
    }
    public String getEmail(){
      return  preferences.getString("email","");
    }

    public static String getBase64() {
        return "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwDss0RsT7kE/ffaw1gwvjNIdBQThYngdpxjmFUkuW8lnus6s/PAH1npN+HX5vTedY+r5E+UjTXbzy4Uxh12Z2URWbWsv8Q8/dXD+50LhkZxZO5jSd3Kz1Sd7Ksu0D056GoduzoQV7S+dJlOni36i53fv4bwScaKzraf877A6SdzXa/I6kYiMvAhkjJU6bywaJS+PrZWuQU7rOCGjohGZTSxIfv3K1JFJTbqf/7j7cMCAwEAAQ==";
    }


    public enum ListGames {
        GameBalloon,
        GameFruitNinja,
    }

    public void setScoreForAllGame(ListGames game, int value) {
        if (getScoreForAllGame(game) < value)
            preferences.edit().putInt(game.toString(), value).apply();
    }

    public int getScoreForAllGame(ListGames games) {
        return preferences.getInt(games.toString(), 0);
    }

    public General(Context context) {
        this.context = context;
//        String s=ListGames.GameBalloon.toString();

        preferences = context.getSharedPreferences("com.teamtext.dueltohhfe.App", Context.MODE_PRIVATE);
    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public void setCoin(int coin) {
        preferences.edit().putInt("coin", coin).apply();
    }

    public int getCoin() {
        return preferences.getInt("coin", 0);
    }

    public void setTicket(int ticket) {
        preferences.edit().putInt("ticket", ticket).apply();
    }

    public int getTicket() {
        return preferences.getInt("ticket", 0);
    }

    public void setFunds(int funds) {
        preferences.edit().putInt("funds", funds).apply();
    }

    public int getFunds() {
        return preferences.getInt("funds", 0);
    }

    public void setCardNumber(String cardNumber) {
        preferences.edit().putString("cardNumber", cardNumber).apply();
    }

    public String getCardNumber() {
        return preferences.getString("cardNumber", "");
    }

    public void setShaba(String shaba) {
        preferences.edit().putString("shaba", shaba).apply();
    }

    public String getShaba() {
        return preferences.getString("shaba", "");
    }

    public void setBankName(String bankName) {
        preferences.edit().putString("bank", bankName).apply();
    }

    public String getBankName() {
        return preferences.getString("bank", "");
    }

    public void setNameInBank(String nameBank) {
        preferences.edit().putString("nameBank", nameBank).apply();
    }

    public String getNameInBank() {
        return preferences.getString("nameBank", "");
    }

    public void setUserNamePlayer(String username) {
        preferences.edit().putString("username", username).apply();
    }

    public String getUsername() {
        return preferences.getString("username", "");
    }

    public void setLogOutUser(Context context) {
        preferences.edit().putBoolean("login", false).apply();

        setMethodSendSmsLogin();
        context.startActivity(new Intent(context, Login.class));
    }

    public void setLearnKoliRead(boolean readOrNo) {
        preferences.edit().putBoolean("learnKoli", readOrNo).apply();
    }

    public boolean getLearnKoli() {
        return preferences.getBoolean("learnKoli", false);
    }

    void setToken(String token) {
        preferences.edit().putString("token", token).apply();
    }

    public String getToken() {
        return preferences.getString("token", "");
    }

    void setUserIsLogin() {
        preferences.edit().putBoolean("login", true).apply();
    }

    void setMethodSendSmsLogin() {
        preferences.edit().putString("send", "sms").apply();
    }

    void setMethodCheckSmsLogin() {
        preferences.edit().putString("send", "check").apply();
    }

    boolean getUserIsLogin() {
        if (preferences.getBoolean("login", false)) {
            return true;
        } else {
            return false;
        }
    }

    String getLoginMethod() {
        return preferences.getString("send", "sms");
    }

    public static void errorDefault(Context context, View view) {
        Snackbar snackbar = Snackbar.make(view, context.getString(R.string.fail_connect_internet), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    public void getFundsFromServer(Context activity) {
        final General general = new General(activity);
        new Req(activity, "getInfoBanki", false) {


            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson = new Gson();
                InfoBank infoBank = gson.fromJson(response, InfoBank.class);
                if (infoBank.getStatus() == 200) {
                    ResultInfo result = gson.fromJson(infoBank.getResult(), ResultInfo.class);
                    setFunds(Integer.parseInt(result.getFunds()));
                }
            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("token", general.getToken());
                stringMap.put("type", "get");
                return stringMap;
            }
        };
    }

    public static void customMessage(Context context, View view, String matn) {
        Snackbar snackbar = Snackbar.make(view, matn, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void saveNewLeagueRegister(String id)
    {
        Set<String> set = new HashSet<>(getAllLeagueRegister());
        set.add(id);
        preferences.edit().putStringSet("leagueRegister",set).apply();
    }


    public Set<String> getAllLeagueRegister() {
        Set<String> set=new HashSet<>();
        set.add("1");
        set.add("2");
        return preferences.getStringSet("leagueRegister", set);
    }

    public String getPicture() {
        return preferences.getString("picture", "m1");
    }

    public void setPicture(String resource) {
        preferences.edit().putString("picture", resource).apply();
    }

    public void setLearnTransaction(boolean b)
    {
        preferences.edit().putBoolean("learnTransaction", b).apply();
    }

    public boolean getLearnTransaction() {
        return preferences.getBoolean("learnTransaction", false);
    }

    public String getDayilyCoinFree() {
        return preferences.getString("dayFreeCoin", "100");
    }

    public String getFreeCoinByAds() {
        return preferences.getString("freeCoin", "200");
    }

    public String freeTicketValue() {
        return preferences.getString("ticket", "1");
    }
}
