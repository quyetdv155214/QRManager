package com.example.quyet.qrappmanager.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    BaseSingleTypeRecyclerViewAdapter<ItemCategoryViewModel> adapter;
    private RelativeLayout mRoot;

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
        init();
        getData();
        setUpRecycleView(context);
    }

    private void init() {
        mRoot = (RelativeLayout) getActivity().findViewById(R.id.rlMenuRoot);
    }

    public void getData() {
        list = new ArrayList<>();
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 1", new ArrayList<Item>(), "1", "Menu0001", "001")));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 2", new ArrayList<Item>(), "2", "Menu0001", "001")));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 3", new ArrayList<Item>(), "3", "Menu0001", "001")));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 4", new ArrayList<Item>(), "4","Menu0001", "001")));
        list.add(new ItemCategoryViewModel(new MenuCategory("cate 5", new ArrayList<Item>(), "5","Menu0001", "001")));
    }

    private void setUpRecycleView(Context context) {
        adapter = new BaseSingleTypeRecyclerViewAdapter<>(context, R.layout.category_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter.addAll(list);
        adapter.setPresenter(new CategoryListener());


        getBinding().rvCategory.setLayoutManager(layoutManager);
        getBinding().rvCategory.setAdapter(adapter);


    }

    public class CategoryListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(MenuCategory category) {
            Toast.makeText(getActivity(), category.getCatename(), Toast.LENGTH_SHORT).show();

        }

        public void onEditClick(MenuCategory category) {
            Toast.makeText(getActivity(), "click the pen", Toast.LENGTH_SHORT).show();

        }

        public void onDeleteClick(final MenuCategory category) {
            Toast.makeText(getActivity(), "click delete", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.cast_ic_notification_small_icon)
                    .setTitle("delete")
                    .setMessage("delete this category")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete(category);
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();

        }

    }

    private void delete(MenuCategory category) {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCateID().equalsIgnoreCase(category.getCateID())) {
                adapter.remove(i);
                list.remove(i);
                Toast.makeText(getActivity(), "delete " + i + " category name : " + category.getCatename(), Toast.LENGTH_SHORT).show();
                showUndoMess(category.getCatename());

                break;
            }
        }

    }


    private void showUndoMess(String catename) {
        Snackbar mySnackbar = Snackbar.make(getActivity().findViewById(R.id.rlMenuRoot),
                R.string.has_deleted , Snackbar.LENGTH_SHORT);
        mySnackbar.setAction(R.string.undo, new MyUndoListener());
        mySnackbar.show();

    }

    public class MyUndoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(), "undo", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "onClick: undo delete");

        }
    }

}
