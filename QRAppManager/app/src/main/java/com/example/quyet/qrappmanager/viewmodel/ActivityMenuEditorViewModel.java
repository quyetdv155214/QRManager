package com.example.quyet.qrappmanager.viewmodel;

import android.content.Context;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khuong Duy on 11/6/2017.
 */

public class ActivityMenuEditorViewModel extends BaseViewModel {
    private BaseSingleTypeRecyclerViewAdapter<CategoryViewModel> adapter;
    private boolean isLoading;
    private MenuJson menuJson;
    private Context context;


    public ActivityMenuEditorViewModel(Context context) {
        this.context = context;
        this.adapter = new BaseSingleTypeRecyclerViewAdapter<>(context, R.layout.item_category);

    }

    public BaseSingleTypeRecyclerViewAdapter<CategoryViewModel> getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseSingleTypeRecyclerViewAdapter<CategoryViewModel> adapter) {
        this.adapter = adapter;
    }
    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public MenuJson getMenuJson() {
        return menuJson;
    }

    public void setMenuJson(MenuJson menuJson) {
        this.menuJson = menuJson;
    }
}
