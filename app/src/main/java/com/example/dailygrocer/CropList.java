package com.example.dailygrocer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CropList extends ArrayAdapter<Crop> {

    private Activity context;
    private List<Crop> cropList;

    public CropList(Activity context, List<Crop> cropList){
        super(context,R.layout.list_layout,cropList);
        this.context = context;
        this.cropList =cropList;
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


        Crop crop = cropList.get(position);

        textViewuserid.setText(crop.getId());
        textViewName.setText(crop.getCropname());
        textViewQuan.setText(crop.getQuantities());
        textViewDet.setText(crop.getDetail());


        return listViewItem;

    }
}