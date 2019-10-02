package com.example.databooze;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class studentGenerate extends AppCompatActivity
{
    TextView    t11,t12,t13,t14,t15,t16,
                t21,t22,t23,t24,t25,t26,
                t31,t32,t33,t34,t35,t36,
                t41,t42,t43,t44,t45,t46,
                t51,t52,t53,t54,t55,t56;
    //SQLiteDatabase dataBooze;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_generate);
        //dataBooze = openOrCreateDatabase("dataBooze", Context.MODE_PRIVATE, null);
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
        Bundle b = getIntent().getExtras();
        String section = b.getString("section");
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
}
