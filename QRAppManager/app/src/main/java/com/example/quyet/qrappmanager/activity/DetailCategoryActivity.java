package com.example.quyet.qrappmanager.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityDetailCategoryBinding;
import com.example.quyet.qrappmanager.fragment.DefaultMenuFragment;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailCategoryViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailCategoryActivity extends BaseActivity<ActivityDetailCategoryBinding, ActivityDetailCategoryViewModel> implements View.OnClickListener {
    BaseSingleTypeRecyclerViewAdapter<ItemViewModel> adapter;
    private List<ItemViewModel> list = new ArrayList<>();
    private boolean editMode = false;

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
        getData();
        setEvent();
        setUpRecycleView(this);
    }

    private void setEvent() {
        getBinding().ivEdit.setOnClickListener(this);
        getBinding().ivbtnDel.setOnClickListener(this);
        getBinding().ivDefaultAddCateBack.setOnClickListener(this);
    }

    private void getData() {
        list.add(new ItemViewModel(new Item("mon thit 1", 23, 10, "so delicous")));
        list.add(new ItemViewModel(new Item("mon thit 2", 23, 10, "so delicous")));
        list.add(new ItemViewModel(new Item("mon thit 3", 23, 10, "so delicous")));
        list.add(new ItemViewModel(new Item("mon thit 4", 23, 10, "so delicous")));
        list.add(new ItemViewModel(new Item("mon thit 5", 23, 10, "so delicous")));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivEdit :
                getBinding().etCateTitle.setEnabled(true);
                getBinding().spCateMenu.setEnabled(true);
                getBinding().spCateType.setEnabled(true);
                getBinding().ivEdit.setImageResource(R.drawable.ic_save_white_24dp);
                editMode =false;

                if(editMode){

                }

                break;
            case R.id.ivDefaultAddCateBack : onBackPressed();
                break;
            case R.id.ivbtnDel :
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
        }
    }


    public class ItemListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(Item item) {
            Toast.makeText(DetailCategoryActivity.this, "click item", Toast.LENGTH_SHORT).show();
        }
        public void onEditClick(Item item){
            Toast.makeText(DetailCategoryActivity.this, "click edit", Toast.LENGTH_SHORT).show();

        }

    }
    private void setUpRecycleView(Context context) {
        adapter = new BaseSingleTypeRecyclerViewAdapter<>(context, R.layout.default_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter.addAll(list);
        adapter.setPresenter(new ItemListener());


        getBinding().rvItem.setLayoutManager(layoutManager);
        getBinding().rvItem.setAdapter(adapter);
    }

}
