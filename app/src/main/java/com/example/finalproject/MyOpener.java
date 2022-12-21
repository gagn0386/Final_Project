package com.example.finalproject;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//This class was unused in my Project. It is an incomplete file to be used for database purposes.

public class MyOpener extends SQLiteOpenHelper {

    protected final static String DATABASE_NAME = "FavouritesDB";
    protected final static String VERSION_NUM = "1";
    public final static String TABLE_NAME = "FAVOURITES";
    public final static String COL_TITLE = "TITLE";
    public final static String COL_DESC = "DESCRIPTION";
    public final static String COL_DATE = "DATE";
    public final static String COL_LINK = "LINK";
    public final static String COL_ID = "_id";


    public MyOpener(Context ctx) {
        super(ctx, DATABASE_NAME, null, Integer.parseInt(VERSION_NUM));

    }

    public MyOpener(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyOpener(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public MyOpener(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_TITLE + " text,"
                + COL_DESC + " text,"
                + COL_DATE + " text,"
                + COL_LINK + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
