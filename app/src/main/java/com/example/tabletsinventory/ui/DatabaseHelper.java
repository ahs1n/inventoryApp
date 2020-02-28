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
    private static final String TABLE_ADD_DEVICES = "add_table";
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
    private static final String KEY_DEVICE = "device";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Update Device
    private static final int DATABASE_VERSION_update = 1;
    private static final String DATABASE_NAME_update = "tablets_inventory";
    private static final String TABLE_UPDATE_DEVICES = "update_table";
    private static final String KEY_ID_update = "id";
    private static final String KEY_IMEI_update = "imei";

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
        values.put(KEY_DEVICE, inventory.getDevice());

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

    private static final String KEY_TAG_update = "tag_number";
    private static final String KEY_DATE_update = "date";
    private static final String KEY_PROJECT_NAME_update = "project_name";
    private static final String KEY_RECEIVED_FROM_update = "received_from";
    private static final String KEY_HANDEDOVERBY_update = "handedoverby";
    private static final String KEY_ACCESSORIES_update = "accessories";
    private static final String KEY_LASTLOCATION_update = "lastlocation";
    private static final String KEY_CURRENTLOCATION_update = "currentlocation";
    private static final String KEY_REMARKS_update = "remarks";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADD_DEVICES_TABLE = " CREATE TABLE " + TABLE_ADD_DEVICES + "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_IMEI + " TEXT, "
                + KEY_SERIAL + " TEXT, " + KEY_TAG + " TEXT, " + KEY_BRAND + " TEXT, "
                + KEY_MODEL + " TEXT, " + KEY_DATE + " TEXT, " + KEY_PROJECT_NAME + " TEXT, "
                + KEY_RECEIVED_FROM + " TEXT, " + KEY_LOCATION + " TEXT, " + KEY_REMARKS + " TEXT, " + KEY_DEVICE + " TEXT " + ");";
        String CREATE_UPDATE_TABLE = " CREATE TABLE " + TABLE_UPDATE_DEVICES + "("
                + KEY_ID_update + "INTEGER PRIMARY KEY, " + KEY_IMEI_update + " TEXT, "
                + KEY_TAG_update + " TEXT, " + KEY_DATE_update + " TEXT, " + KEY_PROJECT_NAME_update + " TEXT, "
                + KEY_RECEIVED_FROM_update + " TEXT, " + KEY_HANDEDOVERBY_update + " TEXT, " + KEY_LASTLOCATION_update + " TEXT, "
                + KEY_CURRENTLOCATION_update + " TEXT, " + KEY_ACCESSORIES_update + " TEXT, " + KEY_REMARKS_update + " TEXT " + ");";

        db.execSQL(CREATE_ADD_DEVICES_TABLE);
        db.execSQL(CREATE_UPDATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADD_DEVICES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPDATE_DEVICES);

        onCreate(db);
    }

    // For Add Device
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
        values.put(KEY_DEVICE, inventory.getDevice());

        long rowID = db.insert(TABLE_ADD_DEVICES, null, values);
        db.close();

        return rowID;
    }

    // For Add Device
    InventoryAdd getInventory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADD_DEVICES, new String[]{KEY_ID, KEY_IMEI, KEY_SERIAL, KEY_TAG, KEY_BRAND, KEY_MODEL, KEY_DATE,
                        KEY_PROJECT_NAME, KEY_RECEIVED_FROM, KEY_LOCATION, KEY_REMARKS, KEY_DEVICE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        InventoryAdd inventory = new InventoryAdd(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))), cursor.getString(cursor.getColumnIndex(KEY_IMEI)), cursor.getString(cursor.getColumnIndex(KEY_SERIAL)),
                cursor.getString(cursor.getColumnIndex(KEY_TAG)), cursor.getString(cursor.getColumnIndex(KEY_BRAND)), cursor.getString(cursor.getColumnIndex(KEY_MODEL)), cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)), cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM)), cursor.getString(cursor.getColumnIndex(KEY_LOCATION)),
                cursor.getString(cursor.getColumnIndex(KEY_REMARKS)), cursor.getString(cursor.getColumnIndex(KEY_DEVICE)));

        return inventory;
    }

    public List<InventoryAdd> getAllInventory() {
        List<InventoryAdd> inventoryList = new ArrayList<>();

//        String selectQuery = "SELECT * FROM " + TABLE_ADD_DEVICES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ADD_DEVICES, new String[]{KEY_IMEI, KEY_SERIAL, KEY_TAG, KEY_BRAND, KEY_MODEL, KEY_DATE, KEY_REMARKS, KEY_PROJECT_NAME, KEY_RECEIVED_FROM, KEY_LOCATION, KEY_DEVICE},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                InventoryAdd inventory = new InventoryAdd();
                inventory.setImei(cursor.getString(cursor.getColumnIndex(KEY_IMEI)));
                inventory.setSerial(cursor.getString(cursor.getColumnIndex(KEY_SERIAL)));
                inventory.setTag_number(cursor.getString(cursor.getColumnIndex(KEY_TAG)));
                inventory.setBrand(cursor.getString(cursor.getColumnIndex(KEY_BRAND)));
                inventory.setModel(cursor.getString(cursor.getColumnIndex(KEY_MODEL)));
                inventory.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                inventory.setProject_name(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
                inventory.setReceived_from(cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM)));
                inventory.setLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
                inventory.setRemarks(cursor.getString(cursor.getColumnIndex(KEY_REMARKS)));
                inventory.setDevice(cursor.getString(cursor.getColumnIndex(KEY_DEVICE)));
                inventoryList.add(inventory);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return inventoryList;
    }

    // For Update Device
    long updateDevice(InventoryUpdate inventory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(KEY_ID, inventory.getImei());
        values.put(KEY_IMEI_update, inventory.getImei());
        values.put(KEY_TAG_update, inventory.getTag_number());
        values.put(KEY_DATE_update, inventory.getDate());
        values.put(KEY_PROJECT_NAME_update, inventory.getProject_name());
        values.put(KEY_HANDEDOVERBY_update, inventory.getHandedoverby());
        values.put(KEY_RECEIVED_FROM_update, inventory.getReceived_from());
        values.put(KEY_LASTLOCATION_update, inventory.getLastlocation());
        values.put(KEY_CURRENTLOCATION_update, inventory.getCurrentlocation());
        values.put(KEY_ACCESSORIES_update, inventory.getAccessories());
        values.put(KEY_REMARKS, inventory.getRemarks());
//        values.put(KEY_DEVICE, inventory.mo());

        long rowID = db.insert(TABLE_UPDATE_DEVICES, null, values);
        db.close();

        return rowID;
    }

    //For Update Device
    InventoryUpdate getInventoryUpdate(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_UPDATE_DEVICES, new String[]{KEY_ID_update, KEY_IMEI_update, KEY_TAG_update, KEY_DATE_update,
                        KEY_PROJECT_NAME_update, KEY_HANDEDOVERBY_update, KEY_RECEIVED_FROM_update, KEY_ACCESSORIES_update, KEY_REMARKS_update}, KEY_ID_update + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        InventoryUpdate inventoryUpdate = new InventoryUpdate(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID_update))), cursor.getInt(cursor.getColumnIndex(KEY_IMEI_update)),
                cursor.getString(cursor.getColumnIndex(KEY_TAG_update)), cursor.getString(cursor.getColumnIndex(KEY_TAG_update)), cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME_update)),
                cursor.getString(cursor.getColumnIndex(KEY_ACCESSORIES_update)), cursor.getString(cursor.getColumnIndex(KEY_LASTLOCATION_update)), cursor.getString(cursor.getColumnIndex(KEY_CURRENTLOCATION_update)),
                cursor.getString(cursor.getColumnIndex(KEY_HANDEDOVERBY_update)), cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM_update)), cursor.getString(cursor.getColumnIndex(KEY_REMARKS_update)));

        return inventoryUpdate;
    }

    // For Update Device
    public List<InventoryUpdate> getAllInventoryUpdate() {
        List<InventoryUpdate> inventoryUpdate = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_UPDATE_DEVICES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{KEY_ID_update, KEY_IMEI_update, KEY_TAG_update, KEY_DATE_update, KEY_PROJECT_NAME_update, KEY_HANDEDOVERBY_update, KEY_RECEIVED_FROM_update,
                KEY_LASTLOCATION_update, KEY_CURRENTLOCATION_update, KEY_ACCESSORIES_update, KEY_REMARKS_update});

        if (cursor.moveToFirst()) {
            do {
                InventoryUpdate inventory = new InventoryUpdate();
                inventory.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_update)));
                inventory.setImei(cursor.getLong(cursor.getColumnIndex(KEY_IMEI_update)));
                inventory.setTag_number(cursor.getString(cursor.getColumnIndex(KEY_TAG_update)));
                inventory.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE_update)));
                inventory.setProject_name(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME_update)));
                inventory.setHandedoverby(cursor.getString(cursor.getColumnIndex(KEY_HANDEDOVERBY_update)));
                inventory.setReceived_from(cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM_update)));
                inventory.setLastlocation(cursor.getString(cursor.getColumnIndex(KEY_LASTLOCATION_update)));
                inventory.setCurrentlocation(cursor.getString(cursor.getColumnIndex(KEY_CURRENTLOCATION_update)));
                inventory.setAccessories(cursor.getString(cursor.getColumnIndex(KEY_ACCESSORIES_update)));
                inventory.setRemarks(cursor.getString(cursor.getColumnIndex(KEY_REMARKS_update)));
                inventoryUpdate.add(inventory);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return inventoryUpdate;
    }

    // For search and call data from Add_Device_Activity
    public InventoryAdd getInformation(String QRCode) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {KEY_TAG, KEY_IMEI, KEY_DATE, KEY_PROJECT_NAME, KEY_LOCATION, KEY_RECEIVED_FROM, KEY_REMARKS};
        String selection = KEY_TAG + " = ?";
        String[] args = {QRCode};
        InventoryAdd inventory = new InventoryAdd();

        Cursor cursor = db.query(TABLE_ADD_DEVICES, columns, selection, args, null, null, null, null);

        cursor.moveToFirst();
        do {
            inventory.setImei(cursor.getString(cursor.getColumnIndex(KEY_IMEI)));
            inventory.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            inventory.setProject_name(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
            inventory.setTag_number(cursor.getString(cursor.getColumnIndex(KEY_TAG)));
            inventory.setLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
            inventory.setReceived_from(cursor.getString(cursor.getColumnIndex(KEY_RECEIVED_FROM)));
            inventory.setRemarks(cursor.getString(cursor.getColumnIndex(KEY_REMARKS)));
        } while (cursor.moveToNext());
        return inventory;
    }
}
