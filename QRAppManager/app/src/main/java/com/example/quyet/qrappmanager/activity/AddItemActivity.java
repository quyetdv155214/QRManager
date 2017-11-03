package com.example.quyet.qrappmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityAddItemBinding;
import com.example.quyet.qrappmanager.viewmodel.ActivityAddItemViewModel;



public class AddItemActivity extends BaseActivity<ActivityAddItemBinding, ActivityAddItemViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_item;
    }

    @Override
    public ActivityAddItemViewModel setViewModel() {
        return new ActivityAddItemViewModel();
    }

    @Override
    public int getVariableId() {
        return com.example.quyet.qrappmanager.BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        init();
    }

    private void init() {
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
        setUpView();
    }

    private void setUpView() {
        getBinding().etItemPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }
}
