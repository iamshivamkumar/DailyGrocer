package com.example.dailygrocer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.dailygrocer.HomePage.cropname;
import static com.example.dailygrocer.HomePage.userid;

public class Buy extends AppCompatActivity implements View.OnClickListener {
    TextView cropnameTextview;
    String phoneNumber,uid,username,first,last,address;
    EditText quantity;
    DatabaseReference databaseCrop,databaseProfile;
    private String name,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Intent intent =getIntent();

        id =intent.getStringExtra(userid);
        name =intent.getStringExtra(cropname);
        quantity = (EditText) findViewById(R.id.quantity);

        cropnameTextview = (TextView)findViewById(R.id.cropname);
        cropnameTextview.setText(name);
        findViewById(R.id.buy).setOnClickListener(this);

        uid = FirebaseAuth.getInstance().getUid();

        databaseCrop = FirebaseDatabase.getInstance().getReference();
        databaseProfile = FirebaseDatabase.getInstance().getReference("Profile");

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseProfile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                phoneNumber = (String) dataSnapshot.child(uid).child("phoneNumber").getValue();
                address = (String) dataSnapshot.child(uid).child("address").getValue();
                first = (String) dataSnapshot.child(uid).child("firstName").getValue();
                last = (String) dataSnapshot.child(uid).child("lastName").getValue();
                username=first+" "+last;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void buy()
    {

        String quantities = quantity.getText().toString().trim();

        if (quantities.isEmpty()) {
            quantity.setError("Quantity is required");
            quantity.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(quantities) ) {

            Notify notify = new Notify(uid,username,name,quantities,phoneNumber,address);

            databaseCrop.child("Notify").child(id).push().setValue(notify);

            Toast.makeText(getApplicationContext(), "Purchased", Toast.LENGTH_SHORT).show();

            finish();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy:
                buy();
                break;
        }
    }
}
