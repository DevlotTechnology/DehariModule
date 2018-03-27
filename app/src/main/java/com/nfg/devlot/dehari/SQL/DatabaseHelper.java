package com.nfg.devlot.dehari.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hassan on 3/24/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    static int DATABASE_VERSION =1;
    static String DATABASE_NAME ="dehari_db";

    DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
