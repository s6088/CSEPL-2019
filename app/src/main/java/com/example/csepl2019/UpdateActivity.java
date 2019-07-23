package com.example.csepl2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {

    private String matchid;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    
    private TextView matchName;
    private TextView score;
    private TextView overs;
    private TextView result;
    private TextView toss;

    private EditText over;
    private EditText ball;
    private EditText run;
    private EditText wicket;
    private EditText resultEdit;
    private CheckBox checkBox;


    private Button deleteBtn;
    private Button updateBtn;

    Match match;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        match = new Match();
        
        matchName = findViewById(R.id.matchName);
        score = findViewById(R.id.score);
        overs = findViewById(R.id.overs);
        result = findViewById(R.id.result);
        toss = findViewById(R.id.toss);


        over = findViewById(R.id.overText);
        ball = findViewById(R.id.ballText);
        run = findViewById(R.id.runText);
        wicket = findViewById(R.id.wicketText);
        resultEdit = findViewById(R.id.resultText);
        checkBox = findViewById(R.id.whichTeam);

        deleteBtn = findViewById(R.id.delete);
        updateBtn = findViewById(R.id.update);


        matchid = getIntent().getStringExtra("matchid");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("matches");


        reference.child(matchid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.exists()) {
                    return;
                }

                match = dataSnapshot.getValue(Match.class);

                String mteam1 = match.getTeam1();
                String mteam2 = match.getTeam2();

                if(match.isWhichTeam()){
                    mteam1 += "*";
                }
                else{
                    mteam2 += "*";
                }

                matchName.setText(mteam1 + " vs " + mteam2);
                score.setText(match.getRun1() +"/"+match.getWicket1() + "\t\t|\t\t" + match.getRun2()+ "/" + match.getWicket2());
                overs.setText("Overs " + match.getOver() + "." + match.getBall());
                result.setText(match.getResultStatus());
                toss.setText(match.getTossStatus());
                over.setText(match.getOver()+"");
                ball.setText(match.getBall()+"");

                if(match.isWhichTeam()) {
                    checkBox.setChecked(true);
                    run.setText(match.getRun1()+"");
                    wicket.setText(match.getWicket1()+"");
                }
                else {
                    checkBox.setChecked(false);
                    run.setText(match.getRun2()+"");
                    wicket.setText(match.getWicket2()+"");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                    Toast.makeText(UpdateActivity.this, "Match Deleted", Toast.LENGTH_LONG).show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    match.setOver(Integer.parseInt(over.getText().toString().trim()));
                    match.setBall(Integer.parseInt(ball.getText().toString().trim()));

                    if (checkBox.isChecked()) {
                        match.setWhichTeam(true);
                        match.setRun1(Integer.parseInt(run.getText().toString().trim()));
                        match.setWicket1(Integer.parseInt(wicket.getText().toString().trim()));
                    } else {
                        match.setWhichTeam(false);
                        match.setRun2(Integer.parseInt(run.getText().toString().trim()));
                        match.setWicket2(Integer.parseInt(wicket.getText().toString().trim()));
                    }
                    match.setResultStatus(resultEdit.getText().toString());
                    reference.child(matchid).setValue(match);
                }
                catch (Exception e){
                    Toast.makeText(UpdateActivity.this, "Operation Undone", Toast.LENGTH_LONG).show();
                }
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(matchid).removeValue();
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}
