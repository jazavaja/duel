package com.teamtext.duelgametohfe.retrofit;

import com.teamtext.duelgametohfe.Req;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    public Retro(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Req.urlRoot)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
}
