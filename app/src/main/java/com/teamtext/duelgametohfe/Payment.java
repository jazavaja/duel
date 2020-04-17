package com.teamtext.duelgametohfe;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Payment extends Activity {
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = findViewById(R.id.payment);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("success"))
                {
                    Toast.makeText(Payment.this, "Payment SuccessFull", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if (url.contains("fail"))
                {
                    Toast.makeText(Payment.this, "Payment Was Fails", Toast.LENGTH_SHORT).show();
                    finish();
                }
                if (url.contains("cancel"))
                {
                    Toast.makeText(Payment.this, "Payment Cancel By User", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        Toast.makeText(this, getString(R.string.payment_loading), Toast.LENGTH_SHORT).show();
        webView.loadUrl(getIntent().getStringExtra("pay"));
    }
}
