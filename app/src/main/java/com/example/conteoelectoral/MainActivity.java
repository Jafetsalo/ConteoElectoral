package com.example.conteoelectoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This had to be done while CREATING :p @☻
        SetupElectionMain = (Elections)getIntent().getSerializableExtra("SetupElection");
        //A lot of beginner errors! NAMES MUST MATCH
        VotesDeposited = getIntent().getIntExtra("VotesDeposited",VotesDeposited);

        if (SetupElectionMain != null)
        {
            PeopleToVote = SetupElectionMain.Voters.length;
        }



    }

    Elections SetupElectionMain;
    int VotesDeposited = 0 ;
    int PeopleToVote = 1;
    //Create method to Jump to SetupActivity from OnClick event for button buttonSetup

    public void GoToSetupActivity(View setup)
    {
        Intent intentSetup = new Intent(this, SetupActivity.class);
        //That was the best command :_)

        startActivity(intentSetup);

        //Explanation: We make an intent, and the this is the local Class 'Context'
        // we also need a second activity we intend to open
        //Finally we run the method startActivity
        //We're just missing setting this method as OnClick for buttonSetup


        

    }

    public void GoToResultsActivity(View Results)
    {
        //TODO: Create a validation fumble to make sure we open results when
        // 1. Setup has been set (DONE) 2. Elections have begun (at least a vote) (Pending)

        //ShowToast(VotesDeposited + " Son los votos");

        if ((SetupElectionMain != null) && (VotesDeposited > 0))
        {
            Intent intentResults = new Intent(this, ResultsActivity.class);
            //We literally forgot to send anything at all :P
            intentResults.putExtra("SetupElectionMain", SetupElectionMain);
            intentResults.putExtra("VotesDeposited",VotesDeposited);

            startActivity(intentResults);
        }
        else
        {
            ShowToast("Faltan Votos");
        }

    }

    public void GoToLoginActivity(View Login)
    { //TODO: Make sure we're not over-voting (In progress)
        //HOWTO: Count how many votes there are, and how many already voted
        //IDEA: Create a variable that counts votes logged and completed "Voted" in their respective activities
        //IDEA:

        if (SetupElectionMain != null)
        {
            PeopleToVote = SetupElectionMain.Voters.length;
        }


        if ((SetupElectionMain != null) && (VotesDeposited < PeopleToVote))
        {

            Intent intentLogin = new Intent(this, LoginActivity.class);
            //Sending SetupElection to LoginActivity
            intentLogin.putExtra("SetupElectionMain", SetupElectionMain);
            intentLogin.putExtra("VotesDeposited",VotesDeposited);
            startActivity(intentLogin);
        }
        else
        {
            if (VotesDeposited >= PeopleToVote) {ShowToast("Votantes Máximos Alcanzados");}
            else {ShowToast("Faltan Datos");}
            //TODO: Alert user
            return;
        }



    }




    public void ShowToast(String Message)
    {
        Toast warningToast = Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM,0,0);

        warningToast.show();
    }

}