package com.example.quyet.qrappmanager.viewmodel;

import android.databinding.Bindable;

/**
 * Created by Quyet on 31/10/2017.
 */

public class ActivityMenuViewModel extends BaseViewModel {
    private boolean isLoading;

    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}
