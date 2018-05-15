package com.ajay.android.onlinecafeteriarecharge;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateDelete extends AppCompatActivity {

    EditText itemName;
    EditText itemPrice;
    EditText itemQuantity;

    DatabaseReference databaseReference;

    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        itemName = findViewById(R.id.itemNameEditText);
        itemPrice = findViewById(R.id.itemPriceEditText);
        itemQuantity = findViewById(R.id.itemQuantityEditText);

        itemName.setText(getIntent().getStringExtra("name"));
        itemPrice.setText(getIntent().getStringExtra("price"));
        itemQuantity.setText(getIntent().getStringExtra("quantity"));
        key = getIntent().getStringExtra("key");


        databaseReference = FirebaseDatabase.getInstance().getReference().child("items").child(key);

    }


    public void updateItem(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("itemName").setValue(itemName.getText().toString());
                dataSnapshot.getRef().child("itemPrice").setValue(itemPrice.getText().toString());
                dataSnapshot.getRef().child("itemQuantity").setValue(itemQuantity.getText().toString());
                Toast.makeText(updateDelete.this, "Item Updated", Toast.LENGTH_LONG).show();
                updateDelete.this.finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void deleteItem(View view) {

        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(updateDelete.this, "Item Deleted", Toast.LENGTH_LONG).show();
                    updateDelete.this.finish();
                } else {
                    Toast.makeText(updateDelete.this, "Item not deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
