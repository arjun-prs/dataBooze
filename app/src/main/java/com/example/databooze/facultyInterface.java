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

public class facultyInterface extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase dataBooze;
    TextView facultyName, facultyIDTV, facultyPosition, facultyDept, facultyCourseID;
    Button generateFaculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_interface);
        Bundle b = getIntent().getExtras();
        String facultyID = b.getString("uName");
        facultyName=findViewById(R.id.edTVfacultyName);
        facultyIDTV=findViewById(R.id.edTVfacultyID);
        facultyPosition=findViewById(R.id.edTVfacultyPosition);
        facultyDept=findViewById(R.id.edTVfacultyDept);
        facultyCourseID=findViewById(R.id.edTVfacultyCourseID);
        generateFaculty=findViewById(R.id.edButGenerateFacultyTimeTable);
        generateFaculty.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        Cursor c = dataBooze.rawQuery("SELECT * FROM faculty",null);
        while(c.moveToNext())
        {
            if(c.getString(0).equals(facultyID))
            {
                facultyIDTV.setText(facultyID);
                facultyName.setText(c.getString(1));
                facultyPosition.setText(c.getString(2));
                facultyDept.setText(c.getString(3));
                facultyCourseID.setText(c.getString(4));
            }
        }
    }

    @Override
    public void onClick(View v) {
        Intent generate = new Intent(this, facultyGenerate.class);
        generate.putExtra("courseID",facultyCourseID.getText().toString());
        generate.putExtra("facultyID",facultyIDTV.getText().toString());
        generate.putExtra("dept",facultyDept.getText().toString());
        startActivity(generate);
    }
}
