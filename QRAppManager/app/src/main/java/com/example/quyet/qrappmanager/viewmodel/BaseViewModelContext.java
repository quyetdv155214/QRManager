package com.example.quyet.qrappmanager.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import java.util.Observable;

/**
 * Created by Khuong Duy on 11/6/2017.
 */

public class BaseViewModelContext extends BaseObservable {
    private Context context;

    public BaseViewModelContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
