package com.example.databooze;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class facultyGenerate extends AppCompatActivity
{
    TextView t11,t12,t13,t14,t15,t16,
            t21,t22,t23,t24,t25,t26,
            t31,t32,t33,t34,t35,t36,
            t41,t42,t43,t44,t45,t46,
            t51,t52,t53,t54,t55,t56;
    SQLiteDatabase dataBooze;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_generate);
        Bundle bx = getIntent().getExtras();
        String courseID = bx.getString("courseID");
        String facultyID = bx.getString("facultyID");
        String dept = bx.getString("dept");
        int id = Integer.parseInt(facultyID.substring(3));
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

        dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
        Cursor a = dataBooze.rawQuery("SELECT * FROM seca", null);
        Cursor b = dataBooze.rawQuery("SELECT * FROM secb", null);
        Cursor c = dataBooze.rawQuery("SELECT * FROM secc", null);
        Cursor d = dataBooze.rawQuery("SELECT * FROM secd", null);


        if((dept.equals("CSE"))&&(id<=5) || (dept.equals("Mathematics"))&&(id<=1))
        {
            while (a.moveToNext()) {
                if (a.getString(2).equals(courseID)) {
                    if (a.getString(0).equals("1"))      //Monday
                    {
                        switch (a.getString(1)) {
                            case "1":
                                t11.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "2":
                                t12.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "3":
                                t13.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "4":
                                t14.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "5":
                                t15.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "6":
                                t16.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                        }
                    }
                    if (a.getString(0).equals("2"))      //Tuesday
                    {
                        switch (a.getString(1)) {
                            case "1":
                                t21.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "2":
                                t22.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "3":
                                t23.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "4":
                                t24.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "5":
                                t25.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "6":
                                t26.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                        }
                    }
                    if (a.getString(0).equals("3"))      //Wednesday
                    {
                        switch (a.getString(1)) {
                            case "1":
                                t31.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "2":
                                t32.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "3":
                                t33.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "4":
                                t34.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "5":
                                t35.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "6":
                                t36.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                        }
                    }
                    if (a.getString(0).equals("4"))      //Thursday
                    {
                        switch (a.getString(1)) {
                            case "1":
                                t41.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "2":
                                t42.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "3":
                                t43.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "4":
                                t44.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "5":
                                t45.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "6":
                                t46.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                        }
                    }
                    if (a.getString(0).equals("5"))      //Friday
                    {
                        switch (a.getString(1)) {
                            case "1":
                                t51.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "2":
                                t52.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "3":
                                t53.setText(a.getString(2) + "\nSection - A\nA201");
                                break;
                            case "4":
                                t54.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "5":
                                t55.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                            case "6":
                                t56.setText(a.getString(2) + "\nSection - A\nA301");
                                break;
                        }
                    }
                }
            }

            while (b.moveToNext()) {
                if (b.getString(2).equals(courseID)) {
                    if (b.getString(0).equals("1"))      //Monday
                    {
                        switch (b.getString(1)) {
                            case "1":
                                t11.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "2":
                                t12.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "3":
                                t13.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "4":
                                t14.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "5":
                                t15.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "6":
                                t16.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                        }
                    }
                    if (b.getString(0).equals("2"))      //Tuesday
                    {
                        switch (b.getString(1)) {
                            case "1":
                                t21.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "2":
                                t22.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "3":
                                t23.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "4":
                                t24.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "5":
                                t25.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "6":
                                t26.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                        }
                    }
                    if (b.getString(0).equals("3"))      //Wednesday
                    {
                        switch (b.getString(1)) {
                            case "1":
                                t31.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "2":
                                t32.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "3":
                                t33.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "4":
                                t34.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "5":
                                t35.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "6":
                                t36.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                        }
                    }
                    if (b.getString(0).equals("4"))      //Thursday
                    {
                        switch (b.getString(1)) {
                            case "1":
                                t41.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "2":
                                t42.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "3":
                                t43.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "4":
                                t44.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "5":
                                t45.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "6":
                                t46.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                        }
                    }
                    if (b.getString(0).equals("5"))      //Friday
                    {
                        switch (b.getString(1)) {
                            case "1":
                                t51.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "2":
                                t52.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "3":
                                t53.setText(b.getString(2) + "\nSection - B\nA202");
                                break;
                            case "4":
                                t54.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "5":
                                t55.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                            case "6":
                                t56.setText(b.getString(2) + "\nSection - B\nA302");
                                break;
                        }
                    }
                }
            }
        }
        else
        {
            while (c.moveToNext()) {
                if (c.getString(2).equals(courseID)) {
                    if (c.getString(0).equals("1"))      //Monday
                    {
                        switch (c.getString(1)) {
                            case "1":
                                t11.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "2":
                                t12.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "3":
                                t13.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "4":
                                t14.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "5":
                                t15.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "6":
                                t16.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                        }
                    }
                    if (c.getString(0).equals("2"))      //Tuesday
                    {
                        switch (c.getString(1)) {
                            case "1":
                                t21.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "2":
                                t22.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "3":
                                t23.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "4":
                                t24.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "5":
                                t25.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "6":
                                t26.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                        }
                    }
                    if (c.getString(0).equals("3"))      //Wednesday
                    {
                        switch (c.getString(1)) {
                            case "1":
                                t31.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "2":
                                t32.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "3":
                                t33.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "4":
                                t34.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "5":
                                t35.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "6":
                                t36.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                        }
                    }
                    if (c.getString(0).equals("4"))      //Thursday
                    {
                        switch (c.getString(1)) {
                            case "1":
                                t41.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "2":
                                t42.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "3":
                                t43.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "4":
                                t44.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "5":
                                t45.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "6":
                                t46.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                        }
                    }
                    if (c.getString(0).equals("5"))      //Friday
                    {
                        switch (c.getString(1)) {
                            case "1":
                                t51.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "2":
                                t52.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "3":
                                t53.setText(c.getString(2) + "\nSection - C\nA203");
                                break;
                            case "4":
                                t54.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "5":
                                t55.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                            case "6":
                                t56.setText(c.getString(2) + "\nSection - C\nA303");
                                break;
                        }
                    }
                }
            }

            while (d.moveToNext()) {
                if (d.getString(2).equals(courseID)) {
                    if (d.getString(0).equals("1"))      //Monday
                    {
                        switch (d.getString(1)) {
                            case "1":
                                t11.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "2":
                                t12.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "3":
                                t13.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "4":
                                t14.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "5":
                                t15.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "6":
                                t16.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                        }
                    }
                    if (d.getString(0).equals("2"))      //Tuesday
                    {
                        switch (d.getString(1)) {
                            case "1":
                                t21.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "2":
                                t22.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "3":
                                t23.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "4":
                                t24.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "5":
                                t25.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "6":
                                t26.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                        }
                    }
                    if (d.getString(0).equals("3"))      //Wednesday
                    {
                        switch (d.getString(1)) {
                            case "1":
                                t31.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "2":
                                t32.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "3":
                                t33.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "4":
                                t34.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "5":
                                t35.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "6":
                                t36.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                        }
                    }
                    if (d.getString(0).equals("4"))      //Thursday
                    {
                        switch (d.getString(1)) {
                            case "1":
                                t41.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "2":
                                t42.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "3":
                                t43.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "4":
                                t44.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "5":
                                t45.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "6":
                                t46.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                        }
                    }
                    if (d.getString(0).equals("5"))      //Friday
                    {
                        switch (d.getString(1)) {
                            case "1":
                                t51.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "2":
                                t52.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "3":
                                t53.setText(d.getString(2) + "\nSection - D\nA204");
                                break;
                            case "4":
                                t54.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "5":
                                t55.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                            case "6":
                                t56.setText(d.getString(2) + "\nSection - D\nA304");
                                break;
                        }
                    }
                }
            }
        }
    }
}
