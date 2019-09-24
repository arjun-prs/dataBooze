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

public class fifth extends AppCompatActivity implements View.OnClickListener {

    EditText section, numberOfGirls, numberOfBoys, facultyID;
    Button classAdd, classDelete, classModify, classShow, classShowAll;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        section=findViewById(R.id.edETClassSection);
        numberOfBoys=findViewById(R.id.edETClassNumberOfBoys);
        numberOfGirls=findViewById(R.id.edETClassNumberOfGirls);
        facultyID=findViewById(R.id.edETClassFacultyID);
        classAdd=findViewById(R.id.edButClassAdd);
        classDelete=findViewById(R.id.edButClassDelete);
        classModify=findViewById(R.id.edButClassModify);
        classShow=findViewById(R.id.edButClassShow);
        classShowAll=findViewById(R.id.edButClassShowAll);
        classAdd.setOnClickListener(this);
        classDelete.setOnClickListener(this);
        classModify.setOnClickListener(this);
        classShow.setOnClickListener(this);
        classShowAll.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        //dataBooze.execSQL("DROP TABLE class");
        dataBooze.execSQL("CREATE TABLE IF NOT EXISTS class(section varchar(8) primary key, numberOfBoys varchar(8), numberOfGirls varchar(8), strength varchar(8), faculty_id varchar(18))");

    }


    @Override
    public void onClick(View v) {

        if(v ==  classAdd)
        {
            if(section.getText().toString().trim().length()==0||numberOfBoys.getText().toString().trim().length()==0||numberOfGirls.getText().toString().trim().length()==0||facultyID.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            int boys = Integer.parseInt(numberOfBoys.getText().toString());
            int girls = Integer.parseInt(numberOfGirls.getText().toString());
            int strength = boys + girls;
            String strengthString;
            strengthString = String.valueOf(strength);
            dataBooze.execSQL("INSERT INTO class VALUES('" + section.getText() + "','" + numberOfBoys.getText() + "','" + numberOfGirls.getText() + "','" + strengthString + "','" + facultyID.getText() + "')");
            showMessage("Success", "Record added");
            clearText();
        }
        if(v == classDelete)
        {
            if(section.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Section...");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM class WHERE section='" + section.getText() + "'", null);
            if (c.moveToFirst()) {
                // Deleting record if found 
                //showMessage("Success", "Record Deleted");
                dataBooze.execSQL("DELETE FROM class WHERE roll_no='" +
                        section.getText() + "'");
            }
            else {
                showMessage("Error", "Invalid Section");
            }
            clearText();
        }
        if(v == classModify)
        {
            if (section.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Section");
                return;
            }
            Cursor c = dataBooze.rawQuery("SELECT *" +
                    " FROM class WHERE section='" + section.getText() + "'", null);
            if (c.moveToFirst()) {
                // Modifying record if found 
                int boys = Integer.parseInt(numberOfBoys.getText().toString());
                int girls = Integer.parseInt(numberOfGirls.getText().toString());
                int strength = boys + girls;
                String strengthString;
                strengthString = String.valueOf(strength);
                dataBooze.execSQL("UPDATE class SET numberOfBoys='" +
                        numberOfBoys.getText() + "',numberOfGirls='" +
                        numberOfGirls.getText() + "', strength='" + strengthString + "', faculty_id='" +
                        facultyID.getText() +
                        "' WHERE section='" + section.getText() + "'");
            }
            else {
                showMessage("Error", "Invalid Section");
            }
            clearText();
        }
        if(v == classShow)
        {
            if(section.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter Section...");
                return;
            }
            Cursor c = dataBooze.rawQuery
                    ("SELECT * FROM class" +
                            " WHERE section='" +
                            section.getText()
                            + "'", null);
            if (c.moveToFirst()) {
                // Displaying record if found 
                numberOfBoys.setText(c.getString(1));
                numberOfGirls.setText(c.getString(2));
                facultyID.setText(c.getString(3));
            }
            else {
                showMessage("Error", "Invalid Section");
                clearText();
            }
        }
        if(v == classShowAll)
        {
            Cursor c = dataBooze.rawQuery
                    ("SELECT * FROM class",
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
                buffer.append("Section: " + c.getString(0) + "\n");
                buffer.append("Number Of Boys: " + c.getString(1) + "\n");
                buffer.append("Number of Girls " + c.getString(2) + "\n");
                buffer.append("Strength: " + c.getString(3) + "\n");
                buffer.append("Faculty ID: " + c.getString(4) + "\n\n");
            }
            // Displaying all records 
            showMessage("Class Details", buffer.toString());
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

        section.setText("");
        numberOfBoys.setText("");
        numberOfGirls.setText("");
        facultyID.setText("");
        section.requestFocus();
    }
}
