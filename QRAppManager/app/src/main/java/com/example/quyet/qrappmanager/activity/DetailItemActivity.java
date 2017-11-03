package com.example.quyet.qrappmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityDetailItemBinding;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailItemViewModel;

public class DetailItemActivity extends BaseActivity<ActivityDetailItemBinding, ActivityDetailItemViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_item;
    }

    @Override
    public ActivityDetailItemViewModel setViewModel() {
        return new ActivityDetailItemViewModel();
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        init();
    }

    private void init() {

    }
}
