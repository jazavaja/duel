package com.teamtext.duelgametohfe;
import android.app.Application;
import ir.tapsell.sdk.Tapsell;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Tapsell.initialize(this,"magltfmhamhbncecreodindhmkaghnomfdkefqqrdjrdjkscrmrnmthtnmlsimhgjqndis");

    }
}
