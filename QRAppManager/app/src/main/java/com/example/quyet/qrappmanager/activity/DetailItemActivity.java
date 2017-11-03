package com.example.quyet.qrappmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityDetailItemBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailItemViewModel;
import com.google.gson.Gson;

public class DetailItemActivity extends BaseActivity<ActivityDetailItemBinding, ActivityDetailItemViewModel> implements View.OnClickListener{
    private Item item;
    private boolean editMode = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_item;
    }

    @Override
    public ActivityDetailItemViewModel setViewModel() {
        return new ActivityDetailItemViewModel();
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
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
        getData();
        setView();
        setEvent();
    }

    private void setEvent() {
        getBinding().ivEditItem.setOnClickListener(this);
        getBinding().ivDelItem.setOnClickListener(this);
        getBinding().ivDefaultAddCateBack.setOnClickListener(this);
    }

    private void setView() {
        getBinding().etItemName.setText(item.getItemName());
        getBinding().etItemPrice.setText(item.getItemPrice() +"");
        getBinding().etItemDes.setText(item.getItemDes());
    }

    public void getData() {
//        getIntent().getExtras("itemJson");
        Gson gson = new Gson();
        item = gson.fromJson(getIntent().getStringExtra("itemJson"), Item.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivEdit:
                if(editMode){
                    getBinding().etItemName.setEnabled(true);
                    getBinding().etItemPrice.setEnabled(true);
                    getBinding().etItemDes.setEnabled(true);
                    editMode = true;
                }else{
                    getBinding().etItemName.setEnabled(false);
                    getBinding().etItemPrice.setEnabled(false);
                    getBinding().etItemDes.setEnabled(false);
                    editMode = false;
                    updateItem();
                }
                break;
        }
    }

    private void updateItem() {
        Toast.makeText(this, "item has updated", Toast.LENGTH_SHORT).show();
    }
}
