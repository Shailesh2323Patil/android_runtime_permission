package com.shailesh.runtimepermission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class EasyPermissionActivity extends AppCompatActivity implements View.OnClickListener , EasyPermissions.PermissionCallbacks
{
    Button id_request_permission;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_permission);

        id_request_permission = findViewById(R.id.id_request_permission);

        id_request_permission.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view == id_request_permission)
        {
            openCamera();
        }
    }

    @AfterPermissionGranted(123)
    private void openCamera()
    {
        String[] permission = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};

        if(EasyPermissions.hasPermissions(this,permission))
        {
            Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
        }
        else
        {
            EasyPermissions.requestPermissions(this,"We Need Permission Because This and That",123,permission);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms)
    {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms)
    {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms))
        {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE)
        {
            Toast.makeText(this, "Please Give Permission From Your Settings", Toast.LENGTH_SHORT).show();
        }
    }
}
