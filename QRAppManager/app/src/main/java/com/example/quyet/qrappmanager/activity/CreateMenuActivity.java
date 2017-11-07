package com.example.quyet.qrappmanager.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityCreateMenuBinding;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuResponseJson;
import com.example.quyet.qrappmanager.networks.services.MenuService;
import com.example.quyet.qrappmanager.viewmodel.ActivityCreateMenuViewModel;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.quyet.qrappmanager.activity.LoginActivity.managerId;

public class CreateMenuActivity extends BaseActivity<ActivityCreateMenuBinding, ActivityCreateMenuViewModel> {

    private static final String TAG = "AddMenuAcitvity";
    private ProgressDialog pDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_menu;
    }

    @Override
    public ActivityCreateMenuViewModel setViewModel() {
        return new ActivityCreateMenuViewModel();
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
        setEvent();
        setUI();
    }

    private void setUI() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Logging...");
        pDialog.setCancelable(false);
        pDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    private void setEvent() {
        getBinding().ivAddMenuOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                addMenu();
            }
        });
        getBinding().ivAddMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void addMenu() {
        Menu menu = getMenu();
        String menuPic= "";
        MenuJson menuJson = new MenuJson(menu.getManagerID(), menu.getMenuName(),menu.getDescribe(), menuPic );

        ///
        MenuService menuService = NetContext.instance.create(MenuService.class);
        MediaType jsonType = MediaType.parse("application/json");
        //
        final String addMenuJson = (new Gson()).toJson(menuJson);

        final RequestBody menuBody = RequestBody.create(jsonType, addMenuJson);

        Call<MenuResponseJson> addMenuCall = menuService.addMenu(menuBody);

        addMenuCall.enqueue(new Callback<MenuResponseJson>() {
            @Override
            public void onResponse(Call<MenuResponseJson> call, Response<MenuResponseJson> response) {
                pDialog.dismiss();
                MenuResponseJson responseJson = response.body();
                Log.d(TAG, "onResponse: addmenujson" + responseJson.toString());
                if(responseJson != null){
                    if (response.code() == 200){
                        Toast.makeText(CreateMenuActivity.this, "add success", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }else{
                    Toast.makeText(CreateMenuActivity.this, "add false", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MenuResponseJson> call, Throwable t) {
                Toast.makeText(CreateMenuActivity.this, "onFailure", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public Menu getMenu() {
        String menuName = getBinding().etMenuName.getText().toString().trim();
        if (menuName.isEmpty()) {
            getBinding().etMenuName.setError("Enter menu name");
        }
        String menuDes = getBinding().etMenuDes.getText().toString();
        String managerID = managerId;
        Log.d(TAG, "getMenu: manager_id "+ LoginActivity.managerId);

        Menu m = new Menu(menuName, managerID, menuDes);
        return m;
    }
}
