package com.example.quyet.qrappmanager.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        showUndoMess();
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode("default_menu_1", BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) findViewById(R.id.ivTestQR)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
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
