package com.example.quyet.qrappmanager.fragment;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.qrappmanager.viewmodel.BaseViewModel;


/**
 * Created by Khuong Duy on 9/28/2017.
 */

public abstract class BaseFragmentDialog<T extends ViewDataBinding, V extends BaseViewModel> extends DialogFragment {
    private T binding;
    private V viewModel;
    private String fragmentTitle;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = binding.getRoot();
        this.viewModel = viewModel == null ? setViewModel() : viewModel;
        binding.setVariable(getVariableId(), viewModel);
        onCreateFragment(getContext());
        return view;
    }

    public abstract int getVariableId();

    public abstract int getLayoutId();

    public abstract V setViewModel();

    public abstract void onCreateFragment(Context context);


    public String getFragmentTitle() {
        return fragmentTitle;
    }

    public void setFragmentTitle(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }

    public T getBinding() {
        return binding;
    }

    public V getViewModel() {
        return viewModel;
    }

}
