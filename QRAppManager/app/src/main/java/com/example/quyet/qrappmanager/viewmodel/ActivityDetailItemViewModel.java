package com.example.quyet.qrappmanager.viewmodel;

/**
 * Created by Quyet on 04/11/2017.
 */

public class ActivityDetailItemViewModel extends BaseViewModel  {
    private ItemViewModel itemViewModel;

    public ItemViewModel getItemViewModel() {
        return itemViewModel;
    }

    public void setItemViewModel(ItemViewModel itemViewModel) {
        this.itemViewModel = itemViewModel;
        notifyChange();
    }
}
