package com.teamtext.duelgametohfe;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.Model.ModelKoli;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class Req extends Volley{
    ProgressDialog progressDialog;
    private Dialog dialog;
    public static int Error = 11;
    public static String mainRoot = "http://tohfegame.ir/";
    public static String rootImg = mainRoot+"img/";
    public static String urlRoot = mainRoot+"api/";
    General general;

    public Req(final Context context, String url, final boolean showProgress) {
        general=new General(context);
        if (showProgress) {
            dialog=new Dialog(context);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.request_server_dialog);
            dialog.show();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlRoot + url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (showProgress)
                            dialog.dismiss();
                        Log.e("Response", response);
                        Gson gson = new Gson();
                        ModelKoli modelKoli = gson.fromJson(response, ModelKoli.class);
                        if (modelKoli.getStatus() == 411) {
                            general.setLogOutUser(context);
                        } else if (modelKoli.getStatus() == 414) {
                            Req.this.NotFind();
                        } else {
                            Req.this.ResponseSuccess(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (showProgress)
                            dialog.dismiss();
                        Req.this.ResponseError(error);
                        String sq = error.getLocalizedMessage();
                        Log.e("StringError", sq + "");

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                String ss = Locale.getDefault().getLanguage();
                stringMap.put("lang", "fa");
                stringMap.putAll(Parameters());
                return stringMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(4000,0,0));
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);

//        General general=new General(context);
//        Log.e("Token",general.getToken());

    }

    public static String getPaymentUrl(String amount,String token,String productid){
        return mainRoot+"pay"+"?"+"amount"+"="+amount+"&"+"token"+"="+token+"&"+"productid"+"="+productid;
    }

    public abstract void NotFind();

    public abstract void ResponseSuccess(String response);

    public abstract void ResponseError(VolleyError error);

    public abstract Map<String, String> Parameters();


}
