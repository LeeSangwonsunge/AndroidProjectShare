package com.example.inha.androidpj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE Location3 (_id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT,lat TEXT, lon TEXT, status TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String date, String lat, String lon, String status) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        //db.execSQL("INSERT INTO MONEYBOOK VALUES(null, '" + item + "', " + price + ", '" + create_at + "');");
        db.execSQL("INSERT INTO Location3 VALUES(null,'" + date + "', '" + lat+ "', '" + lon + "', '"+ status +"');");
        db.close();
    }

    public void update(String Location3, String loca) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        //db.execSQL("UPDATE MONEYBOOK SET price=" + price + " WHERE item='" + item + "';");
        db.execSQL("UPDATE Location3 SET price=" + Location3 + " WHERE item='" + loca + "';");
        db.close();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM Location3 WHERE item='" + item + "';");
        db.close();
    }
    public ArrayList getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        ArrayList<String> strList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM Location3", null);
        while (cursor.moveToNext()) {

            /*
            result += cursor.getString(0)
                    + " / "
                    + cursor.getString(1) +"/"
                    + cursor.getString(2) +"/"
                    + cursor.getString(3) +"/"
                    + cursor.getString(4)
                    + " \n ";

            */
            strList.add(cursor.getString(0));
            strList.add(cursor.getString(1));
            strList.add(cursor.getString(2));
            strList.add(cursor.getString(3));
            strList.add(cursor.getString(4));

        }

        return strList;
    }

    public String[] getDateResult(String date) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String selectDate = "";
        String status = "";

        ArrayList<String> strList = new ArrayList<>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT date,status FROM Location3 WHERE date LIKE "+"'"+date+"%'", null);
        while (cursor.moveToNext()) {
            selectDate += cursor.getString(0) + "/";
            status += cursor.getString(1)+"/";
        }

        String strArray[] = new String[2];
        strArray[0]=selectDate;
        strArray[1]=status;

        return strArray;
    }
}
