package com.example.roseanna.pizzaactivitylistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.*;
import android.util.*;

public class VeggieActivity extends AppCompatActivity {

    public int numToppings = 0;
    ListView veggieListView;
    CheckBoxAdapter veggieAdapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veggieactivity);

        final Intent intent = getIntent();

        button = (Button) findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numToppings = veggieAdapter.total();
                Bundle myBundle = new Bundle();
                myBundle.putInt("num", numToppings);
                intent.putExtras(myBundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        veggieListView  = (ListView) findViewById(R.id.veggielist);
        String[] veggies = {"Spinach", "Mushroom", "Olive",
                "Tomato", "Pepper", "Onions"};
        int[] vegImage = new int[]{R.drawable.spinach, R.drawable.mushroom, R.drawable.olive,
                R.drawable.tomato, R.drawable.pepper, R.drawable.onion};
        veggieAdapter   = new CheckBoxAdapter(this, veggies, vegImage);
        veggieListView.setAdapter(veggieAdapter);



        Log.i("NUM TOPPINGS", Integer.toString(numToppings));
    }
}
