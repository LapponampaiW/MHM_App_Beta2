package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by apple on 12/27/16.
 */

public class MyAdaptorAppLab extends BaseAdapter{
    Context context;
    String[] stringsDate, stringsTime,stringsLab ,stringsNote;

    public MyAdaptorAppLab(Context context, String[] stringsDate, String[] stringsTime, String[] stringsLab, String[] stringsNote) {
        this.context = context;
        this.stringsDate = stringsDate;
        this.stringsTime = stringsTime;
        this.stringsLab = stringsLab;
        this.stringsNote = stringsNote;
    }

    @Override
    public int getCount() {
        return stringsDate.length;
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
        View view = layoutInflater.inflate(R.layout.my_app_lab_listview, parent, false);

        TextView textViewDate = (TextView) view.findViewById(R.id.textView113);
        textViewDate.setText(stringsDate[position]);

        TextView textViewTime = (TextView) view.findViewById(R.id.textView114);
        textViewTime.setText(stringsTime[position]);

        TextView textViewLab = (TextView) view.findViewById(R.id.textView112);
        textViewLab.setText(stringsLab[position]);

        TextView textViewNote = (TextView) view.findViewById(R.id.textView115);
        textViewNote.setText(stringsNote[position]);

        return view;
    }
}
