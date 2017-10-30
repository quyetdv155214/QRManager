package com.example.quyet.qrappmanager.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quyet.qrappmanager.viewmodel.BaseViewModel;

/**
 * Created by Khuong Duy on 9/25/2017.
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {
    private T binding;
    private V viewModel;
    private String fragmentTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        View view = binding.getRoot();
        this.viewModel = viewModel == null ? setViewModel() : viewModel;
        binding.setVariable(getVariableId(), viewModel);
        onCreateFragment(container.getContext());
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
