package com.example.tabletsinventory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
   
    ActivityLoginBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Login Page");
        
        bi = DataBindingUtil.setContentView(this, R.layout.activity_login);
        
        bi.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: loginbutton pressed..");
                validate(bi.UserName.getText().toString(),bi.password.getText().toString());

                if (bi.UserName.getText().toString().isEmpty()){
                bi.UserName.setError("Field Can't be Empty");}
                else {
                    bi.UserName.setError(null);
                }
                if (bi.password.getText().toString().isEmpty()){
                    bi.password.setError("Field Can't be Empty");
                }
                else {
                    bi.password.setError(null);
                }
            }
        });
        // use appname for temporary login
        bi.appname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }

    private void validate(String UserName, String Password){
        if ((UserName.equals("admin")) && (Password.equals("admin"))){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
        else {
            Toast.makeText(this,"Please Enter correct Username and Password"
                    ,Toast.LENGTH_LONG).show();
        }
    }
}