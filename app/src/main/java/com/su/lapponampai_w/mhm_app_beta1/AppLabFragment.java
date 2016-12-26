package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class AppLabFragment extends Fragment {

    private Context globalContext = null;
    ImageButton imageButton;
    ListView listView;
    TextView textViewBackToMain;
    Fragment fragmentAppDoctorFragment;


    public AppLabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_lab, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindWidget(view);

        clickBackToMain(view);

        clickImageButton(view);

    }

    private void clickImageButton(View v) {

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PopUpAppLabActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickBackToMain(View view) {

        textViewBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void bindWidget(View v) {
        listView = (ListView) v.findViewById(R.id.listViewAppointment);
        imageButton = (ImageButton) v.findViewById(R.id.imageButton11);
        textViewBackToMain = (TextView) v.findViewById(R.id.textView106);
    }


}
