package com.example.tabletsinventory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_main);

        bi.addDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddDeviceActivity.class));
//                final AlertDialog.Builder alertDLG = new AlertDialog.Builder(MainActivity.this);
//                alertDLG.setMessage("Which device you want to Add");
//                alertDLG.setCancelable(false);

//                alertDLG.setNegativeButton("Tablet", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(new Intent(MainActivity.this,AddDeviceActivity.class));
//                    }
//                });

//                alertDLG.setPositiveButton("Mobile", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        startActivity(new Intent(MainActivity.this,AddDeviceActivity.class));
//                    }
//                });
//                alertDLG.create().show();
            }
        });

        bi.updateDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UpdateDeviceActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logout:
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                return true;

            case R.id.incident:
                startActivity(new Intent(this,Incident.class));
                return true;

            case R.id.view_data:
                startActivity(new Intent(this,ContentActivity.class));
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
