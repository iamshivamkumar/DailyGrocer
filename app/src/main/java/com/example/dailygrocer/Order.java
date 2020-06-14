package com.example.dailygrocer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Order extends AppCompatActivity implements View.OnClickListener {
    ListView listViewNotification;
    DatabaseReference databasenotify;
    String uid;
    List<Notify> notifyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        uid = FirebaseAuth.getInstance().getUid();

        notifyList = new ArrayList<>();

        listViewNotification = (ListView) findViewById(R.id.notificationlistview);

        databasenotify = FirebaseDatabase.getInstance().getReference("Notify").child(uid);

    }

    @Override
    protected void onStart() {
        super.onStart();
        databasenotify.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notifyList.clear();

                for (DataSnapshot notifySnapshot : dataSnapshot.getChildren()){
                    Notify notify = notifySnapshot.getValue(Notify.class);
                    notifyList.add(notify);
                }

                NotifySeller adapter = new NotifySeller(Order.this,notifyList);
                listViewNotification.setAdapter(adapter);

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
