package com.example.quyet.qrappmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.icu.util.ULocale;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quyet.qrappmanager.model.CategoryType;
import com.example.quyet.qrappmanager.model.Menu;

import java.util.List;

/**
 * Created by Quyet on 02/11/2017.
 */

public class SpinAdapterMenu extends ArrayAdapter<Menu> {

    List<Menu> values;
    private Context context;

    public SpinAdapterMenu(@NonNull Context context, @LayoutRes int resource, List<Menu> list ) {
        super(context, resource);
        this.values = list;
        this.context = context;

    }


    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Menu getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(values.get(position).getMenuName());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(values.get(position).getMenuName());

        return label;
    }
}
