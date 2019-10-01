package com.example.databooze;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class second extends AppCompatActivity
        implements View.OnClickListener {

    EditText studentName, studentRollNo, studentSection, studentDept, studentSemester, studentPassword;
    Button studentAdd, studentDelete, studentModify, studentShowAll, studentShow;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        studentRollNo = findViewById(R.id.edETRollNo);
        studentName = findViewById(R.id.edETStudentName);
        studentAdd = findViewById(R.id.edButStudentAdd);
        studentDelete = findViewById(R.id.edButStudentDelete);
        studentModify = findViewById(R.id.edButStudentModify);
        studentShow = findViewById(R.id.edButStudentShow);
        studentShowAll = findViewById(R.id.edButStudentShowAll);
        studentDept=findViewById(R.id.edETStudentDept);
        studentSection=findViewById(R.id.edETStudentSection);
        studentSemester=findViewById(R.id.edETStudentSemester);
        studentPassword=findViewById(R.id.edETStudentPassword);
        //Registering Event Handlers
        studentAdd.setOnClickListener(this);
        studentDelete.setOnClickListener(this);
        studentModify.setOnClickListener(this);
        studentShow.setOnClickListener(this);
        studentShowAll.setOnClickListener(this);
        // Creating database and table  
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        //dataBooze.execSQL("drop table users");
        //dataBooze.execSQL("drop table student");
        dataBooze.execSQL("create table if not exists users(user_id varchar(18) primary key, password varchar(18));");
        dataBooze.execSQL("create table if not exists student(roll_no varchar(18) primary key, name varchar(18), section varchar(8), dept varchar(18), semester varchar(8), foreign key(roll_no) references users(user_id), foreign key(section) references classes(section));");
    }

    @Override
    public void onClick(View view) {
        // Adding a record
        if (view == studentAdd) {
            // Checking empty fields
            if (studentRollNo.getText().toString().trim().length() == 0 ||
                    studentName.getText().toString().trim().length() == 0 ||
                    studentSemester.getText().toString().trim().length() == 0 ||
                    studentSection.getText().toString().trim().length() == 0 ||
                    studentDept.getText().toString().trim().length() == 0 ||
                    studentPassword.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            // Inserting record 
            dataBooze.execSQL("INSERT INTO student VALUES('" + studentRollNo.getText() + "','" + studentName.getText() +
                    "','" + studentSection.getText() + "','" + studentDept.getText() + "','" + studentSemester.getText() +  "');");
            dataBooze.execSQL("INSERT INTO users VALUES('"+ studentRollNo.getText() + "','" + studentPassword.getText() + "');");
                    showMessage("Success", "Record added");
            clearText();
        }
        // Deleting a record 
        if (view == studentDelete) {
            // Checking empty roll number 
            if (studentRollNo.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Roll Number...");
                return;
            }
            // Searching roll number 
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM student WHERE roll_no='" + studentRollNo.getText() + "'", null);
            Cursor cu = dataBooze.rawQuery("SELECT *" +
                    " FROM users WHERE user_id='" + studentRollNo.getText() + "'", null);
            if (c.moveToFirst()) {
                // Deleting record if found 
                //showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM student WHERE roll_no='" +
                        studentRollNo.getText() + "'");
            }
            if (cu.moveToFirst()) {
                // Deleting record if found 
                showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM users WHERE user_id='" +
                        studentRollNo.getText() + "'");
            }
            else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }
        // Modifying a record 
        if (view == studentModify) {
            // Checking empty roll number 
            if (studentRollNo.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Roll Number");
                return;
            }
            // Searching roll number 
            Cursor c = dataBooze.rawQuery("SELECT * FROM student WHERE roll_no='" + studentRollNo.getText() + "'", null);
            Cursor cu = dataBooze.rawQuery("SELECT * FROM users WHERE user_id='" + studentRollNo.getText() + "'", null);
            if (c.moveToFirst()) {
                // Modifying record if found 
                dataBooze.execSQL("UPDATE student SET name='" +
                        studentName.getText() + "',section='" +
                        studentSection.getText() + "',department='" +
                                studentDept.getText() + "',semester='" +
                                studentSemester.getText() +
                        "' WHERE roll_no='" + studentRollNo.getText() + "'");
                //showMessage("Success", "Record Modified");
            }
            if (cu.moveToFirst()) {
                // Modifying record if found 
                dataBooze.execSQL("UPDATE users SET password='" +
                        studentPassword.getText()  +
                        "' WHERE user_id='" + studentRollNo.getText() + "'");
                showMessage("Success", "Record Modified");
            }
            else {
                showMessage("Error", "Invalid Roll Number");
            }
            clearText();
        }
        // Viewing a record 
        if (view == studentShow) {
            // Checking empty roll number 
            if (studentRollNo.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Roll Number");
                return;
            }
            // Searching roll number 
            Cursor c = dataBooze.rawQuery
                    ("SELECT * FROM student" +
                            " WHERE roll_no='" +
                            studentRollNo.getText()
                            + "'", null);

            if (c.moveToFirst()) {
                // Displaying record if found 
                studentName.setText(c.getString(1));
                studentSection.setText(c.getString(2));
                studentDept.setText(c.getString(3));
                studentSemester.setText(c.getString(4));

            } else {
                showMessage("Error", "Invalid Roll Number");
                clearText();
            }
        }
        // Viewing all records 
        if (view == studentShowAll) {
            // Retrieving all records 
            Cursor c = dataBooze.rawQuery
                    ("SELECT * FROM student",
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
                buffer.append("Roll Number: " + c.getString(0) + "\n");
                buffer.append("Name: " + c.getString(1) + "\n");
                buffer.append("Section: " + c.getString(2) + "\n");
                buffer.append("Department: " + c.getString(3) + "\n");
                buffer.append("Semester: " + c.getString(4) + "\n\n");
            }
            // Displaying all records 
            showMessage("Student Details", buffer.toString());
        }
        // Displaying info 
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){

        studentRollNo.setText("");
        studentName.setText("");
        studentSection.setText("");
        studentDept.setText("");
        studentSemester.setText("");
        studentPassword.setText("");
        studentRollNo.requestFocus();
    }
}




