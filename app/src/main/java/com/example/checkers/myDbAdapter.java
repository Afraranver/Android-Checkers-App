package com.example.checkers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class myDbAdapter {

    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public void insertDataSave(String name, int easywin, int easylose, int mediumwin, int mediumlose, int hardwin, int hardlose)
    {
        try{
            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME, name);
            contentValues.put(myDbHelper.EASYMODEWIN, easywin);
            contentValues.put(myDbHelper.EASYMODELOSS, easylose);
            contentValues.put(myDbHelper.MEDIUMMODEWIN, mediumwin);
            contentValues.put(myDbHelper.MEDIUMMODELOSS, mediumlose);
            contentValues.put(myDbHelper.HARDMODEWIN, hardwin);
            contentValues.put(myDbHelper.HARDMODELOSS, hardlose);
            dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<DBObjects> getData()
    {
        ArrayList<DBObjects> getAllData = new ArrayList<>();
        try{
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.EASYMODEWIN, myDbHelper.EASYMODELOSS, myDbHelper.MEDIUMMODEWIN, myDbHelper.MEDIUMMODELOSS, myDbHelper.HARDMODEWIN, myDbHelper.HARDMODELOSS};
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);

            while (cursor.moveToNext())
            {
                int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
                String easywin =cursor.getString(cursor.getColumnIndex(myDbHelper.EASYMODEWIN));
                String easylose =cursor.getString(cursor.getColumnIndex(myDbHelper.EASYMODELOSS));
                String mediumwin =cursor.getString(cursor.getColumnIndex(myDbHelper.MEDIUMMODEWIN));
                String mediumlose =cursor.getString(cursor.getColumnIndex(myDbHelper.MEDIUMMODELOSS));
                String hardwin =cursor.getString(cursor.getColumnIndex(myDbHelper.HARDMODEWIN));
                String hardlose =cursor.getString(cursor.getColumnIndex(myDbHelper.HARDMODELOSS));

                DBObjects dbObjects = new DBObjects(cid, name, Integer.parseInt(easywin), Integer.parseInt(easylose),Integer.parseInt(mediumwin),
                        Integer.parseInt(mediumlose),Integer.parseInt(hardwin),Integer.parseInt(hardlose));

                getAllData.add(dbObjects);

            }
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return getAllData;
    }

    public  int delete(String uname)
    {
        try{
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] whereArgs ={uname};
            return db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public  void deleteAll()
    {
        try{
            SQLiteDatabase db = myhelper.getWritableDatabase();
            db.delete(myDbHelper.TABLE_NAME, null, null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean CheckIsDataAlreadyInDBorNot( String fieldValue) {
        try{
            SQLiteDatabase sqldb = myhelper.getWritableDatabase();
            String Query = "Select * from " + myDbHelper.TABLE_NAME + " where " + myDbHelper.NAME + " LIKE '% " + fieldValue +"%' ";
            Cursor cursor = sqldb.rawQuery(Query, null);
            if(cursor.getCount() <= 0){
                cursor.close();
                return false;
            }
            cursor.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //check if the user exist in the database table
    public boolean Exists(String searchItem) {
        try{
            SQLiteDatabase sqldb = myhelper.getWritableDatabase();
            String[] columns = { myDbHelper.NAME };
            String selection = myDbHelper.NAME + " =?";
            String[] selectionArgs = { searchItem };
            String limit = "1";

            Cursor cursor = sqldb.query(myDbHelper.TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null, limit);
            boolean exists = (cursor.getCount() > 0);
            cursor.close();
            return exists;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }

    public void updateName(String Name, int easywin, int easylose, int mediumwin, int mediumlose, int hardwin, int hardlose )
    {
        try{
            SQLiteDatabase db = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME, Name);
            contentValues.put(myDbHelper.EASYMODEWIN, easywin);
            contentValues.put(myDbHelper.EASYMODELOSS, easylose);
            contentValues.put(myDbHelper.MEDIUMMODEWIN, mediumwin);
            contentValues.put(myDbHelper.MEDIUMMODELOSS, mediumlose);
            contentValues.put(myDbHelper.HARDMODEWIN, hardwin);
            contentValues.put(myDbHelper.HARDMODELOSS, hardlose);
            String[] whereArgs= {Name};
            db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + " = ?", whereArgs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String EASYMODEWIN= "EasyModeWin";    // Column III
        private static final String EASYMODELOSS= "EasyModeLoss";    // Column III
        private static final String MEDIUMMODEWIN= "MediumModeWin";    // Column III
        private static final String MEDIUMMODELOSS= "MediumModeLoss";    // Column III
        private static final String HARDMODEWIN= "HardModeWin";    // Column III
        private static final String HARDMODELOSS= "HardModeLoss";    // Column III


        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+EASYMODEWIN+" INTEGER, "+EASYMODELOSS+
                " INTEGER,"+MEDIUMMODEWIN+" INTEGER, "+MEDIUMMODELOSS+" INTEGER, "+HARDMODEWIN+" INTEGER, "+HARDMODELOSS+" INTEGER);";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Log.e("myDbAdapter", e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Log.e("myDbAdapter","OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Log.e("myDbAdapter", e.getMessage());
            }
        }
    }
}
