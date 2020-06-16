package com.example.dailygrocer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ProductList extends ArrayAdapter<Product> {

    private Activity context;
    private List<Product> productList;

    public ProductList(Activity context, List<Product> productList){
        super(context,R.layout.list_layout, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewuserid = (TextView)listViewItem.findViewById(R.id.userid);
        TextView textViewName = (TextView)listViewItem.findViewById(R.id.namecrop);
        TextView textViewQuan = (TextView)listViewItem.findViewById(R.id.quan);
        TextView textViewDet = (TextView)listViewItem.findViewById(R.id.det);


        Product product = productList.get(position);

        textViewuserid.setText(product.getId());
        textViewName.setText(product.getCropname());
        textViewQuan.setText(product.getQuantities());
        textViewDet.setText(product.getDetail());


        return listViewItem;

    }
}