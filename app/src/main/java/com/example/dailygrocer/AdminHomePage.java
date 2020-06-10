package com.example.dailygrocer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHomePage extends AppCompatActivity implements View.OnClickListener {
    private String uid;
    String username,fname,lname;
    EditText cropname, quantity, details;
    DatabaseReference databaseCrop,databaseProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        cropname = (EditText) findViewById(R.id.cropname);
        quantity = (EditText) findViewById(R.id.quantity);
        details = (EditText) findViewById(R.id.details);


        findViewById(R.id.buttonsell).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);

        uid = FirebaseAuth.getInstance().getUid();

        databaseProfile= FirebaseDatabase.getInstance().getReference("Profile");

        databaseCrop = FirebaseDatabase.getInstance().getReference("Crops");

    }
    private void addcrop() {

        String cropName = cropname.getText().toString().trim();
        String quantities = quantity.getText().toString().trim();
        String detail = details.getText().toString().trim();
        username = fname+" "+lname;

        if (cropName.isEmpty()) {
            cropname.setError("Crop name is required");
            cropname.requestFocus();
            return;
        }
        if (quantities.isEmpty()) {
            quantity.setError("Quantity is required");
            quantity.requestFocus();
            return;
        }

        if (detail.isEmpty()) {
            details.setError("Details is required");
            details.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(cropName) && !TextUtils.isEmpty(quantities) && !TextUtils.isEmpty(detail)  ) {

            Crop crop = new Crop(uid,cropName, quantities, detail);

            databaseCrop.push().setValue(crop);

            Toast.makeText(this, "Crop Listed", Toast.LENGTH_SHORT).show();
        }
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonsell:
                addcrop();
                break;
            case R.id.notification:
                startActivity(new Intent(this, Order.class));
                finish();

        }
    }
}
