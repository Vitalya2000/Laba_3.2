package com.example.laba_3;


import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    final Random random = new Random();
    Button btn1,btn2,btn3;
    EditText etFam,etImya,etOtch, etDate;
    MyDB_Helper dbHelper;
    int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.button3);
        btn3.setOnClickListener(this);


        etFam = (EditText) findViewById(R.id.etFam);
        etImya = (EditText) findViewById(R.id.etImya);
        etOtch = (EditText) findViewById(R.id.etOtch);
        etDate = (EditText) findViewById(R.id.etDate);


        dbHelper = new MyDB_Helper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        database.delete(MyDB_Helper.TABLE_STUDENTS, null, null);

        for (int i = 0; i<5; i++)
        {
            contentValues.put(MyDB_Helper.KEY_FAMILIYA, "Petrov " );
            contentValues.put(MyDB_Helper.KEY_IMYA, "Petr " );
            contentValues.put(MyDB_Helper.KEY_OTCHESTVO, "Petrovich " );
            contentValues.put(MyDB_Helper.KEY_DATE, random.nextInt(24)+":"+random.nextInt(60));
            database.insert(MyDB_Helper.TABLE_STUDENTS, null, contentValues);
            k = k+1;
        }

        dbHelper.close();
    }

    @Override
    public void onClick(View v) {

        String fam = etFam.getText().toString();
        String imya = etImya.getText().toString();
        String otch = etOtch.getText().toString();
        String date = etDate.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {
            case R.id.button1:
                Intent intent = new Intent(this, ShowActivity.class);
                startActivity(intent);
                break;

            case R.id.button2:
                contentValues.put(MyDB_Helper.KEY_FAMILIYA, fam);
                contentValues.put(MyDB_Helper.KEY_IMYA, imya);
                contentValues.put(MyDB_Helper.KEY_OTCHESTVO, otch);
                contentValues.put(MyDB_Helper.KEY_DATE, date);
                database.insert(MyDB_Helper.TABLE_STUDENTS, null, contentValues);
                k = k + 1;
                break;

            case R.id.button3:

                contentValues.put(MyDB_Helper.KEY_FAMILIYA, "Ivanov");
                contentValues.put(MyDB_Helper.KEY_IMYA, "Ivan");
                contentValues.put(MyDB_Helper.KEY_OTCHESTVO, "Ivanovich");
                database.update(MyDB_Helper.TABLE_STUDENTS, contentValues, MyDB_Helper.KEY_ID + "=" + k, null);

                break;
        }

        dbHelper.close();
    }
}