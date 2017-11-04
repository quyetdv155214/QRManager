package com.example.quyet.qrappmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityAddItemBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.viewmodel.ActivityAddItemViewModel;



public class AddItemActivity extends BaseActivity<ActivityAddItemBinding, ActivityAddItemViewModel> implements View.OnClickListener{


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
        setEvent();
        getData();
    }

    private void setEvent() {
        getBinding().ivAddOK.setOnClickListener(this);
        getBinding().ivDefaultAddItemback.setOnClickListener(this);
    }

    private Item getData() {
        String itemName = getBinding().etItemName.getText().toString();
        float itemPrice= Float.parseFloat(getBinding().etItemPrice.getText().toString());
        String itemDes= getBinding().etItemDes.getText().toString();
        String itemInfo = getBinding().etIteminfo.getText().toString();
        return null;
    }

    private void setUpView() {
        getBinding().etItemPrice.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivAddOK :
                addItem();
                break;
            case R.id.ivDefaultAddItemback :
                onBackPressed();
                break;
        }
    }

    private void addItem() {
        Item item = getData();
    }
}
