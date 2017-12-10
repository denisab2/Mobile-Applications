package com.example.deni.labmobile.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.deni.labmobile.R;


public class AddProductActivity extends AppCompatActivity {

    NumberPicker edQuan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        edQuan = findViewById(R.id.numberPicker);
        edQuan.setMaxValue(1000);
        edQuan.setMinValue(1);
    }

    public void pressAdd(View v){
        Intent finIn = new Intent();
        EditText edName = findViewById(R.id.addName);
        EditText edPrice = findViewById(R.id.addPrice);

        Integer quan = edQuan.getValue();
        finIn.putExtra("name", edName.getText().toString());
        finIn.putExtra("price", edPrice.getText().toString());
        finIn.putExtra("quantity", quan.toString());

        setResult(Activity.RESULT_OK, finIn);
        finish();
    }



}
