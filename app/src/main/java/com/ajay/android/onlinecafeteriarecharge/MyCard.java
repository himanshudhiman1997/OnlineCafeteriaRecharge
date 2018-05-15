package com.ajay.android.onlinecafeteriarecharge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);
    }
    public void recharge(View v)
    {
        Intent recharge = new Intent(MyCard.this,Recharge.class);
        startActivity(recharge);
    }
}
