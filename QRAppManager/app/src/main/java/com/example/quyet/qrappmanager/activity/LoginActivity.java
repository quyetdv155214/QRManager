package com.example.quyet.qrappmanager.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityLoginBinding;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.LoginBodyJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.LoginResponseJson;
import com.example.quyet.qrappmanager.networks.services.LoginService;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.viewmodel.ActivityHomeViewModel;
import com.example.quyet.qrappmanager.viewmodel.ActivityLoginViewModel;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, ActivityLoginViewModel>  {
    public static String managerId ="thisisdemoid_3";
    public String resID;
    private static final String TAG = "loginActivity";

    private ProgressDialog pDialog;
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public ActivityLoginViewModel setViewModel() {
        return new ActivityLoginViewModel();
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        Log.d(TAG, "onCreateActivity: ");
        init();
    }
    public void init(){
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);

        setEvent();
        setUI();
        Log.d(TAG, "init: init done");
    }

    private void setUI() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Logging...");
        pDialog.setCancelable(false);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    public void onLoginSuccess(String managerId) {
//        getBinding().rlLogin.setEnabled(true);
        Toast.makeText(this, "manager id "+ managerId, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        getBinding().rlLogin.setEnabled(true);
    }
    public boolean validate() {
        Toast.makeText(this, "validate", Toast.LENGTH_SHORT).show();
        boolean valid = true;

        String email = getBinding().inputEmail.getText().toString();
        String password = getBinding().inputPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            getBinding().inputEmail.setError("enter a valid email address");
            valid = false;
        } else {
            getBinding().inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            getBinding().inputPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            getBinding().inputPassword.setError(null);
        }

        return valid;
    }

//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
    private void setEvent(){
//        getBinding().rlLogin.setOnRippleCompleteListener(this);
        getBinding().rlLogin.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(RippleView rippleView) {
                getBinding().btnLogin.setEnabled(false);
                login();
            }
        });

        getBinding().btnOrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToRegisterScreen();
            }
        });

    }

    private void changeToRegisterScreen() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    private void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        pDialog.show();


        String email = getBinding().inputEmail.getText().toString();
        String password = getBinding().inputPassword.getText().toString();
//        String email = "myemail3@gmail.com";
//        String password = "123456";


        // TODO: Implement login code


        LoginService loginService = NetContext.instance.create(LoginService.class);
        MediaType jsonType = MediaType.parse("application/json");

        final String loginJson = (new Gson()).toJson(new LoginBodyJson(email, password));
        Log.d(TAG, "loginJson: "+loginJson.toString());
        final RequestBody loginBody = RequestBody.create(jsonType, loginJson);
        Log.d(TAG, "loginBody: " +loginBody.toString());
        Call<LoginResponseJson> loginCall = loginService.login(loginBody);
        Log.d(TAG, "loginCall: " + loginCall.toString());

        loginCall.enqueue(new Callback<LoginResponseJson>() {
            @Override
            public void onResponse(Call<LoginResponseJson> call, Response<LoginResponseJson> response) {
                pDialog.dismiss();

                LoginResponseJson loginResponseJson = response.body();
                Log.d(TAG, "onResponse: body" + loginResponseJson);
                if(loginResponseJson !=null){
                    if(response.code() ==200){
                        managerId = loginResponseJson.getManagerId();
                        Log.d(TAG, "onResponse: login managerid "+ managerId);
                        onLoginSuccess(managerId);
                    }
                }else {
                    Toast.makeText(LoginActivity.this, Constant.LOGIN_WRONG_ACCOUNT_MESS, Toast.LENGTH_SHORT).show();
                    onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseJson> call, Throwable t) {
                pDialog.dismiss();
                onLoginFailed();
                Log.d(TAG, String.format("onFailure: %s", t));
            }
        });


    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
