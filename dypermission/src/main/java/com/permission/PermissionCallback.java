package com.permission;

import java.io.Serializable;

/**
 * author：SelfZhangTQ on
 */

public interface PermissionCallback extends Serializable {
    void onClose();

    //完成
    void onSuccess();

    //拒绝
    void onDeny(String permission, int position);

    //已经允许
    void onGuarantee(String permission, int position);
}
