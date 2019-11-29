package com.example.tabletsinventory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import info.androidhive.barcode.BarcodeReader;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;
import io.blackbox_vision.datetimepickeredittext.view.TimePickerEditText;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityAddBinding;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Calendar;
import java.util.List;

public class Add extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    ActivityAddBinding bi;

    private static final String TAG = "Add";

    //Date picker Ali code
    private DatePickerEditText datePickerEditText;

    //    TextView textView;
    String text = "";
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Add Device");

        bi = DataBindingUtil.setContentView(this, R.layout.activity_add);
        bi.setCallback(this);

        db = new DatabaseHelper(this);

        // used for show data in textview
//        textView = (TextView) findViewById(R.id.textView);

        //inserting inventory
//        long rowID = db.addDevice(new Inventory(1, 55, "R52HB1872EJ", "MaPPS001", "SAMSUNG", "SM-T285", "1/31/2019", "MaPPs", "Yaqub Wasan", "None"));
//        Log.d(TAG, "onCreate: " + rowID);

//        List<Inventory> inventories = db.getAllInventory();
//        if(inventories != null){
//            for (Inventory i : inventories){
//                String record =  "IMEI: " + i.getImei() + ", SERIAL: " + i.getSerial() + ", TAG NUMBER: " + i.getTag_number() + ", BRAND: " + i.getBrand() + ", MODEL: " + i.getModel() +
//                        ", DATE: " + i.getDate() + ", PROJECT NAME: " + i.getProject_name() + ", RECEIVED FROM: " + i.getReceived_from() + ", REMARKS: " + i.getRemarks() + "\n";
//                text+=record;
//            }
//
//            textView.setText(String.valueOf(text));
//        }

   /*     bi.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });*/

        datePickerEditText = findViewById(R.id.entry_date);
    }

    // Function for save data, reset fields, validation
    public void submit(View v){
        if (validateForm()){
            saveData();
            reset();
        }
    }

    public void saveData() {
            Inventory inventory = new Inventory();
            inventory.setImei(Long.parseLong(bi.imei.getText().toString()));
            inventory.setSerial(bi.serial.getText().toString());
            inventory.setTag_number(bi.tagNumber.getText().toString());
            inventory.setBrand(bi.brand.getText().toString());
            inventory.setModel(bi.model.getText().toString());
            inventory.setDate(bi.entryDate.getText().toString());
            inventory.setProject_name(bi.project.getText().toString());
            inventory.setReceived_from(bi.receivedFrom.getText().toString());
            inventory.setLocation(bi.location.getText().toString());
            inventory.setRemarks(bi.remarks.getText().toString());

            db.addDevice(inventory);

        Toast.makeText(this,"Record Saved",Toast.LENGTH_LONG).show();
    }

    //Reset all fields
    public void reset(){
        bi.imei.setText(null);
        bi.serial.setText(null);
        bi.tagNumber.setText(null);
        bi.brand.setText(null);
        bi.model.setText(null);
        bi.entryDate.setText(null);
        bi.project.setText(null);
        bi.receivedFrom.setText(null);
        bi.location.setText(null);
        bi.remarks.setText(null);
    }

    private boolean validateForm(){
        //  Validate IMEI
        if (bi.imei.getText().toString().isEmpty()){
            bi.imei.setError("Field can't be Empty");
            return false;
        }else {
            bi.imei.setError(null);
        }
        //  Validate TAG_NUMBER
        if (bi.tagNumber.getText().toString().isEmpty()){
            bi.tagNumber.setError("Field can't be Empty");
            return false;
        }else {
            bi.tagNumber.setError(null);
        }
        return true;
    }

    //QRReader
    public void BtnQRscan(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan a sample sticker");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(false);

        integrator.initiateScan();
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                bi.tagNumber.setText(result.getContents());
            }
            }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        }

    //BarcodeReader
    public void BtnBRscan(){
        IntentIntegrator integrator = new IntentIntegrator(this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onScanned(Barcode barcode) {
        Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(this,"Camera permission denied!", Toast.LENGTH_LONG).show();

    }
}
