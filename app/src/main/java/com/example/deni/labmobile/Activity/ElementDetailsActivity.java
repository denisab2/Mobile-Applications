package com.example.deni.labmobile.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.deni.labmobile.Model.Product;
import com.example.deni.labmobile.R;
import com.example.deni.labmobile.dao.AppDB;
import com.example.deni.labmobile.dao.IProductDao;

public class ElementDetailsActivity extends AppCompatActivity {

    private AppDB database;
    private IProductDao iProductDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);

        database = AppDB.getAppDatabase(this);


        iProductDao = database.productDao();


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

        EditText edName = findViewById(R.id.editName);
        EditText edPrice = findViewById(R.id.editPrice);
        EditText edQuan = findViewById(R.id.editQuantity);

        String name = edName.getText().toString();
        Integer price = Integer.parseInt(edPrice.getText().toString());
        Integer quantity = Integer.parseInt( edQuan.getText().toString());

        Product product = new Product(name, price, quantity);
        product.setId(Long.parseLong(intent.getStringExtra("id")));

        iProductDao.update(product);
        finish();
    }

    public void pressDelete(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = getIntent();

                String name = intent.getStringExtra("name");
                Integer price = Integer.parseInt(intent.getStringExtra("price"));
                Integer quantity = Integer.parseInt( intent.getStringExtra("quantity"));

                Product product = new Product(name, price, quantity);
                product.setId(Long.parseLong(intent.getStringExtra("id")));

                iProductDao.delete(product);
                finish();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
