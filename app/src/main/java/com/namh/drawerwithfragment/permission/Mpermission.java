package com.namh.drawerwithfragment.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by namh on 2016-08-21.
 * @source from : https://gist.github.com/TheLittleNaruto/2d2fbf284bcb0503637f702bf257f97e
 */


public class MPermission {
    public static final int RECORD_PERMISSION_REQUEST_CODE = 1;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    Activity activity;

    public MPermission(Activity activity) {
        this.activity = activity;
    }

    public boolean checkPermissionForRecord() {
        return checkPermission(Manifest.permission.RECORD_AUDIO);
    }

    public boolean checkPermissionForExternalStorage() {
        return checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    public boolean checkPermissionForCamera() {
        return checkPermission(Manifest.permission.CAMERA);

    }

    public void requestPermissionForRecord() {
        requestPermission(Manifest.permission.RECORD_AUDIO, RECORD_PERMISSION_REQUEST_CODE);

    }

    public void requestPermissionForExternalStorage() {
        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
    }

    public void requestPermissionForCamera() {
        requestPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST_CODE);

    }

    public boolean checkPermission(String permissionType){
        int result = ContextCompat.checkSelfPermission(activity, permissionType);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }

    }

    public void requestPermission(String permissionType, int permissionCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permissionType)) {
            Toast.makeText(activity,
                    "Microphone permission needed for recording. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{permissionType},
                    permissionCode);
        }

    }

}