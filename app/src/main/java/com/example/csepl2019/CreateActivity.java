package com.example.csepl2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateActivity extends AppCompatActivity {

    private Button cancelBtn;
    private Button createBtn;

    private EditText team1Edit;
    private EditText team2Edit;
    private EditText tossStatusEdit;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        cancelBtn = findViewById(R.id.cancelBtn);
        createBtn = findViewById(R.id.createBtn);
        team1Edit = findViewById(R.id.team1Edit);
        team2Edit = findViewById(R.id.team2Edit);
        tossStatusEdit = findViewById(R.id.tossStatusEdit);
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
        match.setTeam1(team1Edit.getText().toString().trim());
        match.setTeam2(team2Edit.getText().toString().trim());
        match.setTossStatus(tossStatusEdit.getText().toString().trim());


        reference.child(key).setValue(match)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateActivity.this, "New match created", Toast.LENGTH_LONG).show();
                        team1Edit.setText("");
                        team2Edit.setText("");
                        tossStatusEdit.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateActivity.this, "Operation Undone", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
