package com.example.csepl2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Date;
import java.sql.Timestamp;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.ServerValue;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {

    private Button cancelBtn;
    private Button createBtn;

    private EditText team1;
    private EditText team2;
    private EditText toss;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        cancelBtn = findViewById(R.id.cancelBtn);
        createBtn = findViewById(R.id.createBtn);
        team1 = findViewById(R.id.team1Text);
        team2 = findViewById(R.id.team2Text);
        toss = findViewById(R.id.tossText);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("matches");

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMatch();
            }
        });
    }


    public void createMatch(){
        String key = reference.push().getKey();
        Match match = new Match();
        match.setId(key);
        match.setTeam1(team1.getText().toString().trim());
        match.setTeam2(team2.getText().toString().trim());
        match.setTossStatus(toss.getText().toString().trim());
        match.setTimestamp(new Date().getTime());


        reference.child(key).setValue(match)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateActivity.this, "New match created", Toast.LENGTH_LONG).show();
                        team1.setText("");
                        team2.setText("");
                        toss.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateActivity.this, "No match created", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
