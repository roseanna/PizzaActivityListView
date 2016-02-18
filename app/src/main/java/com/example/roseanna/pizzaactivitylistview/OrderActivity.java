package com.example.roseanna.pizzaactivitylistview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by roseanna on 2/18/16.
 */
public class OrderActivity extends AppCompatActivity {
    Button button;
    String done = "Your order has been placed";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeorder);

        final Intent intent = getIntent();

        button = (Button) findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle myBundle = new Bundle();
                myBundle.putString("done", done);
                intent.putExtras(myBundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }}
