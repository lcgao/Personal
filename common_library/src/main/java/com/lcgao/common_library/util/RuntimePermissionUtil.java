package com.lcgao.common_library.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.lcgao.common_library.AppConstants;

/**
 * Created by lcgao on 2017/6/8.
 */

public class RuntimePermissionUtil {
    public static boolean getStoragePermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE}, AppConstants.READ_EXTERNAL_STORAGE_REQUEST_CODE);
            return false;
        }
        return true;
    }

    public static boolean getCameraPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, AppConstants.PERMISSION_CAMERA_REQUEST_CODE);
            return false;
        }
        return true;
    }

    public static boolean getRecordAudioPermission(Activity activity){
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.RECORD_AUDIO}, AppConstants.PERMISSION_RECORD_AUDIO_REQUEST_CODE);
            return false;
        }
        return true;
    }

    public static boolean getContactsPermission(Activity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CONTACTS}, AppConstants.PERMISSION_READ_CONTACTS_REQUEST_CODE);
            return false;
        }
        return true;
    }

    public static boolean getPhoneStatePermission(Activity activity){
        if(ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, AppConstants.PERMISSION_READ_PHONE_STATE_REQUEST_CODE);
            return false;
        }
        return true;
    }
}

