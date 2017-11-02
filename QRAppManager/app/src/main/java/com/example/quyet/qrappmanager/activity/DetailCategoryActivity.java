package com.example.quyet.qrappmanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityDetailCategoryBinding;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailCategoryViewModel;

public class DetailCategoryActivity extends BaseActivity<ActivityDetailCategoryBinding, ActivityDetailCategoryViewModel> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_category;
    }

    @Override
    public ActivityDetailCategoryViewModel setViewModel() {
        return new ActivityDetailCategoryViewModel();
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
    }


    public class ItemListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(MenuCategory category) {
            Toast.makeText(DetailCategoryActivity.this, "click item", Toast.LENGTH_SHORT).show();
        }

    }
}
