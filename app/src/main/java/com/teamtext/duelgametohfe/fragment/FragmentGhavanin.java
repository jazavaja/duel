package com.teamtext.duelgametohfe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.teamtext.duelgametohfe.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FragmentGhavanin extends BottomSheetDialogFragment {

    private TextView jtvTxt;
    private ImageView imgclose;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ghavanin,container,false);
        jtvTxt = view.findViewById(R.id.jtvTxt);
        imgclose = view.findViewById(R.id.imgclose);

        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dismiss();
            }
        });


        return view;
    }
}
