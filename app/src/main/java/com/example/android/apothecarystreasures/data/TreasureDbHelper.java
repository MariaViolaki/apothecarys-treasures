package com.example.android.apothecarystreasures.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.apothecarystreasures.data.TreasureContract.TreasureEntry;

public class TreasureDbHelper extends SQLiteOpenHelper {

    // Main data of the database
    private static final String DATABASE_NAME = "apothecary.db";
    private static final int DATABASE_VERSION = 1;

    public TreasureDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the treasures table with SQL
    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE_TREASURES = "CREATE TABLE " + TreasureEntry.TABLE_NAME + " (" +
                TreasureEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TreasureEntry.COLUMN_PRICE + " INTEGER NOT NULL, " +
                TreasureEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 1," +
                TreasureEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, " +
                TreasureEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL);";

        database.execSQL(CREATE_TABLE_TREASURES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}