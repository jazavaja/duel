package com.teamtext.duelgametohfe;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.teamtext.duelgametohfe.fragment.FragmentGame;
import com.teamtext.duelgametohfe.fragment.FragmentLeague;
import com.teamtext.duelgametohfe.fragment.Fragment_Shop;
import com.teamtext.duelgametohfe.fragment.Fragment_comment;
import com.teamtext.duelgametohfe.fragment.GiveMoneyFragment;
import com.teamtext.duelgametohfe.fragment.ProfileFragment;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rec;
    ImageView profileIcon, exit;
    BottomNavigationView bottomNavigationView;
    General general;
    CafeBazzar cafeBazzar;

    public void bottomFunc(Bundle savedInstanceState) {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, new FragmentGame()).commit();
        }

        bottomNavigationView.setSelectedItemId(R.id.action_play);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.action_comment:
                    fragment = new Fragment_comment();
                    break;
                case R.id.action_rank:
                    fragment = new FragmentLeague();
                    break;
                case R.id.action_play:
                    fragment = new FragmentGame();
                    break;
                case R.id.action_mony:
                    fragment = new GiveMoneyFragment();
                    break;
                case R.id.action_buy:
                    fragment = new Fragment_Shop();
                    Bundle bundle=new Bundle();
                    bundle.putString("f","aaaa");
                    bundle.putParcelable("cafe", (Parcelable) cafeBazzar);
                    fragment.setArguments(bundle);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main, fragment).commit();

            return true;
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
        cafeBazzar.onActivityCafe(requestCode, resultCode, data, new CafeBazzar.OnResult() {
            @Override
            public void OnResultCustom() {
                MainActivity.super.onActivityResult(requestCode,resultCode,data);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        general = new General(this);
        initCafeBazzar();
        bottomFunc(savedInstanceState);
        setProfileIcon();
        exit();

    }
    public void initCafeBazzar() {
        cafeBazzar=new CafeBazzar(this, General.getBase64(), new CafeBazzarInterface() {
            @Override
            public void ErrorSetupIabHelper(Exception error) {

            }

            @Override
            public void ErrorLaunch() {

            }
        });
    }

    private void exit() {
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LovelyStandardDialog dialog = new LovelyStandardDialog(MainActivity.this)
                        .setMessage("ایا مطمئنی میخوای خارج بشی؟ ")
                        .setTitle("توجه !");
                dialog.setNegativeButton("انصراف", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.setPositiveButton("خارج میشم", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        general.setLogOutUser(MainActivity.this);
                        startActivity(new Intent(MainActivity.this, Login.class));
                    }
                });
                dialog.show();
            }
        });
    }

    private void setProfileIcon() {
        profileIcon = findViewById(R.id.profileIcon);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout_main, new ProfileFragment())
                        .commit();
            }
        });

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cafeBazzar.destroy();
    }

}
