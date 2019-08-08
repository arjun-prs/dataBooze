package com.example.databooze;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class second extends AppCompatActivity implements View.OnClickListener {
    EditText AdminID, AdminPassWord;
    Button save, show;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        AdminID=findViewById(R.id.edETAdminID);
        AdminPassWord=findViewById(R.id.edETAdminPassword);
        save=findViewById(R.id.edButAdminSave);
        save.setOnClickListener(this);
        dataBooze=openOrCreateDatabase("StudentDB", MODE_PRIVATE, null);
        dataBooze.execSQL("create table if not exists users(user_id varchar(18) primary key, password varchar(18));");
        dataBooze.execSQL("create table if not exists admins(admin_id varchar(18) primary key, password varchar(18), foreign key(admin_id) references users(user_id));");


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edButAdminSave) {
            if (AdminID.getText().toString().trim().length() == 0 || AdminPassWord.getText().toString().trim().length() == 0)
                Toast.makeText(this, "Error! Please Enter All Values...", Toast.LENGTH_LONG).show();
            else {
                dataBooze.execSQL("insert into users values('" + AdminID.getText() + "','" + AdminPassWord.getText() + "');");
                Toast.makeText(this, "Success!!!", Toast.LENGTH_SHORT).show();
                AdminID.setText("");
                AdminPassWord.setText("");
            }
        }
        else if (v.getId() == R.id.edButAdminShow) {
            Cursor c = dataBooze.rawQuery("select * from users", null);
            if (c.getCount() == 0) {
                Toast.makeText(this, "Error No records found", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer =
                    new StringBuffer();
            while (c.moveToNext()) {
                buffer.append("Admin ID: " + c.getString(0) + "\n");
                buffer.append("Password: " + c.getString(1) + "\n");
                Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
