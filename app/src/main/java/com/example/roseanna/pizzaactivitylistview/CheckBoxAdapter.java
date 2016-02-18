package com.example.roseanna.pizzaactivitylistview;

/**
 * Created by minkim on 2/16/16.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.util.Log;
import java.util.*;

public class CheckBoxAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] values;
    private int[] pics;
    private int sum = 0;
    public CheckBoxAdapter(Context context, String[] values) {
        super(context, R.layout.checkboxrow, values);
        this.context    = context;
        this.values     = values;
    }
    public CheckBoxAdapter(Context context, String[] values, int[] pics) {
        super(context, R.layout.checkboxrow, values);
        this.context    = context;
        this.values     = values;
        this.pics       = pics;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView            = inflater.inflate(R.layout.checkboxrow, parent, false);
        CheckBox cb             = (CheckBox) rowView.findViewById(R.id.checkbox);
        ImageView imageView     = (ImageView) rowView.findViewById(R.id.pic);
        TextView textView       = (TextView) rowView.findViewById(R.id.checkText);

        textView.setText(values[position]);
        if (pics != null && pics.length > position) {
            Log.i("pics", String.valueOf(position));
            imageView.setImageResource(pics[position]);
        }
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked)
                    sum++;
                else
                    sum--;
            }
        });
        return rowView;
    }

    @Override
    public int getCount(){
        return values.length;
    }

    public int total(){
        return sum;
    }
}