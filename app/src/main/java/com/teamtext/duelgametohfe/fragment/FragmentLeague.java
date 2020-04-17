package com.teamtext.duelgametohfe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.Adapter.Adapter_Row_Lig;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.league.League;
import com.teamtext.duelgametohfe.Model.league.ResultLeague;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;

import java.util.HashMap;
import java.util.Map;

import static android.widget.LinearLayout.*;

public class FragmentLeague extends Fragment {
    private RecyclerView allLeague,detailLeague;
    private View empty;
    private Context context;
    private General general;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.league_fragment, container, false);
        empty=view.findViewById(R.id.empty);
        general=new General(context);
        allLeague = view.findViewById(R.id.all_league);
        detailLeague=view.findViewById(R.id.detail_league);
        getLeague(true);
        return view;
    }


    private void getLeague(boolean show) {
        new Req(context, "getLeague", show) {
            @Override
            public void NotFind() {
                empty.setVisibility(View.VISIBLE);
                allLeague.setVisibility(VISIBLE);

            }

            @Override
            public void ResponseSuccess(String response) {
                empty.setVisibility(View.GONE);
                allLeague.setVisibility(VISIBLE);
                Gson gson = new Gson();
                League league = gson.fromJson(response, League.class);
                if (league.getStatus() == 200) {
                    ResultLeague[] resultLeague = gson.fromJson(league.getResult(), ResultLeague[].class);
                    Adapter_Row_Lig adapterRowLig=new Adapter_Row_Lig(context,resultLeague,empty,detailLeague,general);
                    allLeague.setAdapter(adapterRowLig);
                    allLeague.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
                }
            }

            @Override
            public void ResponseError(VolleyError error) {
                empty.setVisibility(View.VISIBLE);
                allLeague.setVisibility(GONE);
                detailLeague.setVisibility(GONE);
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
