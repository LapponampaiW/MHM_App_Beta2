package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AppDoctorFragment extends Fragment {

    private Context globalContext = null;


    public AppDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_doctor, container, false);
        globalContext = this.getActivity();

        return view;


    }

}
