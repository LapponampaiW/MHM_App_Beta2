package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by apple on 7/15/16.
 */
public class MyAdaptorLab extends BaseAdapter{

    Context context;
    String[] strings1;
    String[] stringsLabDate;

    public MyAdaptorLab(Context context, String[] stringsLabDate, String[] strings1) {
        this.context = context;
        this.stringsLabDate = stringsLabDate;
        this.strings1 = strings1;


    }

    @Override
    public int getCount() {
        return stringsLabDate.length;
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
        View view = layoutInflater.inflate(R.layout.my_lab_listview, parent, false);

        TextView textViewLabDate = (TextView) view.findViewById(R.id.textView112);
        TextView textView1 = (TextView) view.findViewById(R.id.textView114);



        textViewLabDate.setText(stringsLabDate[position]);
        textView1.setText(strings1[position]);



        return view;
    }
}
