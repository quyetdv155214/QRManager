package com.example.quyet.qrappmanager.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.SpinAdapterCateType;
import com.example.quyet.qrappmanager.adapter.SpinAdapterMenu;
import com.example.quyet.qrappmanager.databinding.ActivityAddCategoryBinding;
import com.example.quyet.qrappmanager.model.CategoryType;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ActivityAddCategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class AddCategoryActivity extends BaseActivity<ActivityAddCategoryBinding, ActivityAddCategoryViewModel> implements View.OnClickListener{

    private static final String TAG = "Add Categoty";
    private List<CategoryType> typeList;
    private List<Menu> menuList;
    private MenuCategory cateData;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_category;
    }

    @Override
    public ActivityAddCategoryViewModel setViewModel() {
        return new ActivityAddCategoryViewModel();
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
        setView();
        setEvent();
        Log.d(TAG, "init: ");
    }

    private void setView() {
        typeList = getCateType();
        menuList = getMenuList();
//
        getBinding().spCateMenu.setAdapter(new SpinAdapterMenu(this, R.layout.simple_spinner_dropdown_item, menuList));
        getBinding().spCateType.setAdapter(new SpinAdapterCateType(this, R.layout.simple_spinner_dropdown_item, typeList));
    }

    private void setEvent() {
        getBinding().ivbtnDel.setOnClickListener(this);
        getBinding().ivbtnOK.setOnClickListener(this);
        getBinding().ivDefaultAddCateBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbtnDel:
                showDiscardDialog();

            case R.id.ivbtnOK:
                MenuCategory cate = getCateData();
                saveToServer(cate);
                onBackPressed();
                break;
            case R.id.ivDefaultAddCateBack : showDiscardDialog();
                break;
        }
    }

    private void saveToServer(MenuCategory cate) {

    }

    public void showDiscardDialog(){
        if(getBinding().etCateTitle.getText().toString().equals("")){
            onBackPressed();
            return;
        }
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.cast_ic_notification_small_icon)
                .setTitle("Discard")
                .setMessage("You will lost input")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();

    }

    public List<CategoryType> getCateType() {
        List<CategoryType> list = new ArrayList<>();

        // dumdata
        list.add(new CategoryType("Food", "0001"));
        list.add(new CategoryType("Food 2", "0002"));
        list.add(new CategoryType("Food 3" , "0003"));
        //
        return list;
    }

    public MenuCategory getCateData() {
        String cateName = getBinding().etCateTitle.getText().toString();
        String cateType = ((CategoryType)getBinding().spCateType.getSelectedItem()).getTypeId();
        String cateMenu = ((Menu)getBinding().spCateMenu.getSelectedItem()).getMenuId();
        String cateID = UUID.randomUUID().toString();;
        MenuCategory category  = new MenuCategory(cateName, cateID, cateMenu, cateType);
        Toast.makeText(this, category.getCatename() + " " + category.getCateID() + " " + category.getCateType() +" " + category.getMenuId(), Toast.LENGTH_SHORT).show();
        return category;
    }

    public List<Menu> getMenuList() {
        List<Menu> menus = new ArrayList<>();

        menus.add(new Menu("Menu 1", "menu001", "res1", "delicous menu"));
        menus.add(new Menu("Menu 2", "menu002", "res1", "delicous menu"));
        menus.add(new Menu("Menu 3", "menu003", "res1", "delicous menu"));
        return menus;
    }
}
