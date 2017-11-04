package com.example.quyet.qrappmanager.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityRegisterBinding;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.RegisterBodyJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.RegisterResponseJson;
import com.example.quyet.qrappmanager.networks.services.RegisterService;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.viewmodel.ActivityRegisterViewModel;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, ActivityRegisterViewModel> {
    private static final String TAG = "RegisterActivity";
    private String name;
    private String email;
    private String password;
    private String password2;
    private ProgressDialog pDialog;
    RegisterResponseJson responseJson;

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
        if (!validateData()){
            return;
        }
        RegisterService registerService = NetContext.instance.create(RegisterService.class);

        MediaType jsonType = MediaType.parse("application/json");

        final String registerJson = (new Gson().toJson(new RegisterBodyJson(name, email, password)));

        final RequestBody registerBody = RequestBody.create(jsonType, registerJson);
        // Create Call
        Call<RegisterResponseJson> regisCall = registerService.register(registerBody);

        regisCall.enqueue(new Callback<RegisterResponseJson>() {
            @Override
            public void onResponse(Call<RegisterResponseJson> call, Response<RegisterResponseJson> response) {
                pDialog.dismiss();
                responseJson = response.body();
                if (response.code() == 200) {
                    Toast.makeText(RegisterActivity.this, Constant.REGISTER_SUCCESS_MESS, Toast.LENGTH_SHORT).show();
                    onSuccess();
                }
                if (response.code() == 400) {
                    onFaild();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseJson> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(RegisterActivity.this, String.format("onFailure cause : %s, message : %s",
                        t.getCause(), t.getMessage()), Toast.LENGTH_LONG).show();
                Log.d(TAG, String.format(String.format("onFailure,  register fail " +
                        "message : %s ", t.getMessage())));
            }
        });


    }
    public void onSuccess(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void onFaild(){
        getBinding().btnRegister.setEnabled(true);
    }

    private boolean validateData() {
        getBinding().btnRegister.setEnabled(false);
        name = getBinding().etRName.getText().toString().trim();
        email = getBinding().etREmail.getText().toString().trim();
        password = getBinding().etRPassword.getText().toString();
        password2 = getBinding().etRPasswordAgain.getText().toString();
        boolean check =true;
        if(name.length() < 5 || name.isEmpty()){
            check= false;
            getBinding().etRName.setError("Name too short (require 5 - 100 character)");
        }else if(email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            getBinding().etREmail.setError("Enter valid email !");
        }
        else if(password.length() < 6){
            getBinding().etRPassword.setError("Password too short (require 6 - 20 character)");
            check=false;
            if(password.contains(Constant.BACK_SPACE)){
                getBinding().etRPassword.setError("Pass word can not contain back space");
                check = false;
            }
        } else if(!password.equals(password2)){
            getBinding().etRPasswordAgain.setError("Password must be the same");
            check =false;

        }
        if (!check){
            getBinding().btnRegister.setEnabled(true);
            return false;
        }
        getBinding().btnRegister.setEnabled(true);
        return true;
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void setUI() {
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
        pDialog =  new ProgressDialog(this);
        pDialog.setMessage("Waiting...");
        pDialog.setCancelable(false);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
}
