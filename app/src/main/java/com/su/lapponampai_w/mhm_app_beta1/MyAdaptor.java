package com.su.lapponampai_w.mhm_app_beta1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by apple on 4/29/16.
 */
public class MyAdaptor extends BaseAdapter {

    Context objContext;
    String[] tradenameStrings, gname12Strings, gname34Strings;
    int[] iconInts;

    public MyAdaptor(Context objContext, String[] tradenameStrings, String[] gname12Strings, String[] gname34Strings, int[] iconInts) {
        this.objContext = objContext;
        this.tradenameStrings = tradenameStrings;
        this.gname12Strings = gname12Strings;
        this.gname34Strings = gname34Strings;
        this.iconInts = iconInts;
    }

    @Override
    public int getCount() {
        return tradenameStrings.length;
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
        View view = layoutInflater.inflate(R.layout.my_filter_listview, parent, false);

        TextView tradenameTextView = (TextView) view.findViewById(R.id.textView_Tradename);
        tradenameTextView.setText(tradenameStrings[position]);

        TextView gname12TextView = (TextView) view.findViewById(R.id.textView_Gname12);
        gname12TextView.setText(gname12Strings[position]);

        TextView gname34TextView = (TextView) view.findViewById(R.id.textView_Gname34);
        gname34TextView.setText(gname34Strings[position]);

        ImageView iconImageView = (ImageView) view.findViewById(R.id.imageViewicon);
        iconImageView.setBackgroundResource(iconInts[position]);

        return view;
    }
}
