package com.shailesh.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


@RuntimePermissions
public class PermissionsDispatcherActivity extends AppCompatActivity implements View.OnClickListener
{
    Button id_request_permission;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_dispatcher);

        id_request_permission = findViewById(R.id.id_request_permission);

        id_request_permission.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view == id_request_permission)
        {
            // Click On Build > Make Project Before Witing this
            PermissionsDispatcherActivityPermissionsDispatcher.openCameraWithPermissionCheck(PermissionsDispatcherActivity.this);
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void openCamera()
    {
        Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionsDispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationale(final PermissionRequest request)
    {
        new AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("This Permission is Needed because this and that")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .create()
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void permissionDenied()
    {
        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void permissionNeverAskAgain()
    {
        Toast.makeText(this, "Never Ask Again", Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("This Permission is Needed because this and that")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}
