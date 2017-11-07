package com.example.quyet.qrappmanager.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityDetailItemBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailItemViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailItemActivity extends BaseActivity<ActivityDetailItemBinding, ActivityDetailItemViewModel> implements View.OnClickListener {

    private boolean isEditing = false;
    private boolean isAdd;

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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        getData();
        setEvent();
    }

    private void setEvent() {
        getBinding().ivDefaultItemDetailBack.setOnClickListener(this);
        getBinding().ivEditItem.setOnClickListener(this);
        getBinding().ivDelItem.setOnClickListener(this);
    }

    public void getData() {
        getViewModel().setItemViewModel(getGson().fromJson(getIntent().getStringExtra(Constant.PASS_ITEMODEL), ItemViewModel.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivEditItem:
                if (isEditing){
                    setEditing(false);
                }else {
                    setEditing(true);
                }
                break;
            case R.id.ivDefaultItemDetailBack:
                onBackPressed();
                break;
            case R.id.ivDelItem:
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_delete_forever_accent_24dp)
                        .setTitle("Delete item "+getViewModel().getItemViewModel().getItemName())
                        .setMessage("Are you sure you want delete this Item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DetailItemActivity.this, "Delete", Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
    }

    private void setEditing(boolean editing) {
        if(!editing){
            isEditing = false;
            getBinding().etItemName.setEnabled(false);
            getBinding().etItemDes.setEnabled(false);
            getBinding().etIteminfo.setEnabled(false);
            getBinding().etItemPrice.setEnabled(false);
            getBinding().ivEditItem.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit_white_24dp));
        }else {
            isEditing = true;
            getBinding().etItemName.setEnabled(true);
            getBinding().etItemDes.setEnabled(true);
            getBinding().etIteminfo.setEnabled(true);
            getBinding().etItemPrice.setEnabled(true);
            getBinding().ivEditItem.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit_black_24dp));
        }
    }

    private void deleteItem() {
        Toast.makeText(this, "deleteItem", Toast.LENGTH_SHORT).show();
    }

    private void updateItem() {
        Toast.makeText(this, "item has updated", Toast.LENGTH_SHORT).show();
    }
}
