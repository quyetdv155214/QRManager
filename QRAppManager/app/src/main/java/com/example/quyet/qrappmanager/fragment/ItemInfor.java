package com.example.quyet.qrappmanager.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.FragmentItemInforBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.viewmodel.FragmentItemInforViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemInfor extends BaseFragmentDialog<FragmentItemInforBinding, FragmentItemInforViewModel> {

     private ItemViewModel itemViewModel;
    private Item item;
    public ItemInfor() {
        // Required empty public constructor
    }


    @Override
    public int getVariableId() {
        return com.example.quyet.qrappmanager.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_item_infor;
    }

    @Override
    public FragmentItemInforViewModel setViewModel() {
        return new FragmentItemInforViewModel(item);
    }

    @Override
    public void onCreateFragment(Context context) {

    }


}
