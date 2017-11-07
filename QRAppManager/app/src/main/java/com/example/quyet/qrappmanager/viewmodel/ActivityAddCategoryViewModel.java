package com.example.quyet.qrappmanager.viewmodel;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by Quyet on 02/11/2017.
 */

public class ActivityAddCategoryViewModel extends BaseViewModel {
    private boolean isLoading = false;

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }
}
