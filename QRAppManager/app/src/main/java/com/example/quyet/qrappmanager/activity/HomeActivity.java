package com.example.quyet.qrappmanager.activity;

import android.app.Activity;
import android.app.ActivityManager;
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
        getBinding().rlAccount.setOnRippleCompleteListener(this);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()){
            case R.id.rlMenu:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.rlAccount :
                if(hasLogin()){
                    gotoAccountScreen();
                }else{
                    Intent loginIntent = new Intent(this, LoginActivity.class);
//        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }
                break;

        }
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        Log.d(TAG, "gotoLogin: ");
    }

    private void gotoAccountScreen() {

    }

    private boolean hasLogin() {

        return false;
    }
}
