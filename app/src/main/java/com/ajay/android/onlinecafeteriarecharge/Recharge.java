package com.ajay.android.onlinecafeteriarecharge;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Recharge extends AppCompatActivity {

    EditText uniqueName;
    EditText amountRecharge;
    EditText cardNumber;
    EditText monthRecharge;
    EditText yearRecharge;

    Button submitRecharge;

    String rechargeUniqueName;
    String rechargeAmount;
    String rechargeCardNumber;
    String rechargeMonth;
    String rechargeYear;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        databaseReference = FirebaseDatabase.getInstance().getReference("card");

        uniqueName = findViewById(R.id.rechargeUniqueNameEdit);
        amountRecharge = findViewById(R.id.rechargeAmountEdit);
        cardNumber = findViewById(R.id.rechargeCardNumber);
        monthRecharge = findViewById(R.id.rechargeMonth);
        yearRecharge = findViewById(R.id.rechargeYear);

        submitRecharge = findViewById(R.id.rechargeSubmitButton);

        submitRecharge.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                rechargeUniqueName = uniqueName.getText().toString().trim();
                rechargeAmount = amountRecharge.getText().toString().trim();
                rechargeCardNumber = cardNumber.getText().toString().trim();
                rechargeMonth = monthRecharge.getText().toString().trim();
                rechargeYear = yearRecharge.getText().toString().trim();



                Query query = databaseReference.orderByChild("uniqueName").equalTo(rechargeUniqueName);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            Card card = dataSnapshot1.getValue(Card.class);
                            long bal = Integer.valueOf(card.getBalance()) + Integer.valueOf(rechargeAmount);
                            //String updatedBalance = String.valueOf(bal);
                            dataSnapshot1.getRef().child("balance").setValue(bal);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        finish();
                    }
                });

            }


        });


    }
}
