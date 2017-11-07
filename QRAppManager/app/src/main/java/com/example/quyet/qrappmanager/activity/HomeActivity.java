package com.example.quyet.qrappmanager.activity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.andexert.library.RippleView;
import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.DBContext;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityHomeBinding;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuResponseJson;
import com.example.quyet.qrappmanager.networks.services.MenuService;
import com.example.quyet.qrappmanager.viewmodel.ActivityHomeViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, ActivityHomeViewModel> implements RippleView.OnRippleCompleteListener{

    private static final String TAG = "HomeActivity";
    private ProgressDialog pDialog;

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
        getBinding().btnGenQr.setOnRippleCompleteListener(this);
        getBinding().btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateMenuActivity.class);
                startActivity(intent);
            }
        });
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
            case R.id.btnGenQr:
                Intent intent1 = new Intent(this, testActivity.class);
                startActivity(intent1);


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
