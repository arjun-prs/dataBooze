package com.example.databooze;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class fourth extends AppCompatActivity implements View.OnClickListener {
    EditText adminID, adminPassWord;
    Button adminAdd, adminDelete, adminShowAll;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        adminID=findViewById(R.id.edETAdminID);
        adminAdd=findViewById(R.id.edButAdminAdd);
        adminDelete=findViewById(R.id.edButAdminDelete);
        adminShowAll=findViewById(R.id.edButAdminShowAll);
        adminPassWord=findViewById(R.id.edETAdminPassword);
        adminAdd.setOnClickListener(this);
        adminDelete.setOnClickListener(this);
        adminShowAll.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        //dataBooze.execSQL("drop table admins");
        //dataBooze.execSQL("drop table users");
        dataBooze.execSQL("create table if not exists users(user_id varchar(18) primary key, password varchar(18));");
        dataBooze.execSQL("create table if not exists admins(admin_id varchar(18) primary key, foreign key(admin_id) references users(user_id));");

    }

    @Override
    public void onClick(View v) {
        if(v == adminAdd) {
            if (adminID.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            } else {
                dataBooze.execSQL("insert into users values ('" + adminID.getText() + "','" + adminPassWord.getText() + "');");
                dataBooze.execSQL("insert into admins values ('" + adminID.getText() + "');");
                showMessage("Success", "Record added");
                clearText();
            }
        }
            if(v == adminDelete)
            {
                if(adminID.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Admin ID...");
                    return;
                }
                else
                {
                    Cursor c = dataBooze.rawQuery("SELECT *" +
                            " FROM admins WHERE admin_id='" + adminID.getText() + "'", null);
                    Cursor cu = dataBooze.rawQuery("SELECT *" +
                            " FROM users WHERE user_id='" + adminID.getText() + "'", null);
                    if (c.moveToFirst()) {
                        // Deleting record if found 
                        //showMessage("Success", "Record Deleted");
                        dataBooze.execSQL("DELETE FROM admins WHERE admin_id='" +
                                adminID.getText() + "'");
                    }
                    if (cu.moveToFirst()) {
                        // Deleting record if found 
                        showMessage("Success", "Record Deleted");
                        dataBooze.execSQL("DELETE FROM users WHERE user_id='" +
                                adminID.getText() + "'");
                    }
                    else {
                        showMessage("Error", "Invalid Admin ID");
                    }
                    clearText();
                }
                }
            if(v == adminShowAll)
            {
                Cursor c = dataBooze.rawQuery
                        ("SELECT * FROM admins",
                                null);
                // Checking if no records found 
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                // Appending records to a string buffer 
                StringBuffer buffer =
                        new StringBuffer();
                while (c.moveToNext())
                {
                    buffer.append("Admin ID: " + c.getString(0) + "\n");
                }
                // Displaying all records 
                showMessage("Admin Details", buffer.toString());
            // Displaying info 
            }



    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){

        adminID.setText("");
        adminPassWord.setText("");
        adminID.requestFocus();
    }
}
