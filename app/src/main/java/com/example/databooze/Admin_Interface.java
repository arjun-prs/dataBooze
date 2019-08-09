package com.example.databooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Admin_Interface extends AppCompatActivity implements View.OnClickListener {
    Button student, faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__interface);
        student=findViewById(R.id.edButProceedStudent);
        faculty=findViewById(R.id.edButProceedFaculty);
        student.setOnClickListener(this);
        faculty.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.edButProceedStudent)
        {
            Intent second=new Intent(this,second.class);
            startActivity(second);
        }

    }
}
