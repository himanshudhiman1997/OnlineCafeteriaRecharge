package com.ajay.android.onlinecafeteriarecharge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    Button feedback;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        feedback = (Button) findViewById(R.id.submitFeedback);
        feedback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                editText = findViewById(R.id.edittextFeedback);
                editText.setText("");

            }
        });
    }


}
