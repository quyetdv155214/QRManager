package com.example.quyet.qrappmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.andexert.library.RippleView;
import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityHomeBinding;
import com.example.quyet.qrappmanager.viewmodel.ActivityHomeViewModel;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, ActivityHomeViewModel> implements RippleView.OnRippleCompleteListener{

    private static final String TAG = "HomeActivity";

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
        init();
    }

    private void init() {
        setEvent();
    }

    private void setEvent() {
        getBinding().rlMenu.setOnRippleCompleteListener(this);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()){
            case R.id.rlMenu:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
        }
    }
}
