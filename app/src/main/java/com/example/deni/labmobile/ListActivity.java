package com.example.deni.labmobile;

import android.app.ListFragment;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity{

    ArrayList<Product> products = new ArrayList<>();
    MyAdpter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        products.add(new Product("oua", 2, 3));
        products.add(new Product("lapte", 15, 1));
        products.add(new Product("faina", 10, 2));
        adapter = new MyAdpter(products);
        ListView listView = findViewById(R.id.List);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView <?> parentAdapter, View view, int position,
                                    long id) {

                Intent intent = new Intent(getApplicationContext(), ElementDetailsActivity.class);
                Log.d("Obiect", products.get(position).toString());
                Integer p = position;
                intent.putExtra("position", p.toString());
                intent.putExtra("name", products.get(position).name);
                intent.putExtra("price", products.get(position).price.toString());
                intent.putExtra("quantity", products.get(position).quantity.toString());
                startActivityForResult(intent, 1);
            }
         });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle da=data.getExtras();

        products.get(Integer.parseInt(data.getStringExtra("position"))).setName(data.getStringExtra("name"));
        products.get(Integer.parseInt(data.getStringExtra("position"))).price = Integer.parseInt(data.getStringExtra("price"));
        products.get(Integer.parseInt(data.getStringExtra("position"))).quantity = Integer.parseInt(data.getStringExtra("quantity"));
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
        public View getView(int i, View view, ViewGroup viewGroup) {
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
