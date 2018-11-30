package sorry.aldan.ti3a_8_12_tugasbesar.Helper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import sorry.aldan.ti3a_8_12_tugasbesar.Model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USER = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LEVEL = "level";

    /*CREATE TABLE students ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_number TEXT......);*/

    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_NAMA + " TEXT , "
            + KEY_EMAIL + " TEXT, "
            + KEY_USERNAME +" TEXT, "
            + KEY_PASSWORD +" TEXT, "
            + KEY_LEVEL +" TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public void addUser(String nama,String email, String username, String password, String level){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_EMAIL, email);
        values.put(KEY_USERNAME, email);
        values.put(KEY_PASSWORD, email);
        values.put(KEY_LEVEL, email);

        long id = db.insertWithOnConflict(TABLE_USER,null,values,SQLiteDatabase.CONFLICT_IGNORE);
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+ TABLE_USER;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()){
            do {
                User u = new User();
                u.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                u.setNama(c.getString(c.getColumnIndex(KEY_NAMA)));
                u.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                u.setUsername(c.getString(c.getColumnIndex(KEY_USERNAME)));
                u.setLevel(c.getString(c.getColumnIndex(KEY_LEVEL)));

                users.add(u);
            }while (c.moveToNext());
        }

        return users;
    }

    public void updateUser(int id,String nama,String email, String username, String password, String level){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, nama);
        values.put(KEY_EMAIL, email);
        values.put(KEY_USERNAME, email);
        values.put(KEY_PASSWORD, email);
        values.put(KEY_LEVEL, email);

        db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }


    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();

        //deleting from users table
        db.delete(TABLE_USER, KEY_ID + " = ?",new String[]{String.valueOf(id)});


    }

    public boolean autentikasi(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_USER+" WHERE "+KEY_USERNAME+"="+username+" AND "+KEY_PASSWORD+"="+password,null);
        cursor.moveToFirst();
        if (cursor.getCount() == 1){
            return true;
        }else{
            return false;
        }
    }
}
