package com.teamtext.duelgametohfe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.teamtext.duelgametohfe.Adapter.AdapterComments;
import com.teamtext.duelgametohfe.General;
import com.teamtext.duelgametohfe.Model.Update;
import com.teamtext.duelgametohfe.Model.comment.Comments;
import com.teamtext.duelgametohfe.Model.comment.ResultComments;
import com.teamtext.duelgametohfe.R;
import com.teamtext.duelgametohfe.Req;

import java.util.HashMap;
import java.util.Map;

public class Fragment_comment extends Fragment {
    private LinearLayout btncomment, show_send_comment;
    private ImageView imgclose;
    private EditText mozo, matn;
    private Button send;
    TextView textView;
    private View viewLayout;
    private Context context;
    private General general;
    private View empty;
    private RecyclerView recyclerView;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        find(view);
        setImgclose();
        btnLoadComment();
        getComments();
        send();
        return view;
    }

    private void getComments() {
        new Req(context, "getComments", true) {


            @Override
            public void NotFind() {
                empty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public void ResponseSuccess(String response) {
                recyclerView.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                Gson gson = new Gson();
                Comments comments = gson.fromJson(response, Comments.class);
                if (comments.getStatus() == 200) {
                    ResultComments[] result = gson.fromJson(comments.getResult(), ResultComments[].class);
                    AdapterComments adapter = new AdapterComments(result,context);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }
            }

            @Override
            public void ResponseError(VolleyError error)
            {
                empty.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("type", "get");
                map.put("token", general.getToken());
                return map;
            }
        };
    }

    private void createComment(final Context context, final General general, final String title, final String description) {
        new Req(context, "createComment", true) {
            @Override
            public void NotFind() {

            }

            @Override
            public void ResponseSuccess(String response) {
                Gson gson = new Gson();
                Update update = gson.fromJson(response, Update.class);
                if (update.getStatus() == 200) {
                    General.customMessage(context, btncomment, update.getMessage());
                } else {
                    General.customMessage(context, btncomment, update.getMessage());
                }
            }

            @Override
            public void ResponseError(VolleyError error) {

            }

            @Override
            public Map<String, String> Parameters() {
                Map<String, String> map = new HashMap<>();
                map.put("token", general.getToken());
                map.put("type", "create");
                map.put("title", title);
                map.put("content", description);
                return map;
            }
        };
    }

    private void send() {
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createComment(context, general, mozo.getText().toString(), matn.getText().toString());

            }
        });
    }

    private void setImgclose() {
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                close();
            }
        });
    }

    private void find(View view) {
        recyclerView=view.findViewById(R.id.recycleView);
        general = new General(context);
        imgclose = view.findViewById(R.id.imgclose);
        show_send_comment = view.findViewById(R.id.show_send_comment);
        btncomment = view.findViewById(R.id.btncomment);
        viewLayout = view.findViewById(R.id.view);
        mozo = view.findViewById(R.id.mozo);
        matn = view.findViewById(R.id.payam);
        empty=view.findViewById(R.id.empty);
        send = view.findViewById(R.id.send);
    }

    private void btnLoadComment() {
        btncomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_in);
                Animation animation_out = AnimationUtils.loadAnimation(getActivity(), R.anim.push_down_out);
                btncomment.setAnimation(animation);
                btncomment.setVisibility(View.GONE);
                show_send_comment.setAnimation(animation_out);
                show_send_comment.setVisibility(View.VISIBLE);
            }
        });
    }

    private void close() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.push_up_in);
        show_send_comment.setAnimation(animation);
        show_send_comment.setVisibility(View.GONE);
        Animation animation_out = AnimationUtils.loadAnimation(getActivity(), R.anim.push_down_out);
        btncomment.setAnimation(animation_out);
        btncomment.setVisibility(View.VISIBLE);
    }
}