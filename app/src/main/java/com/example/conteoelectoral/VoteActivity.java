package com.example.conteoelectoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        SetupElectionVote = (Elections)getIntent().getSerializableExtra("SetupElectionLogin");

        VotesDeposited = getIntent().getIntExtra("VotesDeposited",VotesDeposited);
        voter = SetupElectionVote.Voters[VotesDeposited];
        TextView textView = ((TextView)findViewById(R.id.textViewCode));
        textView.setText("Código de boleta: 000"+ VotesDeposited+1+"000");


    }

   Elections SetupElectionVote;
   int VotesDeposited;
   int VoteChoose = -1;

   boolean AlreadyVoted = false;

    Voter voter;
    public void GoToCertificateActivity(View CertificateActivity)
    {
        //Validation Needs to be done
        //TODO: Validate chosen candidate or blank vote compulsory before continue



        if(VoteChoose != -1)
        {
            SetupElectionVote.Voters[VotesDeposited].CandidateVotedIndex = VoteChoose;



            if (VotesDeposited<SetupElectionVote.Voters.length && !AlreadyVoted)
            {
                voter.CandidateVotedIndex = VoteChoose;

                Intent intentCertificate = new Intent(this, CertificateActivity.class);
                //That was the best command :_)
                intentCertificate.putExtra("SetupElectionVote", SetupElectionVote);
                VotesDeposited++;
                intentCertificate.putExtra("VotesDeposited",VotesDeposited);

                AlreadyVoted = true;
                startActivity(intentCertificate);
            }
            else
            {
                ShowToast("Votación cerrada, regrese al menú principal");
                Intent intentCertificate = new Intent(this, MainActivity.class);
                //That was the best command :_)
                intentCertificate.putExtra("SetupElection", SetupElectionVote);
                intentCertificate.putExtra("VotesDeposited",VotesDeposited);
            }

        }
        else
        {
            ShowToast("Debe elegir un cuadro de la boleta para votar");
        }



    }


    public void OnClickCandidate(View VoteActivity)
    {
        // Get the index of the candidate from the tag of the view
        VoteChoose = Integer.parseInt(VoteActivity.getTag().toString());

        // Show the toast message according to the index
        if (VoteChoose == 3) {
            ShowToast("Eligió el " + SetupElectionVote.Candidates[VoteChoose]);
        } else {
            ShowToast("Eligió al candidato " + SetupElectionVote.Candidates[VoteChoose]);
        }
    }

    public void ShowToast(String Message)
    {
        Toast warningToast = Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM,0,0);

        warningToast.show();
    }


}