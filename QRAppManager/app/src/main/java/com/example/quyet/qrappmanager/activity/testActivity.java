package com.example.quyet.qrappmanager.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        showUndoMess();


        //Generate QR Code
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

        //Save to gallary

        if (ContextCompat.checkSelfPermission(testActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(testActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
        }
        ((Button) findViewById(R.id.btnSaveQR)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable draw = (BitmapDrawable) ((ImageView) findViewById(R.id.ivTestQR)).getDrawable();
                Bitmap bitmap = draw.getBitmap();
                try {
                    saveImageToExternal("default_menu_2", bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void saveImageToExternal(String imgName, Bitmap bm) throws IOException {
        //Create Path to save Image
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "QRcode"); //Creates app specific folder
        path.mkdirs();
        File imageFile = new File(path, imgName + ".png"); // Imagename.png
        FileOutputStream out = new FileOutputStream(imageFile);
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
            out.flush();
            out.close();
            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(this, new String[]{imageFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                    Log.i("ExternalStorage", "Scanned " + path + ":");
                    Log.i("ExternalStorage", "-> uri=" + uri);
                }
            });
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new IOException();
        }
    }

    private void showUndoMess() {
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.rlTest),
                "aaaa", Snackbar.LENGTH_SHORT);
        mySnackbar.setAction("bbbbb", new MyUndoListener());
        mySnackbar.show();
    }

    public class MyUndoListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // Code to undo the user's last action
            Toast.makeText(testActivity.this, "undo", Toast.LENGTH_SHORT).show();
        }
    }
}
