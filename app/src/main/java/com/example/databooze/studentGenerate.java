package com.example.databooze;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class studentGenerate extends AppCompatActivity implements View.OnClickListener {
    TextView    t11,t12,t13,t14,t15,t16,
                t21,t22,t23,t24,t25,t26,
                t31,t32,t33,t34,t35,t36,
                t41,t42,t43,t44,t45,t46,
                t51,t52,t53,t54,t55,t56;
    SQLiteDatabase dataBooze;
    String courseName="null",facultyName,roomNo,courseCode;
    String section, editAccess;
    List<String> courseNames,courseIds,facultyNames,roomNos;
    ArrayAdapter<String> ad1,ad2,ad3,ad4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_generate);
        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);

        t11 = findViewById(R.id.t11);
        t12 = findViewById(R.id.t12);
        t13 = findViewById(R.id.t13);
        t14 = findViewById(R.id.t14);
        t15 = findViewById(R.id.t15);
        t16 = findViewById(R.id.t16);
        t21 = findViewById(R.id.t21);
        t22 = findViewById(R.id.t22);
        t23 = findViewById(R.id.t23);
        t24 = findViewById(R.id.t24);
        t25 = findViewById(R.id.t25);
        t26 = findViewById(R.id.t26);
        t31 = findViewById(R.id.t31);
        t32 = findViewById(R.id.t32);
        t33 = findViewById(R.id.t33);
        t34 = findViewById(R.id.t34);
        t35 = findViewById(R.id.t35);
        t36 = findViewById(R.id.t36);
        t41 = findViewById(R.id.t41);
        t42 = findViewById(R.id.t42);
        t43 = findViewById(R.id.t43);
        t44 = findViewById(R.id.t44);
        t45 = findViewById(R.id.t45);
        t46 = findViewById(R.id.t46);
        t51 = findViewById(R.id.t51);
        t52 = findViewById(R.id.t52);
        t53 = findViewById(R.id.t53);
        t54 = findViewById(R.id.t54);
        t55 = findViewById(R.id.t55);
        t56 = findViewById(R.id.t56);

        t11.setOnClickListener(this);
        t12.setOnClickListener(this);
        t13.setOnClickListener(this);
        t14.setOnClickListener(this);
        t15.setOnClickListener(this);
        t16.setOnClickListener(this);
        t21.setOnClickListener(this);
        t22.setOnClickListener(this);
        t23.setOnClickListener(this);
        t24.setOnClickListener(this);
        t25.setOnClickListener(this);
        t26.setOnClickListener(this);
        t31.setOnClickListener(this);
        t32.setOnClickListener(this);
        t33.setOnClickListener(this);
        t34.setOnClickListener(this);
        t35.setOnClickListener(this);
        t36.setOnClickListener(this);
        t41.setOnClickListener(this);
        t42.setOnClickListener(this);
        t43.setOnClickListener(this);
        t44.setOnClickListener(this);
        t45.setOnClickListener(this);
        t46.setOnClickListener(this);
        t51.setOnClickListener(this);
        t52.setOnClickListener(this);
        t53.setOnClickListener(this);
        t54.setOnClickListener(this);
        t55.setOnClickListener(this);
        t56.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        section = b.getString("section");
        editAccess = b.getString("editAccess");

        if(editAccess.equals("1"))
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(studentGenerate.this);
            dialog.setTitle("WARNING!");
            dialog.setIcon(R.drawable.warning);
            dialog.setMessage("Modifying table data might lead to clashes in the Time Table.\nProceed at your own risk.");
            dialog.setCancelable(true);
            dialog.setPositiveButton("CONTINUE", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialogInterface, int i)
                {
                    dialogInterface.cancel();
                    Toast.makeText(getApplicationContext(),"Click on slot to change",Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }

        if(section.equals("A"))
        {
            popTable('A');
        }
        else if(section.equals("B"))
        {
            popTable('B');
        }
        else if(section.equals("C"))
        {
            popTable('C');
        }
        else if(section.equals("D"))
        {
            popTable('D');
        }

        courseNames = new ArrayList<>();
        courseIds = new ArrayList<>();
        facultyNames = new ArrayList<>();
        roomNos = new ArrayList<>();
        Cursor c = dataBooze.rawQuery("SELECT course_id,name FROM course", null);
        while(c.moveToNext())
        {
            courseIds.add(c.getString(0));
            courseNames.add(c.getString(1));
        }
        Cursor f = dataBooze.rawQuery("SELECT name FROM faculty", null);
        while(f.moveToNext())
        {
            facultyNames.add(f.getString(0));
        }
        Cursor r = dataBooze.rawQuery("SELECT roomNumber,block FROM classroom", null);
        while(r.moveToNext())
        {
            roomNos.add(r.getString(1)+r.getString(0));
        }

        ad1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_selectable_list_item,courseNames);
        ad2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_selectable_list_item,courseIds);
        ad3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_selectable_list_item,facultyNames);
        ad4 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_selectable_list_item,roomNos);

    }

    public void dialogPrompt(final View view)
    {
        AlertDialog.Builder prompt = new AlertDialog.Builder(studentGenerate.this);
        Context context = studentGenerate.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        final AutoCompleteTextView course = new AutoCompleteTextView(context);
        course.setThreshold(1);
        course.setAdapter(ad1);
        course.setHint("Course Name");
        layout.addView(course);
        final AutoCompleteTextView courseID = new AutoCompleteTextView(context);
        courseID.setThreshold(1);
        courseID.setAdapter(ad2);
        courseID.setHint("Course Code");
        layout.addView(courseID);
        final AutoCompleteTextView faculty = new AutoCompleteTextView(context);
        faculty.setThreshold(1);
        faculty.setAdapter(ad3);
        faculty.setHint("Faculty Name");
        layout.addView(faculty);
        final AutoCompleteTextView room = new AutoCompleteTextView(context);
        room.setThreshold(1);
        room.setAdapter(ad4);
        room.setHint("Room No.");
        layout.addView(room);
        prompt.setCancelable(true);
        prompt.setTitle("Modify");
        prompt.setView(layout);
        prompt.setPositiveButton("SAVE", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                courseName = course.getText().toString();
                facultyName = faculty.getText().toString();
                roomNo = room.getText().toString();
                courseCode = courseID.getText().toString();
                /*Intent prev = new Intent(getApplicationContext(),sixth.class);
                startActivity(prev);*/

                if(view==t11)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '1'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '1' and slot = '1'");
                            t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '1' and slot = '1'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '1' and slot = '1'");
                            t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '1' and slot = '1'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '1' and slot = '1'");
                            t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '1' and slot = '1'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '1' and slot = '1'");
                            t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t12)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '2'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '1' and slot = '2'");
                            t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '1' and slot = '2'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '1' and slot = '2'");
                            t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '1' and slot = '2'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '1' and slot = '2'");
                            t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '1' and slot = '2'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '1' and slot = '2'");
                            t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t13)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '3'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '1' and slot = '3'");
                            t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '1' and slot = '3'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '1' and slot = '3'");
                            t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '1' and slot = '3'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '1' and slot = '3'");
                            t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '1' and slot = '3'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '1' and slot = '3'");
                            t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t14)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '4'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '1' and slot = '4'");
                            t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '1' and slot = '4'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '1' and slot = '4'");
                            t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '1' and slot = '4'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '1' and slot = '4'");
                            t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '1' and slot = '4'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '1' and slot = '4'");
                            t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t15)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '5'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '1' and slot = '5'");
                            t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '1' and slot = '5'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '1' and slot = '5'");
                            t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '1' and slot = '5'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '1' and slot = '5'");
                            t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '1' and slot = '5'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '1' and slot = '5'");
                            t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t16)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '6'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '1' and slot = '6'");
                            t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '1' and slot = '6'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '1' and slot = '6'");
                            t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '1' and slot = '6'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '1' and slot = '6'");
                            t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '1' and slot = '6'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '1' and slot = '6'");
                            t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t21)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '1'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '2' and slot = '1'");
                            t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '2' and slot = '1'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '2' and slot = '1'");
                            t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '2' and slot = '1'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '2' and slot = '1'");
                            t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '2' and slot = '1'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '2' and slot = '1'");
                            t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t22)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '2'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '2' and slot = '2'");
                            t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '2' and slot = '2'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '2' and slot = '2'");
                            t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '2' and slot = '2'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '2' and slot = '2'");
                            t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '2' and slot = '2'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '2' and slot = '2'");
                            t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t23)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '3'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '2' and slot = '3'");
                            t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '2' and slot = '3'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '2' and slot = '3'");
                            t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '2' and slot = '3'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '2' and slot = '3'");
                            t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '2' and slot = '3'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '2' and slot = '3'");
                            t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t24)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '4'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '2' and slot = '4'");
                            t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '2' and slot = '4'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '2' and slot = '4'");
                            t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '2' and slot = '4'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '2' and slot = '4'");
                            t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '2' and slot = '4'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '2' and slot = '4'");
                            t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t25)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '5'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '2' and slot = '5'");
                            t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '2' and slot = '5'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '2' and slot = '5'");
                            t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '2' and slot = '5'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '2' and slot = '5'");
                            t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '2' and slot = '5'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '2' and slot = '5'");
                            t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t26)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '6'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '2' and slot = '6'");
                            t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '2' and slot = '6'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '2' and slot = '6'");
                            t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '2' and slot = '6'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '2' and slot = '6'");
                            t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '2' and slot = '6'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '2' and slot = '6'");
                            t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t31)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '1'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '3' and slot = '1'");
                            t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '3' and slot = '1'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '3' and slot = '1'");
                            t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '3' and slot = '1'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '3' and slot = '1'");
                            t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '3' and slot = '1'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '3' and slot = '1'");
                            t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t32)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '2'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '3' and slot = '2'");
                            t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '3' and slot = '2'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '3' and slot = '2'");
                            t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '3' and slot = '2'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '3' and slot = '2'");
                            t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '3' and slot = '2'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '3' and slot = '2'");
                            t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t33)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '3'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '3' and slot = '3'");
                            t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '3' and slot = '3'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '3' and slot = '3'");
                            t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '3' and slot = '3'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '3' and slot = '3'");
                            t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '3' and slot = '3'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '3' and slot = '3'");
                            t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t34)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '4'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '3' and slot = '4'");
                            t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '3' and slot = '4'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '3' and slot = '4'");
                            t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '3' and slot = '4'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '3' and slot = '4'");
                            t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '3' and slot = '4'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '3' and slot = '4'");
                            t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t35)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '5'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '3' and slot = '5'");
                            t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '3' and slot = '5'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '3' and slot = '5'");
                            t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '3' and slot = '5'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '3' and slot = '5'");
                            t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '3' and slot = '5'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '3' and slot = '5'");
                            t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t36)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '6'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '3' and slot = '6'");
                            t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '3' and slot = '6'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '3' and slot = '6'");
                            t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '3' and slot = '6'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '3' and slot = '6'");
                            t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '3' and slot = '6'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '3' and slot = '6'");
                            t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t41)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '1'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '4' and slot = '1'");
                            t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '4' and slot = '1'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '4' and slot = '1'");
                            t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '4' and slot = '1'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '4' and slot = '1'");
                            t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '4' and slot = '1'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '4' and slot = '1'");
                            t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t42)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '2'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '4' and slot = '2'");
                            t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '4' and slot = '2'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '4' and slot = '2'");
                            t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '4' and slot = '2'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '4' and slot = '2'");
                            t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '4' and slot = '2'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '4' and slot = '2'");
                            t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t43)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '3'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '4' and slot = '3'");
                            t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '4' and slot = '3'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '4' and slot = '3'");
                            t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '4' and slot = '3'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '4' and slot = '3'");
                            t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '4' and slot = '3'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '4' and slot = '3'");
                            t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t44)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '4'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '4' and slot = '4'");
                            t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '4' and slot = '4'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '4' and slot = '4'");
                            t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '4' and slot = '4'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '4' and slot = '4'");
                            t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '4' and slot = '4'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '4' and slot = '4'");
                            t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t45)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '5'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '4' and slot = '5'");
                            t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '4' and slot = '5'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '4' and slot = '5'");
                            t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '4' and slot = '5'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '4' and slot = '5'");
                            t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '4' and slot = '5'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '4' and slot = '5'");
                            t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t46)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '6'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '4' and slot = '6'");
                            t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '4' and slot = '6'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '4' and slot = '6'");
                            t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '4' and slot = '6'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '4' and slot = '6'");
                            t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '4' and slot = '6'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '4' and slot = '6'");
                            t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t51)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '1'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '5' and slot = '1'");
                            t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '5' and slot = '1'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '5' and slot = '1'");
                            t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '5' and slot = '1'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '5' and slot = '1'");
                            t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '5' and slot = '1'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '1'");
                            t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t52)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '2'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '5' and slot = '2'");
                            t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '5' and slot = '2'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '5' and slot = '2'");
                            t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '5' and slot = '2'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '5' and slot = '2'");
                            t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '5' and slot = '2'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '2'");
                            t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t53)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '3'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '5' and slot = '3'");
                            t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '5' and slot = '3'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '5' and slot = '3'");
                            t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '5' and slot = '3'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '5' and slot = '3'");
                            t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '5' and slot = '3'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '3'");
                            t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t54)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '4'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '5' and slot = '4'");
                            t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '5' and slot = '4'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '5' and slot = '4'");
                            t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '5' and slot = '4'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '5' and slot = '4'");
                            t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '5' and slot = '4'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '4'");
                            t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t55)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '5'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '5' and slot = '5'");
                            t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '5' and slot = '5'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '5' and slot = '5'");
                            t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '5' and slot = '5'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '5' and slot = '5'");
                            t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '5' and slot = '5'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '5'");
                            t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
                else if(view==t56)
                {
                    s = 1;
                    if(editAccess.equals("1"))
                        //dialogPrompt();
                    if(section.equals("A")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '6'");
                            popTable('A');
                        }
                        else
                        {
                            dataBooze.execSQL("update seca set course = '"+courseCode+"' where day = '5' and slot = '6'");
                            t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("B")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secb set course = 'FREE' where day = '5' and slot = '6'");
                            popTable('B');
                        }
                        else
                        {
                            dataBooze.execSQL("update secb set course = '"+courseCode+"' where day = '5' and slot = '6'");
                            t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("C")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secc set course = 'FREE' where day = '5' and slot = '6'");
                            popTable('C');
                        }
                        else
                        {
                            dataBooze.execSQL("update secc set course = '"+courseCode+"' where day = '5' and slot = '6'");
                            t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                    else if(section.equals("D")&&editAccess.equals("1")&&(s==1))
                    {
                        if(courseName.equals("FREE"))
                        {
                            dataBooze.execSQL("update secd set course = 'FREE' where day = '5' and slot = '6'");
                            popTable('D');
                        }
                        else
                        {
                            dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '6'");
                            t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                        }
                    }
                }
            }
        });
        prompt.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        prompt.show();
    }

    public void popTable(char sec)
    {
        Cursor c1,c2,c3;
        int slot;
        if(sec=='A')
        {
            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '1' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t11.setText("FREE");
            }
            else 
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '1' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t12.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '1' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t13.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '1' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t14.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '1' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t15.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '1' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t16.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '2' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t21.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '2' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t22.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '2' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t23.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '2' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t24.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '2' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t25.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '2' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t26.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '3' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t31.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '3' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t32.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '3' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t33.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '3' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t34.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '3' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t35.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '3' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t36.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '4' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t41.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '4' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t42.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '4' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t43.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '4' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t44.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '4' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t45.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '4' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t46.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '5' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t51.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '5' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t52.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '5' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t53.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '5' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t54.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '5' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t55.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM seca where day = '5' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t56.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A201";
                else
                    roomNo = "A301";
                t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }
        }
        else if(sec=='B')
        {
            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '1' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t11.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '1' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t12.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '1' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t13.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '1' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t14.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '1' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t15.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '1' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t16.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '2' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t21.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '2' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t22.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '2' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t23.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '2' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t24.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '2' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t25.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '2' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t26.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '3' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t31.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '3' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t32.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '3' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t33.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '3' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t34.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '3' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t35.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '3' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t36.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '4' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t41.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '4' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t42.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '4' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t43.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '4' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t44.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '4' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t45.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '4' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t46.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '5' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t51.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '5' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t52.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '5' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t53.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '5' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t54.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '5' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t55.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secb where day = '5' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t56.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A202";
                else
                    roomNo = "A302";
                t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }
        }
        else if(sec=='C')
        {
            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '1' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t11.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '1' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t12.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '1' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t13.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '1' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t14.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '1' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t15.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '1' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t16.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '2' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t21.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '2' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t22.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '2' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t23.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '2' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t24.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '2' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t25.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '2' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t26.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '3' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t31.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '3' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t32.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '3' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t33.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '3' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t34.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '3' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t35.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '3' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t36.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '4' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t41.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '4' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t42.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '4' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t43.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '4' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t44.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '4' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t45.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '4' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t46.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '5' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t51.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '5' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t52.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '5' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t53.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '5' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t54.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '5' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t55.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secc where day = '5' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t56.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A203";
                else
                    roomNo = "A303";
                t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }
        }
        else if(sec=='D')
        {
            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '1' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t11.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t11.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '1' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t12.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t12.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '1' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t13.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t13.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '1' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t14.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t14.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '1' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t15.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t15.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '1' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t16.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t16.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '2' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t21.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t21.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '2' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t22.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t22.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '2' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t23.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t23.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '2' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t24.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t24.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '2' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t25.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t25.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '2' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t26.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t26.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '3' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t31.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t31.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '3' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t32.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t32.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '3' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t33.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t33.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '3' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t34.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t34.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '3' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t35.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t35.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '3' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t36.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t36.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '4' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t41.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t41.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '4' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t42.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t42.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '4' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t43.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t43.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '4' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t44.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t44.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '4' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t45.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t45.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '4' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t46.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t46.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '5' and slot = '1'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t51.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t51.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '5' and slot = '2'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t52.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t52.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '5' and slot = '3'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t53.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t53.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '5' and slot = '4'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t54.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t54.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '5' and slot = '5'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t55.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t55.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }

            c1 = dataBooze.rawQuery("SELECT course,slot FROM secd where day = '5' and slot = '6'",null);
            c1.moveToNext();
            if(c1.getString(0).equals("FREE"))
            {
                t56.setText("FREE");
            }
            else
            {
                slot = Integer.parseInt(c1.getString(1));
                c2 = dataBooze.rawQuery("SELECT name FROM faculty where course_id = '"+c1.getString(0)+"'",null);
                c2.moveToNext();
                facultyName = c2.getString(0);
                c3 = dataBooze.rawQuery("SELECT name FROM course where course_id = '"+c1.getString(0)+"'",null);
                c3.moveToNext();
                courseName = c3.getString(0);
                if(slot<=3)
                    roomNo = "A204";
                else
                    roomNo = "A304";
                t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
            }
        }
    }
    
    int s;
    
    @Override
    public void onClick(View view)
    {
        if(editAccess.equals("1"))
            dialogPrompt(view);
    }
}
