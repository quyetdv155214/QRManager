package com.example.quyet.qrappmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityHomeBinding;
import com.example.quyet.qrappmanager.viewmodel.ActivityHomeViewModel;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, ActivityHomeViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public ActivityHomeViewModel setViewModel() {
        return new ActivityHomeViewModel();
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onCreateActivity() {

    }
}
