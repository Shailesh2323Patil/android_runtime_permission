package com.shailesh.runtimepermission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Button id_request_activity,id_request_dispatcher,id_easy_permission,id_request_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id_request_activity = findViewById(R.id.id_request_activity);
        id_request_dispatcher = findViewById(R.id.id_request_dispatcher);
        id_easy_permission = findViewById(R.id.id_easy_permission);
        id_request_phone = findViewById(R.id.id_request_phone);

        id_request_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,RequestPermissionActivity.class);
                startActivity(intent);
            }
        });

        id_request_dispatcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,PermissionsDispatcherActivity.class);
                startActivity(intent);
            }
        });

        id_easy_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,EasyPermissionActivity.class);
                startActivity(intent);
            }
        });

        id_request_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,PhoneCallRequest.class);
                startActivity(intent);
            }
        });
    }
}
