package com.example.tabletsinventory.ui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tablets_inventory";
    private static final String TABLE_ADD_DEVICES = "devices";
    private static final String KEY_ID = "id";
    private static final String KEY_IMEI = "imei";
    private static final String KEY_SERIAL = "serial";
    private static final String KEY_TAG = "tag_number";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_MODEL = "model";
    private static final String KEY_DATE = "date";
    private static final String KEY_PROJECT_NAME = "project_name";
    private static final String KEY_RECEIVED_FROM = "received_from";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_REMARKS = "remarks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADD_DEVICES_TABLE = " CREATE TABLE " + TABLE_ADD_DEVICES + "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_IMEI + " TEXT, "
                + KEY_SERIAL + " TEXT, " + KEY_TAG + " TEXT, " + KEY_BRAND + " TEXT, "
                + KEY_MODEL + " TEXT, " + KEY_DATE + " TEXT, " + KEY_PROJECT_NAME + " TEXT, "
                + KEY_RECEIVED_FROM + " TEXT, " +KEY_LOCATION + " TEXT, " + KEY_REMARKS + " TEXT " + ");";
        db.execSQL(CREATE_ADD_DEVICES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_DEVICES);

        onCreate(db);
    }

    long addDevice(InventoryAdd inventory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(KEY_ID, inventory.getImei());
        values.put(KEY_IMEI, inventory.getImei());
        values.put(KEY_SERIAL, inventory.getSerial());
        values.put(KEY_TAG, inventory.getTag_number());
        values.put(KEY_BRAND, inventory.getBrand());
        values.put(KEY_MODEL, inventory.getModel());
        values.put(KEY_DATE, inventory.getDate());
        values.put(KEY_PROJECT_NAME, inventory.getProject_name());
        values.put(KEY_RECEIVED_FROM, inventory.getReceived_from());
        values.put(KEY_LOCATION, inventory.getLocation());
        values.put(KEY_REMARKS, inventory.getRemarks());

        long rowID = db.insert(TABLE_ADD_DEVICES, null, values);
        db.close();

        return rowID;
    }

    InventoryAdd getInventory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADD_DEVICES, new String[]{KEY_ID, KEY_IMEI, KEY_SERIAL, KEY_TAG, KEY_BRAND, KEY_MODEL, KEY_DATE,
                        KEY_PROJECT_NAME, KEY_RECEIVED_FROM, KEY_LOCATION, KEY_REMARKS}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        InventoryAdd inventory = new InventoryAdd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))), cursor.getInt(cursor.getColumnIndex(KEY_IMEI)), cursor.getString(cursor.getColumnIndex(KEY_SERIAL)),
                cursor.getString(cursor.getColumnIndex(KEY_TAG)), cursor.getString(cursor.getColumnIndex(KEY_BRAND)), cursor.getString(cursor.getColumnIndex(KEY_MODEL)), cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)), cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM)),cursor.getString(cursor.getColumnIndex(KEY_LOCATION)), cursor.getString(cursor.getColumnIndex(KEY_REMARKS)));

        return inventory;
    }

    public List<InventoryAdd> getAllInventory() {
        List<InventoryAdd> inventoryList = new ArrayList<>();

//        String selectQuery = "SELECT * FROM " + TABLE_ADD_DEVICES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADD_DEVICES, new String[]{KEY_IMEI, KEY_SERIAL, KEY_TAG, KEY_BRAND, KEY_MODEL, KEY_DATE, KEY_REMARKS, KEY_PROJECT_NAME, KEY_RECEIVED_FROM, KEY_LOCATION},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                InventoryAdd inventory = new InventoryAdd();
                inventory.setImei(cursor.getLong(cursor.getColumnIndex(KEY_IMEI)));
                inventory.setSerial(cursor.getString(cursor.getColumnIndex(KEY_SERIAL)));
                inventory.setTag_number(cursor.getString(cursor.getColumnIndex(KEY_TAG)));
                inventory.setBrand(cursor.getString(cursor.getColumnIndex(KEY_BRAND)));
                inventory.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
                inventory.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                inventory.setProject_name(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
                inventory.setReceived_from(cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM)));
                inventory.setLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
                inventory.setRemarks(cursor.getString(cursor.getColumnIndex(KEY_REMARKS)));
                inventoryList.add(inventory);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return inventoryList;
    }

    public int updateInventory(InventoryAdd inventory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMEI, inventory.getImei());
        values.put(KEY_SERIAL, inventory.getSerial());
        values.put(KEY_TAG, inventory.getTag_number());
        values.put(KEY_BRAND, inventory.getBrand());
        values.put(KEY_MODEL, inventory.getModel());
        values.put(KEY_DATE, inventory.getDate());
        values.put(KEY_PROJECT_NAME, inventory.getProject_name());
        values.put(KEY_RECEIVED_FROM, inventory.getReceived_from());
        values.put(KEY_LOCATION, inventory.getLocation());
        values.put(KEY_REMARKS, inventory.getRemarks());

        return db.update(TABLE_ADD_DEVICES, values, KEY_ID + "=?",
                new String[]{String.valueOf(inventory.getId())});
    }

    public void deletInventory(InventoryAdd inventory) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ADD_DEVICES, KEY_ID + "=?",
                new String[]{String.valueOf(inventory.getId())});
        db.close();
    }

    public int getInventoryCount() {
        String countQuery = "SELECT * FROM " + TABLE_ADD_DEVICES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public Cursor getInformation() {
        SQLiteDatabase SQ = getReadableDatabase();
        String[] columns = {TABLE_ADD_DEVICES, KEY_TAG, KEY_IMEI};
        Cursor CR = SQ.query(TABLE_ADD_DEVICES, columns, null, null, null, null, null);

        return CR;
    }
}

