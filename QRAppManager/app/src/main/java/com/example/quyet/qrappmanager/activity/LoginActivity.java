package com.example.quyet.qrappmanager.activity;

import android.app.ProgressDialog;
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
import com.example.quyet.qrappmanager.viewmodel.ActivityHomeViewModel;
import com.example.quyet.qrappmanager.viewmodel.ActivityLoginViewModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, ActivityLoginViewModel>  {

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
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        setEvent();
        Log.d(TAG, "init: init done");
    }

    public void onLoginSuccess() {
        getBinding().rlLogin.setEnabled(true);
//        finish();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = getBinding().inputEmail.getText().toString();
        String password = getBinding().inputPassword.getText().toString();

        // TODO: Implement login code

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);

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
