package com.example.tabletsinventory.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityLoginBinding;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    ActivityLoginBinding bi;
    boolean permission_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Login Page");
        bi = DataBindingUtil.setContentView(this, R.layout.activity_login);

        invokePermissions();

        bi.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!permission_flag) {
                    invokePermissions();
                }


                Log.d(TAG, "onClick: loginbutton pressed..");
                validate(bi.UserName.getText().toString(), bi.password.getText().toString());

                if (bi.UserName.getText().toString().isEmpty()) {
                    bi.UserName.setError("Field Can't be Empty");
                } else {
                    bi.UserName.setError(null);
                }
                if (bi.password.getText().toString().isEmpty()) {
                    bi.password.setError("Field Can't be Empty");
                } else {
                    bi.password.setError(null);
                }
            }
        });
        // use appname for temporary login
        bi.appname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void invokePermissions() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
                permission_flag = true;
            }

            @Override
            public void onDenied(Context context, ArrayList<String> deniedPermissions) {
                permission_flag = false;
            }
        });
    }

    private void validate(String UserName, String Password) {
        if ((UserName.equals("admin")) && (Password.equals("admin"))) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Please Enter correct Username and Password"
                    , Toast.LENGTH_LONG).show();
        }
    }
}