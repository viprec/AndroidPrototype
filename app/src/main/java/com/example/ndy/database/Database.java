package com.example.ndy.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Database extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "events";
    private static final int DATABASE_VERSION = 3;

    public Database(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
    }

}
