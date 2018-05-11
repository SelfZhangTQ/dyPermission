package com.dynamicpermission;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.permission.PermissionCallback;
import com.permission.PermissionItem;
import com.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtianqiu
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.CAMERA).setOnClickListener(this);
        findViewById(R.id.STORAGE).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.STORAGE:
                List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
                permissionItems.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "文件读写"));
                permissionItems.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "文件读写"));
                PermissionManager.create(MainActivity.this)
                        .permissions(permissionItems)
                        .animStyle(R.style.PermissionAnimScale)
                        .checkArrayPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                            }

                            @Override
                            public void onSuccess() {
                                Toast.makeText(MainActivity.this, "文件读写权限申请成功", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onDeny(String permission, int position) {
                            }

                            @Override
                            public void onGuarantee(String permission, int position) {
                            }
                        });
                break;
            case R.id.CAMERA:
                PermissionManager.create(MainActivity.this)
                        .animStyle(R.style.PermissionAnimScale)
                        .checkSinglePermission(Manifest.permission.CAMERA, new PermissionCallback() {
                            @Override
                            public void onClose() {

                            }

                            @Override
                            public void onSuccess() {
                            }

                            @Override
                            public void onDeny(String permission, int position) {
                                Toast.makeText(MainActivity.this, "拒绝", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onGuarantee(String permission, int position) {
                                Toast.makeText(MainActivity.this, "相机权限允许", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                startActivity(intent);
                            }
                        });
                break;
        }


    }
}
