package com.teamtext.duelgametohfe.retrofit;

import com.teamtext.duelgametohfe.Model.Update;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiRetrofit {
    @Multipart
    @POST("updateProfile")
    Call<Update> update(@Part("type") RequestBody type,
                        @Part("token") RequestBody token,
                        @Part("name") RequestBody name,
                        @Part("email") RequestBody email,
                        @Part("bank") RequestBody bank,
                        @Part("card") RequestBody card,
                        @Part("shaba") RequestBody shaba,
                        @Part MultipartBody.Part file
    );

    @FormUrlEncoded
    @POST("updateProfile")
    Call<Update> up(
            @Field("type") String type,
            @Field("token") String token,
            @Field("name") String name,
            @Field("email") String email,
            @Field("bank") String bank,
            @Field("card") String card,
            @Field("shaba") String shaba
    );
}
