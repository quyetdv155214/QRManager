package com.example.quyet.qrappmanager.viewmodel;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by Quyet on 02/11/2017.
 */

public class ActivityDetailCategoryViewModel extends BaseViewModel {
    private CategoryViewModel categoryViewModel;
    private boolean isLoading =false;
    @Bindable
    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR.loading);
    }

    public CategoryViewModel getCategoryViewModel() {
        return categoryViewModel;
    }

    public void setCategoryViewModel(CategoryViewModel categoryViewModel) {
        this.categoryViewModel = categoryViewModel;
    }
}
