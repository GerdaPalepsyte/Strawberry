package com.example.strawberry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.BaseColumns;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FeedReaderContract {
    private FeedReaderContract() {

    }

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String TABLE_NAME_HOME = "home";
        public static final String TABLE_NAME_USER = "user";
        public static final String SNAME = "SNAME";
        public static final String DES = "DES";
        public static final String IM = "IM";
        public static final String username = "username";
        public static final String password = "password";
        public static final String email = "email";


        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.SNAME + " TEXT," +
                        FeedEntry.DES + " TEXT," +
                        FeedEntry.IM + " BLOB" + ");";


        private static final String SQL_CREATE_ENTRIES_HOME =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_HOME + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.SNAME + " TEXT," +
                        FeedEntry.DES + " TEXT," +
                        FeedEntry.IM + " BLOB" + ");";


        private static final String SQL_CREATE_ENTRIES_USER =
                "CREATE TABLE " + FeedEntry.TABLE_NAME_USER + " (" +
                        FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.email + " TEXT," +
                        FeedEntry.username + " TEXT," +
                        FeedEntry.password + " TEXT" + ");";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
        private static final String SQL_DELETE_ENTRIES_HOME =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_HOME;

        private static final String SQL_DELETE_ENTRIES_USER =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_USER;

        public static class SymptomsDbHelper extends SQLiteOpenHelper {

            public static final int DATABASE_VERSION = 3;
            public static final String DATABASE_NAME = "Symptoms.db";

            public SymptomsDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
            }

            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_ENTRIES);
                db.execSQL(SQL_CREATE_ENTRIES_HOME);
                db.execSQL(SQL_CREATE_ENTRIES_USER);

            }

            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL(SQL_DELETE_ENTRIES);
                db.execSQL(SQL_DELETE_ENTRIES_HOME);
                db.execSQL(SQL_DELETE_ENTRIES_USER);
                onCreate(db);
            }

            public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                onUpgrade(db, oldVersion, newVersion);
            }

            public void insertContact(String symptom, String desc, byte[] image) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("IM", image);
                contentValues.put("SNAME", symptom);
                contentValues.put("DES", desc);

                db.insert(TABLE_NAME,null,contentValues);

            }


            public void insertContactHome(String symptom, String desc, byte[] image) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("IM", image);
                contentValues.put("SNAME", symptom);
                contentValues.put("DES", desc);

                db.insert(TABLE_NAME_HOME, null, contentValues);
            }

            public void insertContactUser(String username, String pass) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("username", username);
                contentValues.put("password", pass);

                db.insert(TABLE_NAME_USER, null, contentValues);
            }

            public Cursor fetch() {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query(TABLE_NAME, new String[]{SNAME, DES, IM}, null, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                }
                return cursor;
            }

            public Cursor fetchHome() {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.query(TABLE_NAME_HOME, new String[]{SNAME, DES, IM}, null, null, null, null, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                }
                return cursor;
            }


            public void delete(String name) {
                SQLiteDatabase db = this.getWritableDatabase();
                db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE SNAME = " + name);
            }

            public Cursor getDataSpecific(String id) {
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor res = db.rawQuery("SELECT SNAME,DES,IM FROM " + TABLE_NAME + " WHERE _ID = " + id, null);
                return res;

            }

            public void addUser(User user) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(username, user.getName());
                values.put(email, user.getEmail());
                values.put(password, user.getPassword());
                // Inserting Row
                db.insert(TABLE_NAME_USER, null, values);
                db.close();
            }

            public boolean checkUsers(String usernameUser, String passwordUser) {
                // array of columns to fetch
                String[] columns = {
                        _ID
                };
                SQLiteDatabase db = this.getReadableDatabase();
                // selection criteria
                String selection = username + " = ?" + " AND " + password + " = ?";
                // selection arguments
                String[] selectionArgs = {usernameUser, passwordUser};
                // query user table with conditions
                Cursor cursor = db.query(TABLE_NAME_USER, //Table to query
                        columns,                    //columns to return
                        selection,                  //columns for the WHERE clause
                        selectionArgs,              //The values for the WHERE clause
                        null,                       //group the rows
                        null,                       //filter by row groups
                        null);                      //The sort order
                int cursorCount = cursor.getCount();
                cursor.close();
                db.close();
                if (cursorCount > 0) {
                    return true;
                }
                return false;
            }

            public boolean checkUser(String emailUser) {
                // array of columns to fetch
                String[] columns = {
                        _ID
                };
                SQLiteDatabase db = this.getReadableDatabase();
                // selection criteria
                String selection = email + " = ?";
                // selection argument
                String[] selectionArgs = {emailUser};
                // query user table with condition
                Cursor cursor = db.query(TABLE_NAME_USER, //Table to query
                        columns,                    //columns to return
                        selection,                  //columns for the WHERE clause
                        selectionArgs,              //The values for the WHERE clause
                        null,                       //group the rows
                        null,                      //filter by row groups
                        null);                      //The sort order
                int cursorCount = cursor.getCount();
                cursor.close();
                db.close();
                if (cursorCount > 0) {
                    return true;
                }
                return false;
            }


            public List<User> getAllUser() {
                // array of columns to fetch
                String[] columns = {
                        _ID,
                        username,
                        password,
                        email
                };
                // sorting orders
                String sortOrder =
                        TABLE_NAME_USER + " ASC";
                List<User> userList = new ArrayList<User>();
                SQLiteDatabase db = this.getReadableDatabase();
                // query the user table
                Cursor cursor = db.query(TABLE_NAME_USER, //Table to query
                        columns,    //columns to return
                        null,        //columns for the WHERE clause
                        null,        //The values for the WHERE clause
                        null,       //group the rows
                        null,       //filter by row groups
                        sortOrder); //The sort order
                // Traversing through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        User user = new User();
                        user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(_ID))));
                        user.setName(cursor.getString(cursor.getColumnIndex(username)));
                        user.setEmail(cursor.getString(cursor.getColumnIndex(email)));
                        user.setPassword(cursor.getString(cursor.getColumnIndex(password)));
                        // Adding user record to list
                        userList.add(user);
                    } while (cursor.moveToNext());
                }
                cursor.close();
                db.close();
                // return user list
                return userList;
            }

            public boolean isTableExists(String tableName) {
                SQLiteDatabase db = this.getReadableDatabase();

                    if(db == null || !db.isOpen()) {
                        db = getReadableDatabase();
                    }

                    if(!db.isReadOnly()) {
                        db.close();
                        db = getReadableDatabase();
                    }


                String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'";
                try (Cursor cursor = db.rawQuery(query, null)) {
                    if(cursor!=null) {
                        if(cursor.getCount()>0) {
                            return true;
                        }
                    }
                    return false;
                }
            }


        }
    }
}


