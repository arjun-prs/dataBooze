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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText userName, passWord;
    Button proceed;
    SQLiteDatabase dataBooze;

    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.button2);
        test.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dataBooze.execSQL("update seca set course='FREE' where day='5' and slot = '6'");
                Cursor c = dataBooze.rawQuery("SELECT * FROM seca", null);
                if (c.getCount() == 0) {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer =
                        new StringBuffer();
                while (c.moveToNext())
                {
                    buffer.append("Day: " + c.getString(0) + "\n");
                    buffer.append("Slot: " + c.getString(1) + "\n");
                    buffer.append("Course: " + c.getString(2) + "\n");
                }
                showMessage("Section A Details", buffer.toString());
            }
        });

        userName=findViewById(R.id.edETUserName);
        passWord=findViewById(R.id.edETPassword);
        proceed=findViewById(R.id.edButProceed);
        proceed.setOnClickListener(this);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        dataBooze.execSQL("create table if not exists users(user_id varchar(18) primary key, password varchar(18));");
        dataBooze.execSQL("create table if not exists student(roll_no varchar(18) primary key, name varchar(18), section varchar(8), dept varchar(18), semester varchar(8), foreign key(roll_no) references users(user_id), foreign key(section) references classes(section));");
        dataBooze.execSQL("create table if not exists faculty(faculty_id varchar(18) primary key, name varchar(18), position varchar(18), dept varchar(18), course_id varchar(8), foreign key(faculty_id) references users(user_id), foreign key(faculty_id) references class(faculty_id), foreign key(course_id) references course(course_id))");
        dataBooze.execSQL("create table if not exists admins(admin_id varchar(18) primary key, foreign key(admin_id) references users(user_id));");

        /*dataBooze.execSQL("drop table seca");
        dataBooze.execSQL("drop table secb");
        dataBooze.execSQL("drop table secc");
        dataBooze.execSQL("drop table secd");
        Toast.makeText(this,"Tables Dropped",Toast.LENGTH_SHORT).show();

        dataBooze.execSQL("create table if not exists seca(day varchar(8), slot varchar(8), course varchar(18))");
        dataBooze.execSQL("create table if not exists secb(day varchar(8), slot varchar(8), course varchar(18))");
        dataBooze.execSQL("create table if not exists secc(day varchar(8), slot varchar(8), course varchar(18))");
        dataBooze.execSQL("create table if not exists secd(day varchar(8), slot varchar(8), course varchar(18))");
        Toast.makeText(this,"Tables Created",Toast.LENGTH_SHORT).show();

        dataBooze.execSQL("insert into seca values('1','1','15CSE301')");
        dataBooze.execSQL("insert into seca values('1','2','15CSE303')");
        dataBooze.execSQL("insert into seca values('1','3','15MAT301')");
        dataBooze.execSQL("insert into seca values('1','4','15CSE375')");
        dataBooze.execSQL("insert into seca values('1','5','15CSE302')");
        dataBooze.execSQL("insert into seca values('1','6','FREE')");

        dataBooze.execSQL("insert into seca values('2','1','15CSE303')");
        dataBooze.execSQL("insert into seca values('2','2','15CSE301')");
        dataBooze.execSQL("insert into seca values('2','3','15CSE375')");
        dataBooze.execSQL("insert into seca values('2','4','15MAT301')");
        dataBooze.execSQL("insert into seca values('2','5','15CSE302')");
        dataBooze.execSQL("insert into seca values('2','6','FREE')");

        dataBooze.execSQL("insert into seca values('3','1','FREE')");
        dataBooze.execSQL("insert into seca values('3','2','15CSE375')");
        dataBooze.execSQL("insert into seca values('3','3','15CSE301')");
        dataBooze.execSQL("insert into seca values('3','4','15CSE302')");
        dataBooze.execSQL("insert into seca values('3','5','15MAT301')");
        dataBooze.execSQL("insert into seca values('3','6','15CSE303')");

        dataBooze.execSQL("insert into seca values('4','1','FREE')");
        dataBooze.execSQL("insert into seca values('4','2','15CSE302')");
        dataBooze.execSQL("insert into seca values('4','3','15MAT301')");
        dataBooze.execSQL("insert into seca values('4','4','15CSE375')");
        dataBooze.execSQL("insert into seca values('4','5','15CSE303')");
        dataBooze.execSQL("insert into seca values('4','6','15CSE301')");

        dataBooze.execSQL("insert into seca values('5','1','15MAT301')");
        dataBooze.execSQL("insert into seca values('5','2','15CSE301')");
        dataBooze.execSQL("insert into seca values('5','3','15CSE303')");
        dataBooze.execSQL("insert into seca values('5','4','FREE')");
        dataBooze.execSQL("insert into seca values('5','5','FREE')");
        dataBooze.execSQL("insert into seca values('5','6','FREE')");

        Toast.makeText(this,"A done",Toast.LENGTH_SHORT).show();

        dataBooze.execSQL("insert into secb values('1','1','15MAT301')");
        dataBooze.execSQL("insert into secb values('1','2','15CSE302')");
        dataBooze.execSQL("insert into secb values('1','3','15CSE301')");
        dataBooze.execSQL("insert into secb values('1','4','15CSE303')");
        dataBooze.execSQL("insert into secb values('1','5','15CSE363')");
        dataBooze.execSQL("insert into secb values('1','6','FREE')");

        dataBooze.execSQL("insert into secb values('2','1','FREE')");
        dataBooze.execSQL("insert into secb values('2','2','15CSE302')");
        dataBooze.execSQL("insert into secb values('2','3','15CSE363')");
        dataBooze.execSQL("insert into secb values('2','4','15CSE301')");
        dataBooze.execSQL("insert into secb values('2','5','15CSE303')");
        dataBooze.execSQL("insert into secb values('2','6','15MAT301')");

        dataBooze.execSQL("insert into secb values('3','1','15CSE302')");
        dataBooze.execSQL("insert into secb values('3','2','FREE')");
        dataBooze.execSQL("insert into secb values('3','3','15MAT301')");
        dataBooze.execSQL("insert into secb values('3','4','15CSE303')");
        dataBooze.execSQL("insert into secb values('3','5','15CSE301')");
        dataBooze.execSQL("insert into secb values('3','6','15CSE363')");

        dataBooze.execSQL("insert into secb values('4','1','FREE')");
        dataBooze.execSQL("insert into secb values('4','2','FREE')");
        dataBooze.execSQL("insert into secb values('4','3','15CSE303')");
        dataBooze.execSQL("insert into secb values('4','4','15CSE301')");
        dataBooze.execSQL("insert into secb values('4','5','15MAT301')");
        dataBooze.execSQL("insert into secb values('4','6','FREE')");

        dataBooze.execSQL("insert into secb values('5','1','15CSE363')");
        dataBooze.execSQL("insert into secb values('5','2','15CSE302')");
        dataBooze.execSQL("insert into secb values('5','3','15MAT301')");
        dataBooze.execSQL("insert into secb values('5','4','15CSE301')");
        dataBooze.execSQL("insert into secb values('5','5','15CSE303')");
        dataBooze.execSQL("insert into secb values('5','6','FREE')");

        Toast.makeText(this,"B done",Toast.LENGTH_SHORT).show();

        dataBooze.execSQL("insert into secc values('1','1','FREE')");
        dataBooze.execSQL("insert into secc values('1','2','15CSE302')");
        dataBooze.execSQL("insert into secc values('1','3','15CSE303')");
        dataBooze.execSQL("insert into secc values('1','4','15MAT301')");
        dataBooze.execSQL("insert into secc values('1','5','15CSE301')");
        dataBooze.execSQL("insert into secc values('1','6','15CSE337')");

        dataBooze.execSQL("insert into secc values('2','1','FREE')");
        dataBooze.execSQL("insert into secc values('2','2','15CSE303')");
        dataBooze.execSQL("insert into secc values('2','3','15CSE337')");
        dataBooze.execSQL("insert into secc values('2','4','15MAT301')");
        dataBooze.execSQL("insert into secc values('2','5','15CSE301')");
        dataBooze.execSQL("insert into secc values('2','6','FREE')");

        dataBooze.execSQL("insert into secc values('3','1','15MAT301')");
        dataBooze.execSQL("insert into secc values('3','2','15CSE303')");
        dataBooze.execSQL("insert into secc values('3','3','FREE')");
        dataBooze.execSQL("insert into secc values('3','4','15CSE302')");
        dataBooze.execSQL("insert into secc values('3','5','15CSE337')");
        dataBooze.execSQL("insert into secc values('3','6','15CSE301')");

        dataBooze.execSQL("insert into secc values('4','1','15CSE301')");
        dataBooze.execSQL("insert into secc values('4','2','15CSE302')");
        dataBooze.execSQL("insert into secc values('4','3','15CSE303')");
        dataBooze.execSQL("insert into secc values('4','4','FREE')");
        dataBooze.execSQL("insert into secc values('4','5','15MAT301')");
        dataBooze.execSQL("insert into secc values('4','6','15CSE337')");

        dataBooze.execSQL("insert into secc values('5','1','15CSE303')");
        dataBooze.execSQL("insert into secc values('5','2','15MAT301')");
        dataBooze.execSQL("insert into secc values('5','3','15CSE302')");
        dataBooze.execSQL("insert into secc values('5','4','15CSE301')");
        dataBooze.execSQL("insert into secc values('5','5','FREE')");
        dataBooze.execSQL("insert into secc values('5','6','FREE')");

        Toast.makeText(this,"C done",Toast.LENGTH_SHORT).show();

        dataBooze.execSQL("insert into secd values('1','1','15MAT301')");
        dataBooze.execSQL("insert into secd values('1','2','15CSE378')");
        dataBooze.execSQL("insert into secd values('1','3','15CSE301')");
        dataBooze.execSQL("insert into secd values('1','4','15CSE302')");
        dataBooze.execSQL("insert into secd values('1','5','15CSE303')");
        dataBooze.execSQL("insert into secd values('1','6','FREE')");

        dataBooze.execSQL("insert into secd values('2','1','15CSE378')");
        dataBooze.execSQL("insert into secd values('2','2','15CSE301')");
        dataBooze.execSQL("insert into secd values('2','3','FREE')");
        dataBooze.execSQL("insert into secd values('2','4','FREE')");
        dataBooze.execSQL("insert into secd values('2','5','15CSE303')");
        dataBooze.execSQL("insert into secd values('2','6','15MAT301')");

        dataBooze.execSQL("insert into secd values('3','1','15CSE301')");
        dataBooze.execSQL("insert into secd values('3','2','15CSE302')");
        dataBooze.execSQL("insert into secd values('3','3','FREE')");
        dataBooze.execSQL("insert into secd values('3','4','FREE')");
        dataBooze.execSQL("insert into secd values('3','5','15CSE303')");
        dataBooze.execSQL("insert into secd values('3','6','15MAT301')");

        dataBooze.execSQL("insert into secd values('4','1','15CSE303')");
        dataBooze.execSQL("insert into secd values('4','2','15CSE378')");
        dataBooze.execSQL("insert into secd values('4','3','15MAT301')");
        dataBooze.execSQL("insert into secd values('4','4','15CSE301')");
        dataBooze.execSQL("insert into secd values('4','5','15CSE302')");
        dataBooze.execSQL("insert into secd values('4','6','FREE')");

        dataBooze.execSQL("insert into secd values('5','1','15CSE302')");
        dataBooze.execSQL("insert into secd values('5','2','15CSE301')");
        dataBooze.execSQL("insert into secd values('5','3','15CSE378')");
        dataBooze.execSQL("insert into secd values('5','4','15CSE303')");
        dataBooze.execSQL("insert into secd values('5','5','15MAT301')");
        dataBooze.execSQL("insert into secd values('5','6','FREE')");

        Toast.makeText(this,"D done",Toast.LENGTH_SHORT).show();*/
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
