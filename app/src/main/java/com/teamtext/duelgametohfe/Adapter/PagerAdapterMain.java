package com.teamtext.duelgametohfe.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jem.fliptabs.FlipTab;
import com.makeramen.roundedimageview.RoundedImageView;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.game.Game;
import com.teamtext.duelgametohfe.Model.league.League;
import com.teamtext.duelgametohfe.Model.league.ResultLeague;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;
import com.teamtext.duelgametohfe.game.PlayGame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import org.jetbrains.annotations.NotNull;

public class PagerAdapterMain extends PagerAdapter {
    private Context context;
    private General general;
    private ArrayList<Game> games;
    private LinearLayout linearLayout;
    private RecyclerView recycle_select_league;
    private LinearLayout imageView;
    public PagerAdapterMain(Context context, ArrayList<Game> games,
                            LinearLayout layout, RecyclerView recycle_select_league,
                            LinearLayout imageView)
    {
        this.context = context;
        this.games = games;
        this.linearLayout=layout;
        this.imageView=imageView;
        this.recycle_select_league=recycle_select_league;
    }

    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_viewpager, container, false);
        RoundedImageView imageView = view.findViewById(R.id.img_slider);
        TextView txt_subtitle=view.findViewById(R.id.txt_subtitle);
        FlipTab fliptabs=view.findViewById(R.id.fliptabs);
        txt_subtitle.setText(games.get(position).getName());
        general=new General(context);
        imageView.setImageResource(games.get(position).getResourceImage());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_up_in);
                linearLayout.setAnimation(animation);
                linearLayout.setVisibility(View.GONE);
            }
        });

        fliptabs.setTabSelectedListener(new FlipTab.TabSelectedListener() {
            @Override
            public void onTabSelected(boolean b, @NotNull String s)
            {
                General.customMessage(context,linearLayout,"دوباره کلیک کتید");
            }

            @Override
            public void onTabReselected(boolean b, @NotNull String s) {
                if (s.equals("Offline"))
                {

                    Intent intent=new Intent(context, PlayGame.class);
                    context.startActivity(intent);
                }
                else
                {
                    getLeague(true);

                }
            }
        });
        container.addView(view);
        return view;
    }
    private void getLeague(boolean show) {
        new Req(context, "getLeague", show) {
            @Override
            public void NotFind()
            {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson = new Gson();
                League league = gson.fromJson(response, League.class);
                if (league.getStatus() == 200)
                {
                    ResultLeague[] resultLeague = gson.fromJson(league.getResult(), ResultLeague[].class);
                    Adapter_Row_Lig_Register ligRegister=new Adapter_Row_Lig_Register(context,resultLeague,games);
                    recycle_select_league.setAdapter(ligRegister);
                    recycle_select_league.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_down_out);
                    linearLayout.setAnimation(animation);
                    linearLayout.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("token", general.getToken());
                map.put("type", "get");
                return map;
            }
        };
    }

}
