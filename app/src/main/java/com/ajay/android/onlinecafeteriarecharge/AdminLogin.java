package com.ajay.android.onlinecafeteriarecharge;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class AdminLogin extends AppCompatActivity {

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
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(AdminLogin.this, "You're now signed in. Welcome.", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                    );

                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setProviders(providers)
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };


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

                ItemsList adapter = new ItemsList(AdminLogin.this, itemsList);
                ListView listViewItems = findViewById(R.id.listViewItems);
                listViewItems.setAdapter(adapter);
                progress.dismiss();

                listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent updateDeleteIntent = new Intent(AdminLogin.this, updateDelete.class);
                        Items items = (Items) adapterView.getItemAtPosition(i);
                        updateDeleteIntent.putExtra("name", items.getItemName());
                        updateDeleteIntent.putExtra("price", items.getItemPrice());
                        updateDeleteIntent.putExtra("quantity", items.getItemQuantity());
                        updateDeleteIntent.putExtra("key", items.getItemId());
                        startActivity(updateDeleteIntent);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





    @Override
    public void onBackPressed()
    {

        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress>1) {
            AuthUI.getInstance().signOut(this);
            Intent back = new Intent(AdminLogin.this,MainActivity.class);
            startActivity(back);
        }

    }


    @Override
    public void onActivityResult(int requestCode , int resultCode , Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_SIGN_IN)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this,"Signed In",Toast.LENGTH_SHORT).show();
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this,"Sign In Cancelled",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void addItem(View v)
    {
        Intent add = new Intent(AdminLogin.this,AddItem.class);
        startActivity(add);
    }

    public void updateItem(View v)
    {
        Intent update = new Intent(AdminLogin.this,UpdateItem.class);
        startActivity(update);
    }

    public void deleteItem(View v)
    {
        Intent delete = new Intent(AdminLogin.this,DeleteItem.class);
        startActivity(delete);
    }


    public void signout(View v)
    {
        AuthUI.getInstance().signOut(this);
        Intent main = new Intent(AdminLogin.this,MainActivity.class);
        startActivity(main);
    }
}
