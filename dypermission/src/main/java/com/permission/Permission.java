package com.permission;

import android.Manifest;
import android.os.Build;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：SelfZhangTQ
 */
public final class Permission {

    public static final String[] CALENDAR;   // 读写日历。
    public static final String[] CAMERA;     // 相机。
    public static final String[] CONTACTS;   // 读写联系人。
    public static final String[] LOCATION;   // 读位置信息。
    public static final String[] MICROPHONE; // 使用麦克风。
    public static final String[] PHONE;      // 读电话状态、打电话、读写电话记录。
    public static final String[] SENSORS;    // 传感器。
    public static final String[] SMS;        // 读写短信、收发短信。
    public static final String[] STORAGE;    // 读写存储卡。

    public static final Map<String, String> permissionArrMap;

    public static final Map<String, String[]> permissionMap;
    
    public static final Map<String, String> permissionNameMap;

    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            CALENDAR = new String[]{};
            CAMERA = new String[]{};
            CONTACTS = new String[]{};
            LOCATION = new String[]{};
            MICROPHONE = new String[]{};
            PHONE = new String[]{};
            SENSORS = new String[]{};
            SMS = new String[]{};
            STORAGE = new String[]{};
        } else {
            CALENDAR = new String[]{
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR};

            CAMERA = new String[]{
                    Manifest.permission.CAMERA};

            CONTACTS = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS};

            LOCATION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            MICROPHONE = new String[]{
                    Manifest.permission.RECORD_AUDIO};

            PHONE = new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG,
                    Manifest.permission.USE_SIP,
                    Manifest.permission.PROCESS_OUTGOING_CALLS};

            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS};

            SMS = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_WAP_PUSH,
                    Manifest.permission.RECEIVE_MMS};

            STORAGE = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
        permissionNameMap = new HashMap<>();
        permissionNameMap.put("CALENDAR","读写日历");
        permissionNameMap.put("CAMERA","相机");
        permissionNameMap.put("CONTACTS","读写联系人");

        permissionNameMap.put("LOCATION","读位置信息");
        permissionNameMap.put("MICROPHONE","使用麦克风");
        permissionNameMap.put("PHONE","电话状态");

        permissionNameMap.put("SENSORS","传感器");
        permissionNameMap.put("SMS","读写短信");
        permissionNameMap.put("STORAGE","读写存储卡");


        permissionMap = new HashMap<>();

        permissionMap.put("CALENDAR",CALENDAR);
        permissionMap.put("CAMERA",CAMERA);
        permissionMap.put("CONTACTS",CONTACTS);

        permissionMap.put("LOCATION",LOCATION);
        permissionMap.put("MICROPHONE",MICROPHONE);
        permissionMap.put("PHONE",PHONE);

        permissionMap.put("SENSORS",SENSORS);
        permissionMap.put("SMS",SMS);
        permissionMap.put("STORAGE",STORAGE);

        permissionArrMap = new HashMap<>();

        permissionArrMap.put("android.permission.READ_EXTERNAL_STORAGE", "STORAGE");
        permissionArrMap.put("android.permission.WRITE_EXTERNAL_STORAGE", "STORAGE");


        permissionArrMap.put("android.permission.SEND_SMS", "SMS");
        permissionArrMap.put("android.permission.RECEIVE_SMS", "SMS");
        permissionArrMap.put("android.permission.READ_SMS", "SMS");
        permissionArrMap.put("android.permission.RECEIVE_WAP_PUSH", "SMS");
        permissionArrMap.put("android.permission.RECEIVE_MMS", "SMS");

        permissionArrMap.put("android.permission.BODY_SENSORS", "SENSORS");

        permissionArrMap.put("android.permission.READ_PHONE_STATE", "PHONE");
        permissionArrMap.put("android.permission.CALL_PHONE", "PHONE");
        permissionArrMap.put("android.permission.READ_CALL_LOG", "PHONE");
        permissionArrMap.put("android.permission.WRITE_CALL_LOG", "PHONE");
        permissionArrMap.put("android.permission.USE_SIP", "PHONE");
        permissionArrMap.put("android.permission.PROCESS_OUTGOING_CALLS", "PHONE");

        permissionArrMap.put("android.permission.READ_CALENDAR", "CALENDAR");
        permissionArrMap.put("android.permission.WRITE_CALENDAR", "CALENDAR");

        permissionArrMap.put("android.permission.CAMERA", "CAMERA");

        permissionArrMap.put("android.permission.READ_CONTACTS", "CONTACTS");
        permissionArrMap.put("android.permission.WRITE_CONTACTS", "CONTACTS");
        permissionArrMap.put("android.permission.GET_ACCOUNTS", "CONTACTS");

        permissionArrMap.put("android.permission.ACCESS_FINE_LOCATION", "LOCATION");
        permissionArrMap.put("android.permission.ACCESS_COARSE_LOCATION", "LOCATION");

        permissionArrMap.put("android.permission.RECORD_AUDIO", "MICROPHONE");

    }
}
