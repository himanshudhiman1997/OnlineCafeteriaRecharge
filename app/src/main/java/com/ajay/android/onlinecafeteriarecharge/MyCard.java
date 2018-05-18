package com.ajay.android.onlinecafeteriarecharge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MyCard extends AppCompatActivity {

    private static final String TAG = "MyCard";
    EditText uniqueNameEdit1;

    DatabaseReference databaseReference;

    String uniqueName;

    TextView nameText;
    TextView phoneText;
    TextView balanceText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_card);

        databaseReference = FirebaseDatabase.getInstance().getReference("card");

        uniqueNameEdit1 = (EditText) findViewById(R.id.uniqueNameEditDetails);


        nameText = findViewById(R.id.name);
        phoneText = findViewById(R.id.phone);
        balanceText = findViewById(R.id.balance);
    }


    public void getDetails(View v)
    {
        uniqueName = uniqueNameEdit1.getText().toString().trim();
        Query query = databaseReference.orderByChild("uniqueName").equalTo(uniqueName);
        //Log.d(TAG, "getDetails: " + query.getRef().getKey());
       // Toast.makeText(this,"himanshudhimanhr",Toast.LENGTH_LONG).show();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: ");

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Card card = dataSnapshot1.getValue(Card.class);
                        nameText.setText(card.getUniqueName());
                        phoneText.setText(card.getPhoneNumber());
                        balanceText.setText(card.getBalance());
                    }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

    }
    public void recharge(View v)
    {
        Intent recharge = new Intent(MyCard.this,Recharge.class);
        startActivity(recharge);
    }
}
