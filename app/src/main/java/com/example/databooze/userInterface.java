package com.example.databooze;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userInterface extends AppCompatActivity implements View.OnClickListener {
    TextView studentName, studentRollNo, studentSection, studentDept, studentSemester;
    SQLiteDatabase dataBooze;
    Button generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interface);
        Bundle b = getIntent().getExtras();
        String studentID = b.getString("uName");
        studentName=findViewById(R.id.edTVstudentName);
        studentRollNo=findViewById(R.id.edTVstudentID);
        studentSection=findViewById(R.id.edTVstudentSection);
        studentDept=findViewById(R.id.edTVstudentDept);
        studentSemester=findViewById(R.id.edTVstudentSemester);
        generate=findViewById(R.id.edButGenerateStudentTimeTable);
        generate.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        Cursor c = dataBooze.rawQuery("SELECT * FROM student",null);
        while(c.moveToNext())
        {
            if(c.getString(0).equals(studentID))
            {
                studentRollNo.setText(studentID);
                studentName.setText(c.getString(1));
                studentSection.setText(c.getString(2));
                studentDept.setText(c.getString(3));
                studentSemester.setText(c.getString(4));
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent generate = new Intent(this, studentGenerate.class);
        startActivity(generate);
    }
}
