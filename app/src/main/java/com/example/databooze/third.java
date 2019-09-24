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

public class third extends AppCompatActivity implements View.OnClickListener {

    EditText facultyID, facultyName, facultyDept, facultyPosition, facultyPassword, facultySection, facultyCourseID;
    Button facultyAdd, facultyDelete, facultyModify, facultyShow, facultyShowAll;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        facultyID=findViewById(R.id.edETFacultyID);
        facultyName=findViewById(R.id.edETFacultyName);
        facultyDept=findViewById(R.id.edETFacultyDept);
        facultyPosition=findViewById(R.id.edETFacultyPosition);
        facultySection=findViewById(R.id.edETFacultySection);
        facultyCourseID=findViewById(R.id.edETFacultyCourseID);
        facultyPassword=findViewById(R.id.edETFacultyPassword);
        facultyAdd=findViewById(R.id.edButFacultyAdd);
        facultyDelete=findViewById(R.id.edButFacultyDelete);
        facultyModify=findViewById(R.id.edButFacultyModify);
        facultyShow=findViewById(R.id.edButFacultyShow);
        facultyShowAll=findViewById(R.id.edButFacultyShowAll);
        facultyAdd.setOnClickListener(this);
        facultyDelete.setOnClickListener(this);
        facultyModify.setOnClickListener(this);
        facultyShow.setOnClickListener(this);
        facultyShowAll.setOnClickListener(this);
        dataBooze=openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        dataBooze.execSQL("drop table users");
        dataBooze.execSQL("drop table faculty");
        dataBooze.execSQL("create table if not exists users(user_id varchar(18) primary key, password varchar(18));");
        dataBooze.execSQL("create table if not exists faculty(faculty_id varchar(18) primary key, name varchar(18), position varchar(18), dept varchar(18), section varchar(8), course_id varchar(8), foreign key(faculty_id) references users(user_id), foreign key(section) references class(section), foreign key(course_id) references course(course_id))");

    }
    @Override
    public void onClick(View view)
    {
        if(view == facultyAdd)
        {
            if(facultyID.getText().toString().trim().length()==0||facultyName.getText().toString().trim().length()==0||facultyPosition.getText().toString().trim().length()==0||facultyDept.getText().toString().trim().length()==0||facultySection.getText().toString().trim().length()==0||facultyCourseID.getText().toString().trim().length()==0||facultyPassword.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            dataBooze.execSQL("INSERT INTO faculty VALUES('" + facultyID.getText() + "','" + facultyName.getText() + "','" + facultyPosition.getText() + "','" + facultyDept.getText() + "','" + facultySection.getText() + "','" + facultyCourseID.getText() + "');");
            dataBooze.execSQL("INSERT INTO users VALUES('" + facultyID.getText() + "','" + facultyPassword.getText() + "');");
            showMessage("Success", "Record added");
            clearText();
        }
        if(view == facultyDelete)
        {
            if(facultyID.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter faculty ID");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM faculty WHERE faculty_id='" + facultyID.getText() + "'", null);
            Cursor cu = dataBooze.rawQuery("SELECT *" +
                    " FROM users WHERE user_id='" + facultyID.getText() + "'", null);
            if (c.moveToFirst()) {
                // Deleting record if found 
                //showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM faculty WHERE faculty_id='" +
                        facultyID.getText() + "'");
            }
            if (cu.moveToFirst()) {
                // Deleting record if found 
                showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM users WHERE user_id='" +
                        facultyID.getText() + "'");
            }
            else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        if(view == facultyModify)
        {
            if (facultyID.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Roll Number");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM faculty WHERE faculty_id='" + facultyID.getText() + "'", null);
            Cursor cu = dataBooze.rawQuery("SELECT *" +
                    " FROM users WHERE user_id='" + facultyID.getText() + "'", null);
            if (c.moveToFirst()) {
                // Modifying record if found 
                dataBooze.execSQL("UPDATE faculty SET name='" +
                        facultyName.getText() + "',position='" +
                        facultyPosition.getText() + "',department='" +
                        facultyDept.getText() + "',section='" +
                        facultySection.getText() + "',course_id='" +
                                facultyCourseID.getText() +
                        "' WHERE roll_no='" + facultyID.getText() + "'");
                //showMessage("Success", "Record Modified");
            }
            if (cu.moveToFirst()) {
                // Modifying record if found 
                dataBooze.execSQL("UPDATE users SET password='" +
                        facultyPassword.getText()  +
                        "' WHERE user_id='" + facultyID.getText() + "'");
                showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid Roll Number");
            }
            clearText();
        }
        if(view == facultyShow)
        {
            if (facultyID.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Roll Number");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM faculty WHERE faculty_id='" + facultyID.getText() + "'", null);
            if (c.moveToFirst()) {
                // Displaying record if found 
                facultyName.setText(c.getString(1));
                facultyPosition.setText(c.getString(2));
                facultyDept.setText(c.getString(3));
                facultySection.setText(c.getString(4));
                facultyCourseID.setText(c.getString(5));
            }
            else {
                showMessage("Error", "Invalid Roll Number");
                clearText();
            }
        }
        if(view == facultyShowAll)
        {
            Cursor c = dataBooze.rawQuery("SELECT * FROM faculty", null);
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer =
                    new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("Faculty ID: " + c.getString(0) + "\n");
                buffer.append("Name: " + c.getString(1) + "\n");
                buffer.append("Position " + c.getString(2) + "\n");
                buffer.append("Department: " + c.getString(3) + "\n");
                buffer.append("Section: " + c.getString(4) + "\n");
                buffer.append("Course ID: " + c.getString(5) + "\n\n");
            }
            showMessage("Faculty Details", buffer.toString());
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

        facultyID.setText("");
        facultyName.setText("");
        facultyDept.setText("");
        facultyPosition.setText("");
        facultySection.setText("");
        facultyCourseID.setText("");
        facultyPassword.setText("");
    }
}
