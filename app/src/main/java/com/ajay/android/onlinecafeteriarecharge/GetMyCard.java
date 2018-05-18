package com.ajay.android.onlinecafeteriarecharge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetMyCard extends AppCompatActivity {

    DatabaseReference databaseCard;

    EditText editUniqueName;
    EditText editPhoneNumber;
    String balance = "0";

    Button saveDetailsButton;

    String uniqueName;
    String phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_card);

        databaseCard = FirebaseDatabase.getInstance().getReference("card");

        editUniqueName = (EditText) findViewById(R.id.uniqueNameEdit);
        editPhoneNumber = (EditText) findViewById(R.id.phoneNumberEdit);

        saveDetailsButton = (Button) findViewById(R.id.saveDetailsButton);
        saveDetailsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                uniqueName = editUniqueName.getText().toString().trim();
                phoneNumber = editPhoneNumber.getText().toString().trim();

                editUniqueName.setText("");
                editPhoneNumber.setText("");
                addData();
            }
        });
    }
    public void addData()
    {
        String id = databaseCard.push().getKey();
        Card card = new Card(id, uniqueName, phoneNumber, balance);

        Toast.makeText(this, "Your card is ready", Toast.LENGTH_LONG).show();

        databaseCard.child(id).setValue(card);
        this.finish();
    }

}
