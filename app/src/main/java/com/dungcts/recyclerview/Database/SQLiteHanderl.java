package com.dungcts.recyclerview.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHanderl extends SQLiteOpenHelper {
    private static final String TAG = SQLiteHanderl.class.getSimpleName();
    public static final String DATABASE_NAME = "qluser.db";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_SINHVIEN = "sinhvien";
    public static final String KEY_ID = "id";
    public static final String KEY_MAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_ROLE = "role";

    public SQLiteHanderl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sinhvien = "CREATE TABLE " + TABLE_SINHVIEN + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_MAIL + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_ROLE + " TEXT" +
                ")";
        String them = "INSERT INTO " + TABLE_SINHVIEN + " ( " + KEY_ID + ", " + KEY_MAIL + ","
                + KEY_PASSWORD + "," + KEY_ROLE + ") VALUES" +
                "(1, 'abc@gmai.com', '123abc','Sinh viên')," +
                "(2, 'bcd@gmai.com', '123abc','Sinh viên')," +
                "(3, 'cde@gmai.com', '123abc','Sinh viên')," +
                "(4, 'def@gmai.com', '123abc','Sinh viên')," +
                "(5, 'def@gmai.com', '123abc','Sinh viên')," +
                "(6, 'def@gmai.com', '123abc','Sinh viên')," +
                "(7, 'def@gmai.com', '123abc','Sinh viên')," +
                "(8, 'def@gmai.com', '123abc','Sinh viên')," +
                "(9, 'def@gmai.com', '123abc','Sinh viên')," +
                "(10, 'efg@gmai.com', '123abc','Sinh viên');";
        sqLiteDatabase.execSQL(sinhvien);
        sqLiteDatabase.execSQL(them);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TABLE_SINHVIEN);
    }

    //lấy dữ liệu bảng danh sách
    public Cursor getAllDataSV() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SINHVIEN, null);
        return cursor;
    }

    public void themsv(String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ROLE, role);
        long ma = db.insert(TABLE_SINHVIEN, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "Thêm mới thành công " + ma);
    }

    public void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = String.format("DELETE FROM " + TABLE_SINHVIEN + " WHERE id='%s' ", id);
        db.execSQL(query);
    }

    //cập nhật dữ liệu bảng
    public void updateDataList(String id, String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, id);
        values.put(KEY_MAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_ROLE, role);
        long result = db.update(TABLE_SINHVIEN, values, KEY_ID + "=?", new String[]{id});
        db.close(); // Closing database connection
        Log.d(TAG, "Cập nhật thành công " + result);
    }
}
