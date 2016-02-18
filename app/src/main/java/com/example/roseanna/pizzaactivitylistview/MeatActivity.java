package com.example.roseanna.pizzaactivitylistview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MeatActivity extends AppCompatActivity {

    public int numToppings = 0;
    ListView meatListView;
    CheckBoxAdapter meatAdapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meatactivity);

        final Intent intent = getIntent();

        button = (Button) findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numToppings = meatAdapter.total();
                Bundle myBundle = new Bundle();
                myBundle.putInt("num", numToppings);
                intent.putExtras(myBundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        meatListView    = (ListView) findViewById(R.id.meatlist);
        String[] meats  = {"Steak","Bacon", "Ham", "Chicken", "Pepperoni", "Sausage"};
        int[] meatImage = new int[]{R.drawable.steak, R.drawable.bacon, R.drawable.ham,
                                        R.drawable.chicken, R.drawable.pepperoni, R.drawable.sausage};

        meatAdapter   = new CheckBoxAdapter(this, meats, meatImage);
        meatListView.setAdapter(meatAdapter);
        
        Log.i("NUM TOPPINGS", Integer.toString(numToppings));
    }
}
