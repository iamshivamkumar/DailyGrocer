package com.example.dailygrocer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    ListView listViewCrop;
    DatabaseReference databaseCrop;
    List<Crop> cropList;
    public static final String cropname ="cropname";
    public static final String username ="username";
    public static final String userid = "Userid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        cropList = new ArrayList<>();

        listViewCrop = (ListView) findViewById(R.id.croplistview);

        databaseCrop = FirebaseDatabase.getInstance().getReference("Crops");

        listViewCrop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Crop crop = cropList.get(position);

                Intent intent = new Intent(HomePage.this , Buy.class);
                intent.putExtra(cropname,crop.getCropname());
                intent.putExtra(userid,crop.getId());
                startActivity(intent);

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseCrop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cropList.clear();

                for (DataSnapshot cropSnapshot : dataSnapshot.getChildren()){
                    Crop crop = cropSnapshot.getValue(Crop.class);
                    cropList.add(crop);
                }

                CropList adapter = new CropList(HomePage.this,cropList);
                listViewCrop.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
