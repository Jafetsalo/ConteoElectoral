package com.example.conteoelectoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CertificateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        SetupElectionCertificate = (Elections)getIntent().getSerializableExtra("SetupElectionVote");
        VotesDeposited = getIntent().getIntExtra("VotesDeposited",VotesDeposited);

        TextView textView = ((TextView)findViewById(R.id.textViewCertificate));


        textView.setText("Voto Completo. Certificado de Votación Número: 000"+ SetupElectionCertificate.Voters[VotesDeposited-1].NationalID + "000" + (VotesDeposited+1));
       // ShowToast(VotesDeposited +" Are votes "+ SetupElectionCertificate.Voters[VotesDeposited-1].BirthDate +" "+ SetupElectionCertificate.Voters[VotesDeposited-1].BirthDate +" "+ FirstName +" "+ LastName);

    }

    Elections SetupElectionCertificate;
    int VotesDeposited;
    public void GoToMainActivity(View MainActivity)
    {
        //Validation Needs to be done
        //TODO: Validate chosen candidate or blank vote compulsory before continue

        Intent intentMain = new Intent(this, MainActivity.class);
        //That was the best command :_)

        intentMain.putExtra("SetupElection", SetupElectionCertificate);
        intentMain.putExtra("VotesDeposited",VotesDeposited);

        startActivity(intentMain);


    }


    public void ShowToast(String Message)
    {
        Toast warningToast = Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM,0,0);

        warningToast.show();
    }

}