package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by apple on 6/3/16.
 */
public class MyNewsAdaptor extends BaseAdapter {

    Context objContext;
    String[] stringsHeadline;
    int[] iconInts;

    public MyNewsAdaptor(Context objContext, String[] stringsHeadline, int[] iconInts) {
        this.objContext = objContext;
        this.stringsHeadline = stringsHeadline;
        this.iconInts = iconInts;

    } //Constructor

    @Override
    public int getCount() {
        return stringsHeadline.length;
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
        LayoutInflater layoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.my_info_listview, parent, false);

        TextView textViewNewAdaptor = (TextView) view.findViewById(R.id.textViewNewAdaptor);
        textViewNewAdaptor.setText(stringsHeadline[position]);

        ImageView imageViewNewsAdaptor = (ImageView) view.findViewById(R.id.imageViewNewsAdaptor);
        imageViewNewsAdaptor.setBackgroundResource(iconInts[position]);

        return view;



    }
}  //Main Class
