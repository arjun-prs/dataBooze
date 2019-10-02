package com.example.databooze;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText userName, passWord;
    Button proceed;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.edETUserName);
        passWord=findViewById(R.id.edETPassword);
        proceed=findViewById(R.id.edButProceed);
        proceed.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        dataBooze.execSQL("create table if not exists users(user_id varchar(18) primary key, password varchar(18));");
        dataBooze.execSQL("create table if not exists student(roll_no varchar(18) primary key, name varchar(18), section varchar(8), dept varchar(18), semester varchar(8), foreign key(roll_no) references users(user_id), foreign key(section) references classes(section));");
        dataBooze.execSQL("create table if not exists faculty(faculty_id varchar(18) primary key, name varchar(18), position varchar(18), dept varchar(18), course_id varchar(8), foreign key(faculty_id) references users(user_id), foreign key(faculty_id) references class(faculty_id), foreign key(course_id) references course(course_id))");
        dataBooze.execSQL("create table if not exists admins(admin_id varchar(18) primary key, foreign key(admin_id) references users(user_id));");
    }

    @Override
    public void onClick(View v)
    {
        int i=0;
        String uName = userName.getText().toString();
        String pass = passWord.getText().toString();
        Cursor c = dataBooze.rawQuery
                ("SELECT * FROM users",
                        null);
        Cursor c1 = dataBooze.rawQuery
                ("SELECT * FROM admins",
                        null);
        Cursor c2 = dataBooze.rawQuery
                ("SELECT * FROM faculty",
                        null);
        Cursor c3 = dataBooze.rawQuery
                ("SELECT * FROM student",
                        null);
        if(uName.toString().equals("ADMIN000"))
        {
            Intent adminInterface=new Intent(this, Admin_Interface.class); //only for admins...
            adminInterface.putExtra("uName", uName);
            startActivity(adminInterface);
        }
        else
        {
            while (c.moveToNext())
            {
                i=0;
                if(c.getString(1).equals(pass.toString())&&c.getString(0).equals(uName.toString()))
                {
                    while((c1.moveToNext()))
                    {
                        if ((c1.getString(0).equals(uName.toString())))
                        {
                            i++;
                            Intent adminInterface = new Intent(this, Admin_Interface.class); //only for admins...
                            adminInterface.putExtra("uName", uName);
                            startActivity(adminInterface);
                            break;
                        }
                    }
                    while(c2.moveToNext())
                    {
                        if (c2.getString(0).equals(uName.toString()))
                        {
                            i++;
                            Intent facultyInterface = new Intent(this, facultyInterface.class);
                            facultyInterface.putExtra("uName", uName);
                            startActivity(facultyInterface);
                            break;
                        }
                    }
                    while(c3.moveToNext())
                    {
                        if (c3.getString(0).equals(uName.toString()))
                        {
                            i++;
                            Intent userInterface = new Intent(this, userInterface.class);
                            userInterface.putExtra("uName", uName);
                            startActivity(userInterface);
                            break;
                        }
                    }
                }
                if(i!=0)
                {
                    break;
                }
            }
        }
        if(i==0)
        {
            showMessage("Error", "Enter Correct Credentials");
        }
    }
    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        userName.setText("");
        passWord.setText("");
        userName.requestFocus();
    }
}
