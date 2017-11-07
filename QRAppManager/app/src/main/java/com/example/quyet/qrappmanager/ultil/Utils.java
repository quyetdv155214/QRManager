package com.example.quyet.qrappmanager.ultil;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

import okhttp3.Request;
import okio.Buffer;

/**
 * Created by quyetdv on 2/15/2017.
 */

public class Utils {
//    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final Utils instance = new Utils();
    private static final String TAG = "Utils";

    private Utils(){

    }
    public String getUUID(){
        String uuid = UUID.randomUUID().toString();
        Log.d(TAG, "getUUID: " + uuid);
        return uuid;
    }

    public static String getDataShared(Context context, String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }
    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
