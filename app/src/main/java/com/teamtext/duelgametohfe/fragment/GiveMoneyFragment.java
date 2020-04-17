package com.teamtext.duelgametohfe.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.jem.fliptabs.FlipTab;
import com.marozzi.segmentedtab.SegmentedGroup;
import com.marozzi.segmentedtab.SegmentedTab;
import com.teamtext.duelgametohfe.Adapter.AdapterRequestPay;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.ModelKoli;
import com.teamtext.duelgametohfe.Model.Update;
import com.teamtext.duelgametohfe.Model.request.RequestPay;
import com.teamtext.duelgametohfe.Model.request.ResultReq;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GiveMoneyFragment extends Fragment {

    private Context context;
    private LinearLayout layoutPardakht, layoutSharzh,main;
    private FlipTab fliptabs;
    private Button btn_req_sharj;
    private TextView mojodi;
    private RecyclerView recyclerView;
    private View empty;
    private EditText mablag;
    private SegmentedGroup segmentedGroup;
    private RadioGroup radioGroup;
    private EditText mobile;
    private Button send_request;
    private General general;
    private String getOperator="MTN";
    private SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_give_money, container, false);
        find(view);
        getSharj(view);
        setSend_request();
        setFliptabs();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRequestPay();
            }
        });

        return view;
    }
    private void find(View view){
        general = new General(context);
        layoutPardakht = view.findViewById(R.id.layoutPardakht);
        layoutSharzh = view.findViewById(R.id.layoutSharzh);
        fliptabs = view.findViewById(R.id.fliptabs);
        btn_req_sharj = view.findViewById(R.id.btn_req_sharj);
        mablag = view.findViewById(R.id.mablagDarkhasti);
        empty = view.findViewById(R.id.empty);
        radioGroup = view.findViewById(R.id.amount);
        segmentedGroup = view.findViewById(R.id.operator);
        mojodi = view.findViewById(R.id.mojodi);
        mobile = view.findViewById(R.id.mobile);
        main=view.findViewById(R.id.main);
        recyclerView = view.findViewById(R.id.recycleView);
        send_request = view.findViewById(R.id.send_request_payment);
        refreshLayout = view.findViewById(R.id.refresh);
        mojodi.setText(String.valueOf(general.getFunds()));
        segmentedGroup.setOnSegmentedGroupListener(new SegmentedGroup.OnSegmentedGroupListener() {
            @Override
            public void onSegmentedTabSelected(SegmentedTab tab, int checkedId) {
                switch (checkedId)
                {
                    case R.id.irancell:
                        getOperator="MTN";
                        break;
                    case R.id.rightel:
                        getOperator="RTL";
                        break;
                    case R.id.mci:
                        getOperator="MCI";
                        break;
                }
            }
        });
    }

    private void setSend_request() {
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSubmitRequestPay();
            }
        });
    }

    private void getRequestPay() {
        general.getFundsFromServer(context);
        new Req(context, "getRequestPay", true) {
            @Override
            public void NotFind() {
                refreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
            }

            @Override
            public void ResponseSuccess(String response) {
                mojodi.setText(String.valueOf(general.getFunds()));
                refreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                Gson gson = new Gson();
                RequestPay requestPay = gson.fromJson(response, RequestPay.class);
                if (requestPay.getStatus() == 200) {
                    ResultReq[] req = gson.fromJson(requestPay.getResult(), ResultReq[].class);
                    AdapterRequestPay pay = new AdapterRequestPay(context, req);
                    recyclerView.setAdapter(pay);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                    refreshLayout.setRefreshing(false);
                } else {
                    General.customMessage(context, refreshLayout, requestPay.getMessage());
                }
            }

            @Override
            public void ResponseError(VolleyError error) {
                refreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                empty.setVisibility(View.VISIBLE);
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("type", "get");
                map.put("token", general.getToken());
                return map;
            }
        };

        mojodi.setText(String.valueOf(general.getFunds()));
    }

    private void setFliptabs() {
        fliptabs.setTabSelectedListener(new FlipTab.TabSelectedListener() {
            @Override
            public void onTabSelected(boolean b, @NotNull String s) {
                if (b) {
                    layoutSharzh.setVisibility(View.GONE);
                    layoutPardakht.setVisibility(View.VISIBLE);
                } else {
                    layoutPardakht.setVisibility(View.GONE);
                    layoutSharzh.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabReselected(boolean b, @NotNull String s) {
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void setSubmitRequestPay() {
        if (!mablag.getText().toString().equals("")) {
            new Req(context, "submitRequestPay", true) {
                @Override
                public void NotFind() {
                }

                @Override
                public void ResponseSuccess(String response) {
                    Gson gson = new Gson();
                    Update update = gson.fromJson(response, Update.class);
                    General.customMessage(context, refreshLayout, update.getMessage());
                }

                @Override
                public void ResponseError(VolleyError error) {
                    General.customMessage(context, refreshLayout, error.getMessage());
                }

                @Override
                public Map<String, String> Parameters() {
                    Map<String, String> map = new HashMap<>();
                    map.put("type", "submit");
                    map.put("amount", mablag.getText().toString());
                    map.put("token", general.getToken());
                    return map;
                }
            };
        } else
            General.customMessage(context, refreshLayout, "لطفا مبلغ را وارد کنید");
    }

    private void getSharj(View view) {

            btn_req_sharj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if (!mobile.getText().toString().equals("")){
                        boolean select = radioGroup.getCheckedRadioButtonId() != -1;
                        if (select) {
                            RadioButton radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                            String amount=radioButton.getText().toString();
                            requestGetSharj(amount,getOperator,mobile.getText().toString());
                        }
                    }else
                    {
                        General.customMessage(context,main,"شماره موبایل خود را وارد کنید");
                    }

                }
            });
    }

    private void requestGetSharj(String amount, String operator, String mobileTXT) {
        new Req(context, "sharj", true) {
            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson=new Gson();
                ModelKoli modelKoli=gson.fromJson(response,ModelKoli.class);
                if (modelKoli.getStatus()==200)
                {
                    General.customMessage(context,main,modelKoli.getMessage().toString());
                }else{
                    General.customMessage(context,main,modelKoli.getMessage().toString());
                }

            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {
                radioGroup.getCheckedRadioButtonId();
                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("token", general.getToken());
                stringStringMap.put("type", "set");
                stringStringMap.put("amount", amount);
                stringStringMap.put("mobile", mobileTXT);
                stringStringMap.put("operator", operator);
                return stringStringMap;
            }
        };
    }
}
