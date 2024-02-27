package com.example.conteoelectoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        SetupElectionResults = (Elections)getIntent().getSerializableExtra("SetupElectionMain");

        VotesDeposited = getIntent().getIntExtra("VotesDeposited",VotesDeposited);


        TextView Title = findViewById(R.id.textViewTitle);

        textViewsCandidates[0] = findViewById(R.id.textViewCandidate1);
        textViewsCandidates[1] = findViewById(R.id.textViewCandidate2);
        textViewsCandidates[2] = findViewById(R.id.textViewCandidate3);
        textViewsCandidates[3] = findViewById(R.id.textViewCandidate4);

        MostVoted = findViewById(R.id.textViewMostVoted);

        TextView DateTimeNow = findViewById(R.id.textViewDateTimeNow);

        if (VotesDeposited < SetupElectionResults.Voters.length)
        {
            Title.setText("Reporte Parcial de Resultados Oficial");
        }
        else
        {
            Title.setText("Reporte Final de Resultados Oficial");
        }



        DateTimeNow.setText(GetTimeNow());

        calculateVote();
    }

    TextView[] textViewsCandidates = new TextView[4];
    Elections SetupElectionResults;
    int VotesDeposited;
    TextView MostVoted;

    public void GoToMainActivity(View Main)
    {
        Intent intentMain = new Intent(this, MainActivity.class);
        //That was the best command :_)

        intentMain.putExtra("SetupElection", SetupElectionResults);
        intentMain.putExtra("VotesDeposited",VotesDeposited);


        startActivity(intentMain);

        //Going back to main

    }

    public void calculateVote () {
        //This is a tool created by salome :3

        int[] CandidateVoteCount = new int[SetupElectionResults.Candidates.length];
        int myIndex = 0;


        for(int Counter: CandidateVoteCount) //Numero de candidatos
        {
            Counter = 0;

            for (Voter Vote :SetupElectionResults.Voters) //Numero de votantes
            {

                if (Vote.CandidateVotedIndex == myIndex)
                {
                    Counter++;
                }

            }

            textViewsCandidates[myIndex].setText("Candidato: " + SetupElectionResults.Candidates[myIndex] + " Votos: " + Counter);

            if (myIndex >=1 &&
                    (Counter>CandidateVoteCount[myIndex-1]))
            {
                MostVoted.setText("Más votado: " + SetupElectionResults.Candidates[myIndex]);
            }
            else if (myIndex == 0)
            {
                MostVoted.setText("Más votado: " + SetupElectionResults.Candidates[myIndex]);
            }
            myIndex++;

        }





    }

    public String GetTimeNow()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss XXX", Locale.getDefault());
        myFormat.setTimeZone(TimeZone.getTimeZone("GMT-05:00"));

        return myFormat.format(calendar.getTime());
    }



}