package com.teamtext.duelgametohfe.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.av.smoothviewpager.Smoolider.SmoothViewpager;
import com.teamtext.duelgametohfe.Model.game.Game;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Adapter.PagerAdapterMain;
import com.teamtext.duelgametohfe.game.PlayGame;


import java.util.ArrayList;

public class FragmentGame extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_game, container, false);
        SmoothViewpager smoothViewpager = view.findViewById(R.id.smoolider);
        LinearLayout linearLayout=view.findViewById(R.id.show_send_comment);
        RecyclerView recycle_select_league=view.findViewById(R.id.recycle_select_league);
        LinearLayout imageViewClose=view.findViewById(R.id.imgclose);
        PagerAdapterMain adapterMain = new PagerAdapterMain(getActivity(), games(),linearLayout,recycle_select_league,imageViewClose);
        smoothViewpager.setAdapter(adapterMain);
        return view;
    }
    private ArrayList<Game> games()
    {
        ArrayList<Game> games=new ArrayList<>();
        games.add(ggame(1,getString(R.string.badkonak),R.drawable.badkonak2, PlayGame.class));
        return games;
    }
    private Game ggame(int id, String name, int resourceImage,Class aClass){
        Game game= new Game();
        game.id=id;
        game.name=name;
        game.aClass=aClass;
        game.resourceImage=resourceImage;
        return game;
    }
}
