package com.example.laba_3;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity {
    MyDB_Helper dbHelper_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        TextView text_id = (TextView)findViewById(R.id.text_id);
        TextView text_fam = (TextView)findViewById(R.id.text_fam);
        TextView text_imya = (TextView)findViewById(R.id.text_imya);
        TextView text_otch = (TextView)findViewById(R.id.text_otch);
        TextView text_date = (TextView)findViewById(R.id.text_date);
        text_id.append("№\n");
        text_fam.append("Familiya\n");
        text_imya.append("Imya \n");
        text_otch.append("Otchestvo\n");
        text_date.append("Vremya \n");
        dbHelper_2 = new MyDB_Helper(this);
        SQLiteDatabase database_2 = dbHelper_2.getWritableDatabase();
        String query = "SELECT " + MyDB_Helper.KEY_ID + ", "
                + MyDB_Helper.KEY_FAMILIYA + ", " + MyDB_Helper.KEY_IMYA + ", " + MyDB_Helper.KEY_OTCHESTVO + ", "
                + MyDB_Helper.KEY_DATE + " FROM " + MyDB_Helper.TABLE_STUDENTS;
        Cursor cursor = database_2.rawQuery(query, null);
        int idIndex = cursor.getColumnIndex(MyDB_Helper.KEY_ID);
        int famIndex = cursor.getColumnIndex(MyDB_Helper.KEY_FAMILIYA);
        int imyaIndex = cursor.getColumnIndex(MyDB_Helper.KEY_IMYA);
        int otchIndex = cursor.getColumnIndex(MyDB_Helper.KEY_OTCHESTVO);
        int dateIndex = cursor.getColumnIndex(MyDB_Helper.KEY_DATE);
        //catCursor.moveToFirst();
        String id_2;
        String fam_2;
        String imya_2;
        String otch_2;
        String date_2;
        while (cursor.moveToNext()) {

            id_2 = cursor.getString(idIndex);
            text_id.append(id_2 + " \n");
            fam_2 = cursor.getString(famIndex);
            text_fam.append(fam_2 + " \n");
            imya_2 = cursor.getString(imyaIndex);
            text_imya.append(imya_2 + " \n");
            otch_2 = cursor.getString(otchIndex);
            text_otch.append(otch_2 + " \n");
            date_2 = cursor.getString(dateIndex);
            text_date.append(date_2 + " \n");
        }
        cursor.close();
    }


}
