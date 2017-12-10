package com.example.deni.labmobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deni.labmobile.Model.Product;
import com.example.deni.labmobile.R;
import com.example.deni.labmobile.dao.AppDB;
import com.example.deni.labmobile.dao.IProductDao;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity{

    ArrayList<Product> products = new ArrayList<>();
    MyAdpter adapter;
    private AppDB database;
    private IProductDao iProductDao;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        database = AppDB.getAppDatabase(this);


        iProductDao = database.productDao();


        products = (ArrayList<Product>) iProductDao.getAll();
        Log.v("products", products.toString());

        adapter = new MyAdpter(products);

        listView = findViewById(R.id.List);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parentAdapter, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), ElementDetailsActivity.class);
                Log.d("Obiect", products.get(position).toString());
                Integer p = position;
                intent.putExtra("id", products.get(position).getId().toString());
                intent.putExtra("name", products.get(position).name);
                intent.putExtra("price", products.get(position).price.toString());
                intent.putExtra("quantity", products.get(position).quantity.toString());

                startActivity(intent);
            }
         });
    }

    @Override
    protected void onResume() {

        super.onResume();
        adapter.notifyDataSetChanged();
        products = (ArrayList<Product>) iProductDao.getAll();

        adapter = new MyAdpter(products);
        listView.setAdapter(adapter);
    }

    public void addProduct(View v) {
        Intent addFormView = new Intent(getApplicationContext(), AddProductActivity.class);

        startActivityForResult(addFormView, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String name = data.getStringExtra("name");
        Integer price = Integer.parseInt(data.getStringExtra("price"));
        Integer quantity = Integer.parseInt(data.getStringExtra("quantity"));

        Product product = new Product(name, price, quantity);

        iProductDao.insert(product);
        adapter.notifyDataSetChanged();
        products = (ArrayList<Product>) iProductDao.getAll();

        adapter = new MyAdpter(products);
        listView.setAdapter(adapter);
    }

    public void goToChart(View v)
    {

        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }


    class MyAdpter extends BaseAdapter
    {

        ArrayList<Product> list;

        public MyAdpter(ArrayList<Product> lista){
            list = lista;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.list_element_layout, null);
            TextView view1 = view.findViewById(R.id.numeprodus);
            TextView view2 = view.findViewById(R.id.priceprodus);
            TextView view3 = view.findViewById(R.id.quanprodus);
            view1.setText(list.get(i).name);
            view2.setText(list.get(i).price.toString());
            view3.setText(list.get(i).quantity.toString());

            return view;
        }

    }

}
