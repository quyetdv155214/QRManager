package com.example.quyet.qrappmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quyet.qrappmanager.model.CategoryType;

import java.util.List;

/**
 * Created by Quyet on 02/11/2017.
 */

public class SpinAdapterCateType extends ArrayAdapter<CategoryType> {
    List<CategoryType> values;
    private Context context;

    public SpinAdapterCateType(@NonNull Context context, @LayoutRes int resource, List<CategoryType> categoryTypes) {
        super(context, resource);
        this.values = categoryTypes;
        this.context = context;
    }
    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public CategoryType getItem(int position){
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
        label.setText(values.get(position).getNameOfType());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(18);
        label.setText(values.get(position).getNameOfType());

        return label;
    }
}
