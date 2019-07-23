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
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase database;
    private DatabaseReference reference;
    
    private FloatingActionButton fab;
    private ArrayList <Match> matchList;
    private ArrayList <String> matchNameList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchList = new ArrayList<>();
        matchNameList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("matches");
        listView = findViewById(R.id.list);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                matchList.clear();
                matchNameList.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Match match = postSnapshot.getValue(Match.class);
                    matchList.add(match);
                    }
                Collections.reverse(matchList);
                for(int i=0, j=matchList.size(); i<matchList.size(); i++, j--){
                    Match match = matchList.get(i);
                    matchNameList.add("Match\t" + j + "\t\t|\t" + match.getTeam1() + " vs " + match.getTeam2());
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
                android.R.layout.simple_list_item_1, android.R.id.text1, matchNameList);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("matchid", matchList.get(position).getId());
                startActivity(intent);

            }

        });

    }
}
