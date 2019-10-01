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

public class eighth extends AppCompatActivity implements View.OnClickListener {

    EditText roomNumber, block, capacity;
    Button classroomAdd, classroomDelete, classroomModify, classroomShow, classroomShowAll;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eighth);
        roomNumber=findViewById(R.id.edETClassRoomNo);
        block=findViewById(R.id.edETClassRoomBlock);
        capacity=findViewById(R.id.edETClassRoomCapacity);
        classroomAdd=findViewById(R.id.edButClassRoomAdd);
        classroomDelete=findViewById(R.id.edButClassRoomDelete);
        classroomModify=findViewById(R.id.edButClassRoomModify);
        classroomShow=findViewById(R.id.edButClassRoomShow);
        classroomShowAll=findViewById(R.id.edButClassRoomShowAll);
        classroomAdd.setOnClickListener(this);
        classroomDelete.setOnClickListener(this);
        classroomModify.setOnClickListener(this);
        classroomShow.setOnClickListener(this);
        classroomShowAll.setOnClickListener(this);
        dataBooze=openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        //dataBooze.execSQL("DROP TABLE classroom");
        dataBooze.execSQL("CREATE TABLE IF NOT EXISTS classroom (roomNumber varchar(8) primary key, block varchar(8), capacity varchar(2))");

    }

    @Override
    public void onClick(View v) {
        if(v == classroomAdd)
        {
            if(roomNumber.getText().toString().trim().length()==0||block.getText().toString().trim().length()==0||capacity.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            dataBooze.execSQL("INSERT INTO classroom VALUES ('" + roomNumber.getText() + "','" + block.getText() + "','" + capacity.getText() + "');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(v == classroomDelete)
        {
            if(roomNumber.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Room Number");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM classroom WHERE faculty_id='" + roomNumber.getText() + "'", null);
            if (c.moveToFirst()) {
                // Deleting record if found 
                //showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM classroom WHERE roomNumber='" +
                        roomNumber.getText() + "'");
            }
            else {
                showMessage("Error", "Invalid Room Number");
            }
            clearText();
        }
        if(v == classroomModify)
        {
            if(roomNumber.getText().toString().trim().length()==0||block.getText().toString().trim().length()==0||capacity.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM classroom WHERE faculty_id='" + roomNumber.getText() + "'", null);
            if (c.moveToFirst()) {
                // Modifying record if found 
                dataBooze.execSQL("UPDATE classroom SET roomNumber='" +
                        roomNumber.getText() + "',block='" +
                        block.getText() + "',capacity='" +
                        capacity.getText()  +
                        "' WHERE roll_no='" + roomNumber.getText() + "'");
                //showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid Room Number");
            }
            clearText();
        }
        if(v == classroomShow)
        {
            if(roomNumber.getText().toString().trim().length()==0||block.getText().toString().trim().length()==0||capacity.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM classroom WHERE faculty_id='" + roomNumber.getText() + "'", null);
            if (c.moveToFirst()) {
                // Displaying record if found 
                block.setText(c.getString(1));
                capacity.setText(c.getString(2));
            }
            else {
                showMessage("Error", "Invalid Room Number");
                clearText();
            }

        }
        if(v == classroomShowAll)
        {
            Cursor c = dataBooze.rawQuery("SELECT * FROM classroom", null);
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer =
                    new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("Room Number: " + c.getString(0) + "\n");
                buffer.append("Block: " + c.getString(1) + "\n");
                buffer.append("Capacity " + c.getString(2) + "\n\n");
            }
            showMessage("Class Room Details: ", buffer.toString());
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

        roomNumber.setText("");
        block.setText("");
        capacity.setText("");
        roomNumber.setText("");
    }
}
