package com.example.tabletsinventory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityIncidentBinding;

public class Incident extends AppCompatActivity {

    private static final String TAG = "Incident";

    ActivityIncidentBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);
    }
}
