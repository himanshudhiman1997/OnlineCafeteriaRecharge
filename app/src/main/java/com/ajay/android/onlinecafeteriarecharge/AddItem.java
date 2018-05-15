package com.ajay.android.onlinecafeteriarecharge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPrice;
    EditText editTextQuantity;

    DatabaseReference databaseItems;

    Button submitButton;

    String itemName;
    String itemPrice;
    String itemQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        databaseItems = FirebaseDatabase.getInstance().getReference("items");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPrice = (EditText) findViewById(R.id.editTextPrice);
        editTextQuantity = (EditText) findViewById(R.id.editTextQuantity);

        submitButton = (Button) findViewById(R.id.saveButton);
        submitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                itemName = editTextName.getText().toString().trim();
                itemPrice = editTextPrice.getText().toString().trim();
                itemQuantity = editTextQuantity.getText().toString().trim();

                editTextName.setText("");
                editTextPrice.setText("");
                editTextQuantity.setText("");
                addItem();
            }
        });
    }
    public void addItem()
    {
        String id = databaseItems.push().getKey();
        Items items = new Items(id, itemName, itemPrice, itemQuantity);

        Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show();

        databaseItems.child(id).setValue(items);
        this.finish();
    }


}
