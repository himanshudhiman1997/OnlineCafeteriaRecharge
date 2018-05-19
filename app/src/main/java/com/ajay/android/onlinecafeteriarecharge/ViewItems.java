package com.ajay.android.onlinecafeteriarecharge;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewItems extends AppCompatActivity {

    DatabaseReference databaseReferenceItems;
    List<Items> itemsList;


    private FirebaseAuth mAuth;

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    public static final int RC_SIGN_IN = 1;


    private String mUsername;

    // Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mMessagesDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public int backpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        mUsername = ANONYMOUS;

        // Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();



        databaseReferenceItems = FirebaseDatabase.getInstance().getReference("items");
        itemsList = new ArrayList<>();


    }

    @Override
    protected void onStart() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        super.onStart();

        databaseReferenceItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemsList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Items items = dataSnapshot1.getValue(Items.class);
                    itemsList.add(items);
                }

                ItemsList adapter = new ItemsList(ViewItems.this, itemsList);
                ListView listViewItems = findViewById(R.id.listViewItems);
                listViewItems.setAdapter(adapter);
                progress.dismiss();

                /*listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent updateDeleteIntent = new Intent(ViewItems.this, updateDelete.class);
                        Items items = (Items) adapterView.getItemAtPosition(i);
                        updateDeleteIntent.putExtra("name", items.getItemName());
                        updateDeleteIntent.putExtra("price", items.getItemPrice());
                        updateDeleteIntent.putExtra("quantity", items.getItemQuantity());
                        updateDeleteIntent.putExtra("key", items.getItemId());
                        startActivity(updateDeleteIntent);
                    }
                });*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }











}
