package com.example.databooze;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class studentGenerate extends AppCompatActivity implements View.OnClickListener {
    TextView    t11,t12,t13,t14,t15,t16,
                t21,t22,t23,t24,t25,t26,
                t31,t32,t33,t34,t35,t36,
                t41,t42,t43,t44,t45,t46,
                t51,t52,t53,t54,t55,t56;
    SQLiteDatabase dataBooze;
    String courseName="null",facultyName,roomNo,courseCode;
    String section, editAccess;
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

        if(section.equals("B"))
        {
            //Monday
            t11.setText("Linear Algebra\nDr. J. Mahalakshmi\nA202");
            t12.setText("Database \nManagement Systems\nDr. Priyanka Kumar\nA202");
            t13.setText("Computer Organisation \nand Architecture\nDr. Harini N.\nA202");
            t14.setText("Theory of \nComputation\nDr. Prakash P.\nA302");
            t15.setText("Digital Image \nProcessing\nMs. Sikha O. K.\nA302");
            t16.setText("FREE");
            //Tuesday
            t21.setText("FREE");
            t22.setText("Database \nManagement Systems\nDr. Priyanka Kumar\nA202");
            t23.setText("Digital Image \nProcessing\nMs. Sikha O. K.\nA202");
            t24.setText("Computer Organisation \nand Architecture\nDr. Harini N.\nA302");
            t25.setText("Theory of \nComputation\nDr. Prakash P.\nA302");
            t26.setText("Linear Algebra\nDr. J. Mahalakshmi\nA302");
            //Wednesday
            t31.setText("Database \nManagement Systems\nDr. Priyanka Kumar\nA202");
            t32.setText("FREE");
            t33.setText("Linear Algebra\nDr. J. Mahalakshmi\nA202");
            t34.setText("Theory of \nComputation\nDr. Prakash P.\nA302");
            t35.setText("Computer Organisation \nand Architecture\nDr. Harini N.\nA302");
            t36.setText("Digital Image \nProcessing\nMs. Sikha O. K.\nA302");
            //Thursday
            t41.setText("FREE");
            t42.setText("FREE");
            t43.setText("Theory of \nComputation\nDr. Prakash P.\nA202");
            t44.setText("Computer Organisation \nand Architecture\nDr. Harini N.\nA302");
            t45.setText("Linear Algebra\nDr. J. Mahalakshmi\nA302");
            t46.setText("FREE");
            //Friday
            t51.setText("Digital Image \nProcessing\nMs. Sikha O. K.\nA202");
            t52.setText("Database \nManagement Systems\nDr. Priyanka Kumar\nA202");
            t53.setText("Linear Algebra\nDr. J. Mahalakshmi\nA202");
            t54.setText("Computer Organisation \nand Architecture\nDr. Harini N.\nA302");
            t55.setText("Theory of \nComputation\nDr. Prakash P.\nA302");
            t56.setText("FREE");
        }
        else if(section.equals("C"))
        {
            //Monday
            t11.setText("FREE");
            t12.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA203");
            t13.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA203");
            t14.setText("Linear Algebra\nDr. J. Geetha\nA303");
            t15.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA303");
            t16.setText("Cloud Computing\nDr. Gowtham R.\nA303");
            //Tuesday
            t21.setText("FREE");
            t22.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA203");
            t23.setText("Cloud Computing\nDr. Gowtham R.\nA203");
            t24.setText("Linear Algebra\nDr. J. Geetha\nA303");
            t25.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA303");
            t26.setText("FREE");
            //Wednesday
            t31.setText("Linear Algebra\nDr. J. Geetha\nA203");
            t32.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA203");
            t33.setText("FREE");
            t34.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA303");
            t35.setText("Cloud Computing\nDr. Gowtham R.\nA303");
            t36.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA303");
            //Thursday
            t41.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA203");
            t42.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA203");
            t43.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA203");
            t44.setText("FREE");
            t45.setText("Linear Algebra\nDr. J. Geetha\nA303");
            t46.setText("Cloud Computing\nDr. Gowtham R.\nA303");
            //Friday
            t51.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA203");
            t52.setText("Linear Algebra\nDr. J. Geetha\nA203");
            t53.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA203");
            t54.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA303");
            t55.setText("FREE");
            t56.setText("FREE");
        }
        else if(section.equals("D"))
        {
            //Monday
            t11.setText("Linear Algebra\nDr. J. Geetha\nA204");
            t12.setText("Game Theory\nDr. Ganesh Neelakanta Iyer\nA204");
            t13.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA204");
            t14.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA304");
            t15.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA304");
            t16.setText("FREE");
            //Tuesday
            t21.setText("Game Theory\nDr. Ganesh Neelakanta Iyer\nA204");
            t22.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA204");
            t23.setText("FREE");
            t24.setText("FREE");
            t25.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA304");
            t26.setText("Linear Algebra\nDr. J. Geetha\nA304");
            //Wednesday
            t31.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA204");
            t32.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA204");
            t33.setText("FREE");
            t34.setText("FREE");
            t35.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA304");
            t36.setText("Linear Algebra\nDr. J. Geetha\nA304");
            //Thursday
            t41.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA204");
            t42.setText("Game Theory\nDr. Ganesh Neelakanta Iyer\nA204");
            t43.setText("Linear Algebra\nDr. J. Geetha\nA204");
            t44.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA304");
            t45.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA304");
            t46.setText("FREE");
            //Friday
            t51.setText("Database \nManagement Systems\nDr. Jeyakumar G.\nA204");
            t52.setText("Computer Organisation \nand Architecture\nDr. Dhanya N. M.\nA204");
            t53.setText("Game Theory\nDr. Ganesh Neelakanta Iyer\nA204");
            t54.setText("Theory of \nComputation\nMs. Prathilothamai M.\nA304");
            t55.setText("Linear Algebra\nDr. J. Geetha\nA304");
            t56.setText("FREE");
        }
    }

    public void dialogPrompt()
    {
        AlertDialog.Builder prompt = new AlertDialog.Builder(studentGenerate.this);
        Context context = studentGenerate.this;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText course = new EditText(context);
        course.setHint("Course Name");
        layout.addView(course);
        final EditText courseID = new EditText(context);
        courseID.setHint("Course Code");
        layout.addView(courseID);
        final EditText faculty = new EditText(context);
        faculty.setHint("Faculty Name");
        layout.addView(faculty);
        final EditText room = new EditText(context);
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
                s = 1;
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
    
    int s = 0;
    
    @Override
    public void onClick(View view)
    {
        if(view==t11)
        {
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '1'");
                    t11.setText("FREE");
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
                    t11.setText("FREE");
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
                    t11.setText("FREE");
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
                    t11.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '2'");
                    t12.setText("FREE");
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
                    t12.setText("FREE");
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
                    t12.setText("FREE");
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
                    t12.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '3'");
                    t13.setText("FREE");
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
                    t13.setText("FREE");
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
                    t13.setText("FREE");
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
                    t13.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '4'");
                    t14.setText("FREE");
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
                    t14.setText("FREE");
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
                    t14.setText("FREE");
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
                    t14.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '5'");
                    t15.setText("FREE");
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
                    t15.setText("FREE");
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
                    t15.setText("FREE");
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
                    t15.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '1' and slot = '6'");
                    t16.setText("FREE");
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
                    t16.setText("FREE");
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
                    t16.setText("FREE");
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
                    t16.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '1'");
                    t21.setText("FREE");
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
                    t21.setText("FREE");
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
                    t21.setText("FREE");
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
                    t21.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '2'");
                    t22.setText("FREE");
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
                    t22.setText("FREE");
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
                    t22.setText("FREE");
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
                    t22.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '3'");
                    t23.setText("FREE");
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
                    t23.setText("FREE");
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
                    t23.setText("FREE");
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
                    t23.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '4'");
                    t24.setText("FREE");
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
                    t24.setText("FREE");
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
                    t24.setText("FREE");
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
                    t24.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '5'");
                    t25.setText("FREE");
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
                    t25.setText("FREE");
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
                    t25.setText("FREE");
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
                    t25.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '2' and slot = '6'");
                    t26.setText("FREE");
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
                    t26.setText("FREE");
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
                    t26.setText("FREE");
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
                    t26.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '1'");
                    t31.setText("FREE");
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
                    t31.setText("FREE");
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
                    t31.setText("FREE");
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
                    t31.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '2'");
                    t32.setText("FREE");
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
                    t32.setText("FREE");
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
                    t32.setText("FREE");
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
                    t32.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '3'");
                    t33.setText("FREE");
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
                    t33.setText("FREE");
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
                    t33.setText("FREE");
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
                    t33.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '4'");
                    t34.setText("FREE");
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
                    t34.setText("FREE");
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
                    t34.setText("FREE");
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
                    t34.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '5'");
                    t35.setText("FREE");
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
                    t35.setText("FREE");
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
                    t35.setText("FREE");
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
                    t35.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '3' and slot = '6'");
                    t36.setText("FREE");
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
                    t36.setText("FREE");
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
                    t36.setText("FREE");
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
                    t36.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '1'");
                    t41.setText("FREE");
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
                    t41.setText("FREE");
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
                    t41.setText("FREE");
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
                    t41.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '2'");
                    t42.setText("FREE");
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
                    t42.setText("FREE");
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
                    t42.setText("FREE");
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
                    t42.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '3'");
                    t43.setText("FREE");
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
                    t43.setText("FREE");
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
                    t43.setText("FREE");
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
                    t43.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '4'");
                    t44.setText("FREE");
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
                    t44.setText("FREE");
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
                    t44.setText("FREE");
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
                    t44.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '5'");
                    t45.setText("FREE");
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
                    t45.setText("FREE");
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
                    t45.setText("FREE");
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
                    t45.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '4' and slot = '6'");
                    t46.setText("FREE");
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
                    t46.setText("FREE");
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
                    t46.setText("FREE");
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
                    t46.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '1'");
                    t51.setText("FREE");
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
                    t51.setText("FREE");
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
                    t51.setText("FREE");
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
                    t51.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '2'");
                    t52.setText("FREE");
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
                    t52.setText("FREE");
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
                    t52.setText("FREE");
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
                    t52.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '3'");
                    t53.setText("FREE");
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
                    t53.setText("FREE");
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
                    t53.setText("FREE");
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
                    t53.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '4'");
                    t54.setText("FREE");
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
                    t54.setText("FREE");
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
                    t54.setText("FREE");
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
                    t54.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '5'");
                    t55.setText("FREE");
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
                    t55.setText("FREE");
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
                    t55.setText("FREE");
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
                    t55.setText("FREE");
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
            s = 0;
            if(editAccess.equals("1"))
                dialogPrompt();
            if(section.equals("A")&&editAccess.equals("1")&&(s==1))
            {
                if(courseName.equals("FREE"))
                {
                    dataBooze.execSQL("update seca set course = 'FREE' where day = '5' and slot = '6'");
                    t56.setText("FREE");
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
                    t56.setText("FREE");
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
                    t56.setText("FREE");
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
                    t56.setText("FREE");
                }
                else
                {
                    dataBooze.execSQL("update secd set course = '"+courseCode+"' where day = '5' and slot = '6'");
                    t56.setText(courseName+"\n"+facultyName+"\n"+roomNo);
                }
            }
        }
    }
}
