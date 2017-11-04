package com.example.quyet.qrappmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityRegisterBinding;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.viewmodel.ActivityRegisterViewModel;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, ActivityRegisterViewModel> {
    private String name;
    private String email;
    private String password;
    private String password2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public ActivityRegisterViewModel setViewModel() {
        return new ActivityRegisterViewModel();
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
        setUI();
        setEvent();
    }

    private void setEvent() {
        getBinding().btnLinkToLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLogin();
            }
        });
        getBinding().btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRegister();
            }

            
        });
    }

    private void sendRegister() {
        validateData();
    }

    private boolean validateData() {
        getBinding().btnRegister.setEnabled(false);
        name = getBinding().etRName.getText().toString().trim();
        email = getBinding().etREmail.getText().toString().trim();
        password = getBinding().etRPassword.getText().toString();
        password2 = getBinding().etRPasswordAgain.getText().toString();
        boolean check =true;
        if(name.length()< 5){
            check= false;
            Toast.makeText(this, "Name too short (require 5 - 100 character)", Toast.LENGTH_SHORT).show();
        }else if(password.length() < 6){
            Toast.makeText(this, "Password too short (require 6 - 20 character)", Toast.LENGTH_SHORT).show();
            check=false;
            if(password.contains(Constant.BACK_SPACE)){
                Toast.makeText(this, "Pass word can not contain back space", Toast.LENGTH_SHORT).show();
                check = false;
            }

        } else if(!password.equals(password2)){
            Toast.makeText(this, "Password must be the same", Toast.LENGTH_SHORT).show();
            check =false;

        }
        if (!check){
            getBinding().btnRegister.setEnabled(true);
            return false;
        }

        return true;
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void setUI() {
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
    }
}
