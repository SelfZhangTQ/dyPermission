package com.permission;

import java.io.Serializable;

/**
 * @author SelfZhangTQ
 */

public class PermissionItem implements Serializable {
    public String PermissionName;
    public String Permission;

    public PermissionItem(String permission, String permissionName) {
        Permission = permission;
        PermissionName = permissionName;
    }

    public PermissionItem(String permission) {
        Permission = permission;
    }
}
