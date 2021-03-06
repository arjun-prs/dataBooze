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
import android.widget.Toast;

public class Admin_Interface extends AppCompatActivity implements View.OnClickListener {
    Button student, faculty, admin, classes, classroom, course, allotment;
    TextView adminTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String adminName = b.getString("uName");
        setContentView(R.layout.activity_admin__interface);
        student=findViewById(R.id.edButProceedStudent);
        faculty=findViewById(R.id.edButProceedFaculty);
        admin=findViewById(R.id.edButProceedAdmin);
        //classes=findViewById(R.id.edButProceedClass);
        classroom=findViewById(R.id.edButProceedClassroom);
        course=findViewById(R.id.edButProceedCourse);
        allotment=findViewById(R.id.edButProceedAllotment);
        adminTV=findViewById(R.id.edTVadminWelcome);
        student.setOnClickListener(this);
        faculty.setOnClickListener(this);
        admin.setOnClickListener(this);
        //classes.setOnClickListener(this);
        classroom.setOnClickListener(this);
        course.setOnClickListener(this);
        allotment.setOnClickListener(this);
        adminTV.setText(adminName);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.edButProceedStudent)
        {
            Intent second=new Intent(this,second.class);
            startActivity(second);
        }
        if(v.getId()==R.id.edButProceedFaculty)
        {
            Intent third = new Intent(this, third.class);
            startActivity(third);
        }
        if(v.getId()==R.id.edButProceedAdmin)
        {
            //Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
            Intent fourth = new Intent(this, fourth.class);
            startActivity(fourth);
        }
        /*if(v.getId()==R.id.edButProceedClass)
        {
            Intent fifth = new Intent(this, fifth.class);
            startActivity(fifth);
        }*/
        if(v.getId()==R.id.edButProceedClassroom)
        {
            Intent eighth = new Intent(this, eighth.class);
            startActivity(eighth);
        }
        if(v.getId()==R.id.edButProceedCourse)
        {
            Intent seventh = new Intent(this,seventh.class);
            startActivity(seventh);
        }
        if(v.getId()==R.id.edButProceedAllotment)
        {
            Intent sixth = new Intent(this, sixth.class);
            startActivity(sixth);
        }
    }
}
