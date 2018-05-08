package com.example.andhikaeffendy.projectorang;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Andhika Effendy on 4/19/2018.
 */

public class BerandaFragment extends Fragment {
    private ViewPager mSlidePager;

    public BerandaFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_main, container, false);

        LinearLayout panti = (LinearLayout) view.findViewById(R.id.panti_btn);
        panti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DaftarPantiActivity.class);
                startActivity(intent);
            }
        });

        mSlidePager = (ViewPager) view.findViewById(R.id.slider);
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity());
        mSlidePager.setAdapter(sliderAdapter);

        return view;
    }
}
