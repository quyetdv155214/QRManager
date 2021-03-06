package com.example.quyet.qrappmanager.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Khuong Duy on 9/26/2017.
 */

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T binding;

    public BaseViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
