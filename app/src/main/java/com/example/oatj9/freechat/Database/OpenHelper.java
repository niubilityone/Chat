package com.example.oatj9.freechat.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.oatj9.freechat.User.User;

import java.util.ArrayList;

/**
 * Created by oatj9 on 2019/3/22.
 */

public class OpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;  //声明一个数据库变量db;

    public OpenHelper(Context context) {
        super(context, "db_test", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    //定义增删查改的方法
    public void add(String name, String password) {
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)", new Object[]{name, password});
    }

    public void delete(String name, String password) {
        db.execSQL("DELETE FROM user WHERE name = AND password =" + name + password);
    }

    public void updata(String password) {
        db.execSQL("UPDATE user SET password = ?", new Object[]{password});
    }
    //定义一个ArrayList类的list用于存放查询内容
    //使用游标Cursor进行数据库查询，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，最后是查询出来内容的排序方式："name DESC"
    //游标定义好了，接下来写一个while循环，让游标从表头游到表尾
    //在游的过程中把游出来的数据存放到list容器中
    public ArrayList<User> getAllData() {

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name, password));
        }
        return list;

    }
}

