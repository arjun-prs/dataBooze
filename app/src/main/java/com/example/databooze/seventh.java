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

public class seventh extends AppCompatActivity implements View.OnClickListener {
    EditText courseID, courseName, courseCredits, courseSemester;
    Button courseAdd, courseDelete, courseModify, courseShow, courseShowAll;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        courseID=findViewById(R.id.edETCourseID);
        courseName=findViewById(R.id.edETCourseName);
        courseCredits=findViewById(R.id.edETCourseCredits);
        courseSemester=findViewById(R.id.edETCourseSemester);
        courseAdd=findViewById(R.id.edButCourseAdd);
        courseDelete=findViewById(R.id.edButCourseDelete);
        courseModify=findViewById(R.id.edButCourseModify);
        courseShow=findViewById(R.id.edButCourseShow);
        courseShowAll=findViewById(R.id.edButCourseShowAll);
        courseAdd.setOnClickListener(this);
        courseDelete.setOnClickListener(this);
        courseModify.setOnClickListener(this);
        courseShow.setOnClickListener(this);
        courseShowAll.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        //dataBooze.execSQL("DROP TABLE COURSE");
        dataBooze.execSQL("CREATE TABLE IF NOT EXISTS course (course_id varchar(8), name varchar(18), credits varchar(8), semester varchar(8))");

    }

    @Override
    public void onClick(View v) {

        if(v == courseAdd)
        {
            if(courseID.getText().toString().trim().length()==0||courseName.getText().toString().trim().length()==0||courseCredits.getText().toString().trim().length()==0||courseSemester.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            dataBooze.execSQL("INSERT INTO course VALUES('" + courseID.getText() + "','" + courseName.getText() + "','" + courseCredits.getText() + "','" + courseSemester.getText() + "')");
            showMessage("Success", "Record added");
            clearText();
        }
        if(v == courseDelete)
        {
            if(courseID.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Course ID...");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM course WHERE course_id='" + courseID.getText() + "'", null);
            if (c.moveToFirst()) {
                // Deleting record if found 
                //showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM course WHERE course_id='" +
                        courseID.getText() + "'");
            }

            else {
                showMessage("Error", "Invalid Course ID");
            }
            clearText();
        }
        if(v == courseModify)
        {
            if(courseID.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter Course ID...");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM course WHERE course_id='" + courseID.getText() + "'", null);
            if (c.moveToFirst()) {
                dataBooze.execSQL("UPDATE course SET name='" +
                        courseName.getText() + "',credits='" +
                        courseCredits.getText() + "', semester='" + courseSemester.getText() +
                        "' WHERE section='" + courseID.getText() + "'");
            }
            else {
            showMessage("Error", "Invalid Course ID");
            }
            clearText();
        }
        if(v == courseShow)
        {
            if(courseID.getText().toString().trim().length()==0) {
                showMessage("Error", "Please enter Course ID...");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM course WHERE course_id='" + courseID.getText() + "'", null);
            if (c.moveToFirst()) {
                // Displaying record if found 
                courseName.setText(c.getString(1));
                courseCredits.setText(c.getString(2));
                courseSemester.setText(c.getString(3));
            }
            else {
                showMessage("Error", "Invalid Course ID");
                clearText();
            }
        }

        if(v == courseShowAll)
        {
            Cursor c = dataBooze.rawQuery
                    ("SELECT * FROM course",
                            null);
            // Checking if no records found 
            if (c.getCount() == 0) {
                showMessage("Error", "No records found");
                return;
            }
            StringBuffer buffer =
                    new StringBuffer();
            while (c.moveToNext())
            {
                buffer.append("Course ID: " + c.getString(0) + "\n");
                buffer.append("Course Name: " + c.getString(1) + "\n");
                buffer.append("Course Credits " + c.getString(2) + "\n");
                buffer.append("Course Semester: " + c.getString(3) + "\n\n");
            }
            // Displaying all records 
            showMessage("Course Details", buffer.toString());
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

        courseID.setText("");
        courseName.setText("");
        courseCredits.setText("");
        courseSemester.setText("");
        courseID.requestFocus();
    }
}
