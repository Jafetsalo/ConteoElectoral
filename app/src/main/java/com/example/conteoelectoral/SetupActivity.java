package com.example.conteoelectoral;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class SetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        picker = (NumberPicker)findViewById(R.id.numberpicker_main_picker);
        picker.setMaxValue(100);
        picker.setMinValue(1);
    }
    Elections SetupElection;
    NumberPicker picker;
    public void OnClickToMain(View Main)
    {
        //DONE_TODO: ADD METHOD TO UPDATE ELECTIONS PROPERTIES (Create Election Class) DONE
        //Retrieving from Activity

            String FirstCandidateName = ((TextView)findViewById(R.id.firstCandidateEditText)).getText().toString();
            String SecondCandidateName = ((TextView)findViewById(R.id.secondCandidateEditText)).getText().toString();
            String ThirdCandidateName = ((TextView)findViewById(R.id.thirdCandidateEditText)).getText().toString();


            if ((!FirstCandidateName.isEmpty()) || (!SecondCandidateName.isEmpty()) || (!ThirdCandidateName.isEmpty()))
            {
                    String[] Candidates = {FirstCandidateName,SecondCandidateName,ThirdCandidateName, "VOTO BLANCO"};
                    int NumberOfVoters = picker.getValue();
                    SetupElection = new Elections(Candidates, new Voter[NumberOfVoters]);
                    ShowToast("Candidate 1" + SetupElection.Candidates[0]);

            }
            else
            {
                    ShowToast("Falta Llenar Datos");
                    //TODO: Alert user
                    return;
            }

        GoToMainActivity(Main);


    }


    public void GoToMainActivity(View Main)
    {
        //Because parameters need to be saved, we should add another method to manage that and save
        //Setup data for elections

        Intent intentMain = new Intent(this, MainActivity.class);
        //Application myApp = this.getApplication();
        //THE MISTAKE WAS TO PUT EXTRA AFTER STARTING, I had to putExtra BEFORE starting
        intentMain.putExtra("SetupElection", SetupElection);

        startActivity(intentMain);




    }

    public void ShowToast(String Message)
    {
        Toast warningToast = Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM,0,0);

        warningToast.show();
    }
}