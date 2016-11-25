package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by apple on 11/25/16.
 */

public class MyAdaptorChangeAppearance extends BaseAdapter {

    Context context;
    int[] intsIcon;

    public MyAdaptorChangeAppearance(Context context, int[] intsIcon) {
        this.context = context;
        this.intsIcon = intsIcon;
    }


    @Override
    public int getCount() {
        return intsIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_change_appearance_listview, parent, false);


        ImageView imageView = (ImageView) view.findViewById(R.id.imageChangeAppearance);
        imageView.setBackgroundResource(intsIcon[position]);

        return view;

    }
}
