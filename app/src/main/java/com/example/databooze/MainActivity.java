package com.example.databooze;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText userName, passWord;
    Button proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.edETUserName);
        passWord=findViewById(R.id.edETPassword);
        proceed=findViewById(R.id.edButProceed);
        proceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent adminInterface=new Intent(this, Admin_Interface.class); //only for admins...
        startActivity(adminInterface);
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){

        userName.setText("");
        passWord.setText("");
        userName.requestFocus();
    }
}
