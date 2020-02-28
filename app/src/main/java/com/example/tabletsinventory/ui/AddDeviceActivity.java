package com.example.tabletsinventory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tabletsinventory.R;
import com.example.tabletsinventory.databinding.ActivityAddDeviceBinding;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;

public class AddDeviceActivity extends AppCompatActivity {

    ActivityAddDeviceBinding bi;
    private static final String TAG = "Add";
    private static final int BR_REQUEST_IMEI = 1011;
    private static final int QR_REQUEST = 1021;
    private static final int BR_REQUEST_SERIAL = 1031;

    //Date picker Ali code
    private DatePickerEditText datePickerEditText;

    //    TextView textView;
    String text = "";
    DatabaseHelper db;
    String device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Add Device");

        bi = DataBindingUtil.setContentView(this, R.layout.activity_add_device);
        bi.setCallback(this);

        db = new DatabaseHelper(this);

        datePickerEditText = findViewById(R.id.entry_date);


        // used for show data in textview
//        textView = (TextView) findViewById(R.id.textView);

        //inserting inventory
//        long rowID = db.addDevice(new InventoryAdd(1, 55, "R52HB1872EJ", "MaPPS001", "SAMSUNG", "SM-T285", "1/31/2019", "MaPPs", "Yaqub Wasan", "None"));
//        Log.d(TAG, "onCreate: " + rowID);

//        List<InventoryAdd> inventories = db.getAllInventory();
//        if(inventories != null){
//            for (InventoryAdd i : inventories){
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

        // Add radio Group
        bi.type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == bi.mobile.getId()) {
                    device = bi.mobile.getText().toString();
                } else {
                    device = bi.tablet.getText().toString();
                }
            }
        });
    }

    // Function for save data, reset fields, validation
    public void submit(View v) {
        if (validateForm()) {
            saveData();
            reset();
        }
    }

    public void saveData() {
        InventoryAdd inventory = new InventoryAdd();
        inventory.setImei(bi.imei.getText().toString());
        inventory.setSerial(bi.serial.getText().toString());
        inventory.setTag_number(bi.tagNumber.getText().toString());
        inventory.setBrand(bi.brand.getText().toString());
        inventory.setModel(bi.model.getText().toString());
        inventory.setDate(bi.entryDate.getText().toString());
        inventory.setProject_name(bi.project.getText().toString());
        inventory.setReceived_from(bi.receivedFrom.getText().toString());
        inventory.setLocation(bi.location.getText().toString());
        inventory.setRemarks(bi.remarks.getText().toString());
        inventory.setDevice(device);

        db.addDevice(inventory);

        Toast.makeText(this, "Record Saved", Toast.LENGTH_LONG).show();
    }

    //Reset all fields
    public void reset() {
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
        bi.type.clearCheck();
    }

    private boolean validateForm() {
        //  Validate IMEI
        if (bi.imei.getText().toString().isEmpty()) {
            bi.imei.setError("Field can't be Empty");
            bi.imei.requestFocus();
            return false;
        } else {
            bi.imei.setError(null);
        }
        //  Validate TAG_NUMBER
        if (bi.tagNumber.getText().toString().isEmpty()) {
            bi.tagNumber.setError("Field can't be Empty");
            return false;
        } else {
            bi.tagNumber.setError(null);
        }
        return true;
    }

    //QR and Barcode scanner
    public void Btnscan(int i) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Scan a sample sticker");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(true);
        integrator.setOrientationLocked(true);
        if (i == 1) {
            integrator.setRequestCode(BR_REQUEST_IMEI);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        } else if (i == 2) {
            integrator.setRequestCode(QR_REQUEST);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        } else {
            integrator.setRequestCode(BR_REQUEST_SERIAL);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        }

        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode != QR_REQUEST && requestCode != BR_REQUEST_IMEI && requestCode != BR_REQUEST_SERIAL) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if (result.getContents() == null) {
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
            if (requestCode == QR_REQUEST)
                bi.tagNumber.setText(result.getContents());
            else if (requestCode == BR_REQUEST_IMEI)
                bi.imei.setText(result.getContents());
            else if (requestCode == BR_REQUEST_SERIAL)
                bi.serial.setText(result.getContents());
        }
    }
}
