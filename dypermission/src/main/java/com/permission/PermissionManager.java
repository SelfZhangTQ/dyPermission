package com.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author SelfZhangTQ
 */

public class PermissionManager {
    private final Context mContext;
    private String mTitle;
    private String mMsg;
    private int mStyleResId = -1;
    private PermissionCallback mCallback;
    private List<PermissionItem> mCheckPermissions;
    private int mPermissionType;

    private int mFilterColor = 0;
    private int mAnimStyleId = -1;

    public static PermissionManager create(Context context) {
        return new PermissionManager(context);
    }

    public PermissionManager(Context context) {
        mContext = context;
    }

    public PermissionManager title(String title) {
        mTitle = title;
        return this;
    }

    public PermissionManager permissions(List<PermissionItem> permissionItems) {
        mCheckPermissions = permissionItems;
        return this;
    }


    public PermissionManager animStyle(int styleId) {
        mAnimStyleId = styleId;
        return this;
    }

    public PermissionManager style(int styleResIdsId) {
        mStyleResId = styleResIdsId;
        return this;
    }

    public static boolean checkPermission(Context context, String permission) {
        int checkPermission = ContextCompat.checkSelfPermission(context, permission);
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    /**
     * 检查多个权限
     *
     * @param callback
     */
    public void checkArrayPermission(PermissionCallback callback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (callback != null) {
                callback.onSuccess();
            }
            return;
        }
        //检查权限，过滤已允许的权限
        Iterator<PermissionItem> iterator = mCheckPermissions.listIterator();
        while (iterator.hasNext()) {
            if (checkPermission(mContext, iterator.next().Permission)) {
                iterator.remove();
            }
        }
        mCallback = callback;
        if (mCheckPermissions.size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                List<String> arrNameList = new ArrayList<>();
                for (int i = 0; i < mCheckPermissions.size(); i++) {
                    String permissionKey = mCheckPermissions.get(i).Permission;
                    //适配8.0权限组申请
                    String permissionArrName = Permission.permissionArrMap.get(permissionKey);
                    if (!TextUtils.isEmpty(permissionArrName)) {
                        arrNameList.add(permissionArrName);
                    }

                }
                //去掉重复权限组
                List<String> mList = removeRpetitionr(arrNameList);
                mCheckPermissions.clear();
                for (int i = 0; i < mList.size(); i++) {
                    String str = mList.get(i);
                    String permissionName = Permission.permissionNameMap.get(str);
                    String[] arr = Permission.permissionMap.get(str);
                    for (int x = 0; x < arr.length; x++) {
                        mCheckPermissions.add(new PermissionItem(arr[x], permissionName));
                    }

                }

            }
            if (mCheckPermissions.size() > 0) {
                startActivity();
            } else {
                callback.onClose();
            }

        } else {
            if (callback != null) {
                callback.onSuccess();
            }
        }


    }

    private List<String> removeRpetitionr(List<String> arrNameList) {
        Set set = new HashSet();
        List<String> newList = new ArrayList();
        set.addAll(arrNameList);
        newList.addAll(set);
        return newList;
    }

    /**
     * 检查单个权限
     *
     * @param permission
     * @param callback
     */
    public void checkSinglePermission(String permission, PermissionCallback callback) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkPermission(mContext, permission)) {
            if (callback != null) {
                callback.onGuarantee(permission, 0);
            }
            return;
        }
        mCallback = callback;
        mPermissionType = PermissionActivity.PERMISSION_TYPE_SINGLE;
        mCheckPermissions = new ArrayList<>();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String permissionArrName = Permission.permissionArrMap.get(permission);

            String permissionName = Permission.permissionNameMap.get(permissionArrName);
            String[] arr = Permission.permissionMap.get(permissionArrName);
            for (int x = 0; x < arr.length; x++) {
                mCheckPermissions.add(new PermissionItem(arr[x], permissionName));
            }
        } else {
            mCheckPermissions.add(new PermissionItem(permission));
        }

        startActivity();
    }

    private void startActivity() {
        PermissionActivity.setCallBack(mCallback);
        Intent intent = new Intent(mContext, PermissionActivity.class);
        intent.putExtra(ConstantValue.DATA_TITLE, mTitle);
        intent.putExtra(ConstantValue.DATA_PERMISSION_TYPE, mPermissionType);
        intent.putExtra(ConstantValue.DATA_MSG, mMsg);
        intent.putExtra(ConstantValue.DATA_FILTER_COLOR, mFilterColor);
        intent.putExtra(ConstantValue.DATA_STYLE_ID, mStyleResId);
        intent.putExtra(ConstantValue.DATA_ANIM_STYLE, mAnimStyleId);
        intent.putExtra(ConstantValue.DATA_PERMISSIONS, (Serializable) mCheckPermissions);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

}
