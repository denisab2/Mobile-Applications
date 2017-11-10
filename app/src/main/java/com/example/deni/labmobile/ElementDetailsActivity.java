package com.example.deni.labmobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ElementDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);
        Intent intent = getIntent();

        EditText edName = findViewById(R.id.editName);
        EditText edPrice = findViewById(R.id.editPrice);
        EditText edQuan = findViewById(R.id.editQuantity);

        edName.setText(intent.getStringExtra("name"));
        edPrice.setText(intent.getStringExtra("price"));
        edQuan.setText(intent.getStringExtra("quantity"));

    }

    public void pressDone(View v){
        Intent intent = getIntent();
        Intent finIn = new Intent();
        EditText edName = findViewById(R.id.editName);
        EditText edPrice = findViewById(R.id.editPrice);
        EditText edQuan = findViewById(R.id.editQuantity);

        finIn.putExtra("position", intent.getStringExtra("position"));
        finIn.putExtra("name", edName.getText().toString());
        finIn.putExtra("price", edPrice.getText().toString());
        finIn.putExtra("quantity", edQuan.getText().toString());

        setResult(Activity.RESULT_OK, finIn);
        finish();
    }
}
