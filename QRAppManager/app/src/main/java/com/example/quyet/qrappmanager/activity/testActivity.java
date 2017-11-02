package com.example.quyet.qrappmanager.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        showUndoMess();
    }

    private void showUndoMess() {
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.rlTest),
               "aaaa", Snackbar.LENGTH_SHORT);
        mySnackbar.setAction("bbbbb", new MyUndoListener());
        mySnackbar.show();
    }

    public class MyUndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            // Code to undo the user's last action
            Toast.makeText(testActivity.this, "undo", Toast.LENGTH_SHORT).show();
        }
    }
}
