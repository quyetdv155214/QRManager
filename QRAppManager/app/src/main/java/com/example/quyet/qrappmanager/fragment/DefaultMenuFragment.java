package com.example.quyet.qrappmanager.fragment;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.activity.MenuActivity;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.FragmentDefaultMenuBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.FragmentDefaultMenuViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultMenuFragment extends BaseFragment<FragmentDefaultMenuBinding, FragmentDefaultMenuViewModel> {
    private static final String TAG = "Menu fragment";
    private List<ItemCategoryViewModel> list;

    public DefaultMenuFragment() {

    }




    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_default_menu;
    }

    @Override
    public FragmentDefaultMenuViewModel setViewModel() {
        return new FragmentDefaultMenuViewModel();
    }

    @Override
    public void onCreateFragment(Context context) {
        getData();
        setUpRecycleView(context);
    }

    public void getData() {
        list = new ArrayList<>();
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 1", new ArrayList<Item>())));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 2", new ArrayList<Item>())));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 3", new ArrayList<Item>())));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 4", new ArrayList<Item>())));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 5", new ArrayList<Item>())));
    }
    private void setUpRecycleView(Context context) {
        BaseSingleTypeRecyclerViewAdapter<ItemCategoryViewModel> adapter = new BaseSingleTypeRecyclerViewAdapter<>(context, R.layout.category_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter.addAll(list);
        adapter.setPresenter(new CategoryListener());
        if(layoutManager == null){
            Toast.makeText(context, "layout null", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            getBinding().rvCategory.setLayoutManager(layoutManager);
            getBinding().rvCategory.setAdapter(adapter);
        }catch (Exception e){
            Log.d(TAG, "setUpRecycleView: " +e.getMessage());
        }


    }

    public class CategoryListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(MenuCategory category) {
            Toast.makeText(getActivity(), category.getCatename(), Toast.LENGTH_SHORT).show();

        }
        public void onEditClick(MenuCategory category){
            Toast.makeText(getActivity(), "click the pen", Toast.LENGTH_SHORT).show();

        }
        public void onDeleteClick(MenuCategory category){
            Toast.makeText(getActivity(), "click delete", Toast.LENGTH_SHORT).show();

        }

    }

}
