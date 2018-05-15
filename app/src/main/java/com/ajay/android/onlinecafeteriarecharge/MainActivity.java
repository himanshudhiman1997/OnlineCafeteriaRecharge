package com.ajay.android.onlinecafeteriarecharge;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button admin;
    Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = (Button) findViewById(R.id.adminButton);
        admin.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View view)
           {
               Intent adminLogin = new Intent(MainActivity.this,AdminLogin.class);
               startActivity(adminLogin);
           }
        });

        user = (Button) findViewById(R.id.userButton);
        user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent userLogin = new Intent(MainActivity.this,UserLogin.class);
                startActivity(userLogin);
            }
        });

    }



}
