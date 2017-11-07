package com.example.quyet.qrappmanager.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.quyet.qrappmanager.viewmodel.BaseViewModel;
import com.google.gson.Gson;

/**
 * Created by Khuong Duy on 9/20/2017.
 */

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {
    private T binding;
    private V viewModel;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        initDatabinding();
        onCreateActivity();
    }


    private void initDatabinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        this.viewModel = viewModel == null ? setViewModel() : viewModel;
        binding.setVariable(getVariableId(), viewModel);
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public T getBinding() {
        return binding;
    }

    public V getViewModel() {
        return viewModel;
    }

    public Gson getGson() {
        return gson;
    }

    public void hideKeyboard(EditText editText){
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    public void showKeyboard(EditText editText){
        InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    public abstract int getLayoutId();

    public abstract V setViewModel();

    public abstract int getVariableId();

    public abstract void onCreateActivity();

}
