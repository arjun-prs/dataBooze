package com.example.databooze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class sixth extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    Spinner s1;
    Button b1;
    TextView item;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        s1 = findViewById(R.id.spinner);
        b1 = findViewById(R.id.button);
        s1.setOnItemSelectedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(getApplicationContext(), studentGenerate.class);
                i.putExtra("section",item.getText().toString());
                i.putExtra("editAccess","1");
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        item = (TextView) view;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
    }
}
