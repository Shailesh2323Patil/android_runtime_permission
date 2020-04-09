package com.shailesh.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneCallRequest extends AppCompatActivity implements View.OnClickListener
{
    EditText id_edit_text;
    Button id_btn_call;

    private static int REQUEST_PERMISSION = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_request);

        id_edit_text = findViewById(R.id.id_edit_text);
        id_btn_call = findViewById(R.id.id_edit_call);

        id_btn_call.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view == id_btn_call)
        {
            makePhoneCall();
        }
    }

    private void makePhoneCall()
    {
        String number = id_edit_text.getText().toString();

        if(number.trim().length() > 0)
        {
            if(ContextCompat.checkSelfPermission(PhoneCallRequest.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(PhoneCallRequest.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PERMISSION);
            }
            else
            {
                String dial = "tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else
        {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PERMISSION)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }
            else
            {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
