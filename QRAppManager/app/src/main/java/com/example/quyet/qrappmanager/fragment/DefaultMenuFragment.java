package com.example.quyet.qrappmanager.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.DBContext;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.activity.DetailCategoryActivity;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.FragmentDefaultMenuBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.CategoryJson;
import com.example.quyet.qrappmanager.networks.services.CategoryService;
import com.example.quyet.qrappmanager.viewmodel.FragmentDefaultMenuViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultMenuFragment extends BaseFragment<FragmentDefaultMenuBinding, FragmentDefaultMenuViewModel> {
    private static final String TAG = "Menu fragment";
    private List<ItemCategoryViewModel> list =DBContext.instance.getCategoryViewModelList();
    BaseSingleTypeRecyclerViewAdapter<ItemCategoryViewModel> adapter;
    private RelativeLayout mRoot;
    public Menu menu;
    private ProgressDialog pDialog;


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
        getCategory();
    }

    private void init() {
        mRoot = (RelativeLayout) getActivity().findViewById(R.id.rlMenuRoot);
    }

    public void getData() {
        list = new ArrayList<>();

        // dump data
        List<MenuCategory> categories = menu.getCategories();
        if (categories != null) {
            for (MenuCategory mc : categories) {
                list.add(new ItemCategoryViewModel(mc));
            }
        }


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
//            Toast.makeText(getActivity(), category.getCatename(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), DetailCategoryActivity.class);
            Gson gson = new Gson();
            String categoryGson = gson.toJson(category);
            intent.putExtra("category", categoryGson);
//            intent.putExtra("category", category );
            startActivity(intent);
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
                R.string.has_deleted, Snackbar.LENGTH_SHORT);
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void getCategory() {
        pDialog = new ProgressDialog(this.getActivity());
        pDialog.setMessage("loading...");
        pDialog.setCancelable(false);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        pDialog.show();
        CategoryService categoryService = NetContext.instance.create(CategoryService.class);

        categoryService.getCategoryMenuWithId(menu.getMenuId()).enqueue(new Callback<List<CategoryJson>>() {
            @Override
            public void onResponse(Call<List<CategoryJson>> call, Response<List<CategoryJson>> response) {
                List<CategoryJson> responseJsons = response.body();
                pDialog.dismiss();
                if(responseJsons != null){
                    if (response.code() == 200){
                        Toast.makeText(getActivity(), "get category " + menu.getMenuName(), Toast.LENGTH_SHORT).show();
                         List<ItemCategoryViewModel> list = new ArrayList<ItemCategoryViewModel>();
                        for (CategoryJson c :
                                responseJsons) {
                            list.add(new ItemCategoryViewModel(new MenuCategory(c.getCateName(),new ArrayList<Item>(), c.getCateId(), c.getMenuId(), c.getCateType())));
                        }

                        DBContext.instance.setCategoryViewModelList(list);
                        adapter.deleteAll();
                        adapter.addAll(list);
                        getBinding().rvCategory.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<CategoryJson>> call, Throwable t) {
                pDialog.dismiss();

            }
        });
    }






}
