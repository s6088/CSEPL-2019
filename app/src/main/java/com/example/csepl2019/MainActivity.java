package com.example.csepl2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FloatingActionButton fab;
    private ArrayList <Match> matchList;
    private ArrayList <String> values;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchList = new ArrayList<>();
        values = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        listView = findViewById(R.id.list);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        reference.child("match").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                matchList.clear();
                values.clear();

                for(DataSnapshot ymdSnapshot : dataSnapshot.getChildren()){

                    for(DataSnapshot repSnapshot : ymdSnapshot.getChildren()){
                        Match match = repSnapshot.getValue(Match.class);
                        matchList.add(match);
                        values.add(match.getTeam1() + " vs " + match.getTeam2());
                    }
                }

                updateList();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    void updateList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert


            }

        });

    }
}
