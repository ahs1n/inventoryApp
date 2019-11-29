package com.example.tabletsinventory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityContentBinding;

import java.util.List;

public class ContentActivity extends AppCompatActivity {

    ActivityContentBinding bi;
    ContentAdapter adapter;
    DatabaseHelper db;
    List<Inventory> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bi = DataBindingUtil.setContentView(this,R.layout.activity_content);
        db = new DatabaseHelper(this);

        getiingData();

    }

    private void getiingData() {

        list = db.getAllInventory();

        if(list != null){
            adapter = new ContentAdapter(list,this);
            bi.list.setLayoutManager(new LinearLayoutManager(this));
            bi.list.setHasFixedSize(true);
            bi.list.setAdapter(adapter);
        }
    }
}
