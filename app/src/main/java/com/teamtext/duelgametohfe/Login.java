package com.teamtext.duelgametohfe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.Model.SendModel;
import com.teamtext.duelgametohfe.Model.check.Check;
import com.teamtext.duelgametohfe.Model.check.Result;
import com.teamtext.duelgametohfe.fragment.FragmentGhavanin;

import java.util.HashMap;
import java.util.Map;

import ir.tapsell.plus.TapsellPlus;
import ir.tapsell.sdk.Tapsell;

public class Login extends AppCompatActivity {
    TextView txtGhavanin;
    General general;
    ConstraintLayout main;
    EditText mobileEdt;
    Button code_sent;
    LinearLayout linear_m;
    EditText checkCodeEdt;
    TextView changeNumber;
    AppCompatCheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setting();
        ghavaninClick();
        goToMain();
        TapsellPlus.initialize(this,"magltfmhamhbncecreodindhmkaghnomfdkefqqrdjrdjkscrmrnmthtnmlsimhgjqndis");
    }


    public void ghavaninClick() {
        txtGhavanin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                FragmentGhavanin fragment_ghavanin = new FragmentGhavanin();
                fragment_ghavanin.show(getSupportFragmentManager(), fragment_ghavanin.getTag());
            }
        });
    }

    private void checkCode() {
        new Req(Login.this, "check", true) {
            @Override
            public void NotFind()
            {

            }
            @Override
            public void ResponseSuccess(String response)
            {
                Gson gson = new Gson();
                Check check = gson.fromJson(response, Check.class);
                if (check.getStatus() == 200) {
                    Gson gson1 = new Gson();
                    Result m = gson1.fromJson(check.getResult(), Result.class);
                    general.setToken(m.getApiToken());
                    general.setUserIsLogin();
                    goToMain();
                } else {
                    General.customMessage(getApplicationContext(), main, check.getMessage());
                }
            }

            @Override
            public void ResponseError(VolleyError error) {
                General.errorDefault(Login.this,main);
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("code", checkCodeEdt.getText().toString());
                stringMap.put("mobile", mobileEdt.getText().toString());
                return stringMap;
            }
        };
    }

    public void goToMain() {
        if (general.getUserIsLogin()) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }

    public void changeNumber(View view) {
        general.setMethodSendSmsLogin();
        change();
    }

    public void sendCode() {
        new Req(Login.this, "send", true) {


            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson = new Gson();
                SendModel sendModel = gson.fromJson(response, SendModel.class);
                if (sendModel.getStatus() == 200) {
                    General.customMessage(getApplicationContext(), main, sendModel.getMessage());
                    general.setMethodCheckSmsLogin();
                    change();
                } else {
                    General.customMessage(getApplicationContext(), main, sendModel.getMessage());
                }
            }

            @Override
            public void ResponseError(VolleyError error) {
                General.errorDefault(getApplicationContext(), main);
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("mobile", mobileEdt.getText().toString());
                return stringMap;
            }
        };
    }

    public void btnlogin(View view) {
        if (checkBox.isChecked())
        {
            if (general.getLoginMethod().equals("sms"))
                sendCode();
            else
            {
                checkCode();
            }
        }
        else
        {
            General.customMessage(this,main,"باید قوانین را قبول کنید");
        }

    }

    public void setting() {
        linear_m=findViewById(R.id.linear_m);
        checkBox=findViewById(R.id.checkboxGhavanin);
        changeNumber=findViewById(R.id.changeNumber);
        code_sent = findViewById(R.id.code_sent);
        checkCodeEdt = findViewById(R.id.code_edt);
        mobileEdt = findViewById(R.id.mobile_edt);
        main = findViewById(R.id.main);
        general = new General(this);
        txtGhavanin = findViewById(R.id.txtGhavanin);

    }

    public void change() {
        if (general.getLoginMethod().equals("check")) {
            mobileEdt.setVisibility(View.GONE);
            code_sent.setText(getString(R.string.check_code));
            checkCodeEdt.setVisibility(View.VISIBLE);
            linear_m.setVisibility(View.GONE);
        }
        if (general.getLoginMethod().equals("sms")) {
            mobileEdt.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            code_sent.setText(getString(R.string.get_code));
            linear_m.setVisibility(View.VISIBLE);

        }
    }
}
