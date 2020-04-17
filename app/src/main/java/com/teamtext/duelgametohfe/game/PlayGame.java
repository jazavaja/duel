package com.teamtext.duelgametohfe.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.ModelKoli;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;

import java.util.HashMap;
import java.util.Map;

public class PlayGame extends AppCompatActivity {

    LinearLayout linearLayout;
    WebView webView;
    General general;
    public static int stringIntent = -100;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame_tower);
        webView = findViewById(R.id.webview);
        linearLayout = findViewById(R.id.linear);
        general = new General(this);
        stringIntent = getIntent().getIntExtra("ID", -100);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.addJavascriptInterface(new WebAppInterface(this,this), "Android");
        webView.loadUrl("file:///android_asset/tower/index.html");   // now it will not fail here

    }

    public static class WebAppInterface {
        Context mContext;
        //        PlayGame playGame;
        String data;
        PlayGame playGame;

        WebAppInterface(PlayGame playGame,Context ctx) {
            this.mContext = ctx;
            this.playGame=playGame;
        }


        @JavascriptInterface
        public void sendData(String data) {
//            playGame=new PlayGame();
            //Get the string value to process
            this.data = data;
            int s = PlayGame.stringIntent;
            if (s == -100)
                Toast.makeText(mContext, "شما بازی افلاین برای تست انجام میدهید برای شرکت در لیگ بازی انلاین را انجام دهید", Toast.LENGTH_SHORT).show();
            else
                playGame.sendScore(mContext,s, Integer.parseInt(data));

        }
    }

    public  void sendScore(Context context,int leagueID, int point) {
        new Req(context, "updatePoint", true) {
            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson = new Gson();
                ModelKoli modelKoli = gson.fromJson(response, ModelKoli.class);
                if (modelKoli.getStatus() == 200)
                {
                    Toast.makeText(context, "رکورد شما با موئفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "ناموفق بود لطفا دوباره تلاش کنید", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void ResponseError(VolleyError error) {
                Toast.makeText(context, "اشکال در ثبت لطفا تصال به اینترنت را چک کنید", Toast.LENGTH_SHORT).show();
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("token", general.getToken());
                map.put("type", "update");
                map.put("league_id", String.valueOf(leagueID));
                map.put("point", String.valueOf(point));
                return map;
            }
        };
    }
}
