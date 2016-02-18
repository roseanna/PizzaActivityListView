package com.example.roseanna.pizzaactivitylistview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.util.*;
import java.util.*;
import android.content.*;
import android.app.Activity;

public class MainActivity extends Activity {

    LinearLayout ll;

    TextView chooseSize;
    TextView chooseTopping;
    TextView placeOrder;
    TextView result;

    Spinner spinner;

    Button veggieTopping;
    Button meatTopping;
    Button order;

    String pSize    = "Small";

    int sizeCost    = 0;
    int vMult       = 1;
    int mMult       = 1;
    int numVeggies  = 0;
    int numMeats    = 0;
    int total       = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll              = (LinearLayout) findViewById(R.id.ll);
        result          = new TextView(this);
        spinner         = new Spinner(this);
        chooseSize      = new TextView(this);
        order           = new Button(this);
        meatTopping     = new Button(this);
        veggieTopping   = new Button(this);
        placeOrder      = new TextView(this);
        chooseTopping   = new TextView(this);

        createSpinner();
        addVeggie();
        addMeat();
        addButton();

        ll.addView(result);
    }

    private void createSpinner(){
        final String[] sizeOptions  = {"Small", "Medium", "Large"};
        spinner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        chooseSize.setText("Choose Pizza Size: ");
        chooseSize.setTextSize(30);
        chooseSize.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, sizeOptions);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sizeCost = 5;
                        pSize = "Small";
                        break;
                    case 1:
                        sizeCost = 7;
                        pSize = "Medium";
                        break;
                    case 2:
                        sizeCost = 10;
                        pSize = "Large";
                        break;
                }
                setText();
                Log.i("sizeCost", String.valueOf(sizeCost));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ll.addView(chooseSize);
        ll.addView(spinner);

    }


    private void addButton() {
        placeOrder.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        placeOrder.setText("Place Order: ");
        placeOrder.setTextSize(30);

        order.setText("Place Order");
        order.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Separate page
//                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
//                startActivityForResult(intent, 300);
                Toast t = Toast.makeText(getApplicationContext(), "Your order has been placed!", Toast.LENGTH_SHORT);
                t.show();
            }
        });
        ll.addView(placeOrder);
        ll.addView(order);
    }

    public void addVeggie() {

        chooseTopping.setText("Choose Topping: ");
        chooseTopping.setTextSize(30);
        chooseTopping.setGravity(Gravity.CENTER|Gravity.BOTTOM);

        veggieTopping.setText("Add Veggie Toppings");
        veggieTopping.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        veggieTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veggieintent = new Intent(MainActivity.this, VeggieActivity.class);
                startActivityForResult(veggieintent, 100);
            }
        });
        ll.addView(chooseTopping);
        ll.addView(veggieTopping);
    }

    public void addMeat() {
        meatTopping.setText("Add Meat Toppings");
        meatTopping.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        meatTopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meatintent = new Intent(MainActivity.this, MeatActivity.class);
                startActivityForResult(meatintent, 200);
            }
        });
        ll.addView(meatTopping);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 100){
                Bundle veggieData = data.getExtras();
                numVeggies = veggieData.getInt("num");
                setText();
                Log.i("HERE GOT INFO FROM V", Integer.toString(numVeggies));
            }
            else if (requestCode == 200){
                Bundle veggieData = data.getExtras();
                numMeats = veggieData.getInt("num");
                setText();
                Log.i("HERE GOT INFO FROM MEAT", Integer.toString(numMeats));
            }
            else if (requestCode == 300){
                Bundle placedOrder = data.getExtras();
                String myresult = placedOrder.getString("done");
                result.setText(myresult);
            }
        }
        catch (Exception e){
            Log.i("ERROR","ERROR");
        }
    }

    public void setText(){
        calculate();
        String resultString = "Your total for a " + pSize + " pizza with ";
        resultString += String.valueOf(numMeats) + " meat toppings, " + String.valueOf(numVeggies) + " veggie toppings is $";
        resultString += String.valueOf(total);
        result.setText(resultString);
        result.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        result.setTextSize(30);
    }

    public void calculate(){
        total = 0;
        if (sizeCost == 5) {
            vMult = 1;
            mMult = 2;
        }
        else if (sizeCost == 7){
            vMult = 2;
            mMult = 4;
        }
        else{
            vMult = 3;
            mMult = 6;
        }
        total = sizeCost + (vMult * numVeggies) + (mMult * numMeats);
    }



}
