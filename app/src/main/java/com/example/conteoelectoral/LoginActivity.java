package com.example.conteoelectoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //picker = (NumberPicker)findViewById(R.id.numberpicker_main_picker);

        datePicker = (DatePicker)findViewById(R.id.DatePickerBirthDate);
        SetupElectionLogin = (Elections)getIntent().getSerializableExtra("SetupElectionMain");
        VotesDeposited = getIntent().getIntExtra("VotesDeposited",VotesDeposited);

        calendar.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);



        datePicker.setMaxDate(System.currentTimeMillis());


    }

    Elections SetupElectionLogin;
    int VotesDeposited = 0;

    Calendar calendar = Calendar.getInstance();

    private DatePicker datePicker;


    public void GoToVoteActivity(View VoteActivity)
    {

        if(CheckAdult())
        {
            //We checked you're 18+ we can proceed
            //
            Intent intentVote = new Intent(this, VoteActivity.class);
            //Add voter
            String FirstName = ((TextView)findViewById(R.id.editTextFirstName)).getText().toString();
            String LastName = ((TextView)findViewById(R.id.editTextLastName)).getText().toString();
            String documentID = ((TextView)findViewById(R.id.editTextNationalId)).getText().toString();
            Integer DocumentID= Integer.parseInt(documentID);

            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            //
            SetupElectionLogin.Voters[VotesDeposited] = new Voter(calendar.getTime(),DocumentID,FirstName,LastName);
            //Serializable is an issue :(
            //THE ONLY ISSUE WAS TO NOT LET NULL UNINITIALIZED FIELDS
            //They must all be initialized with the class!!



            //For now, I'll bypass

            intentVote.putExtra("SetupElectionLogin", SetupElectionLogin); //It has a problem serializing it
            intentVote.putExtra("VotesDeposited",VotesDeposited);

            ShowToast(VotesDeposited +" Are votes "+ calendar.getTime() +" "+ DocumentID +" "+ FirstName +" "+ LastName);

            startActivity(intentVote); //There is an error here. Something is null
        }





    }


    public boolean CheckAdult()
    {
        boolean IsAdult = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());


        if((int)(datePicker.getYear()) < ((int)(calendar.get(Calendar.YEAR)-18)))
        {
            //YES ADULT
            IsAdult = true;
        }
        else if (( ((int)(datePicker.getYear()) == ((int)(calendar.get(Calendar.YEAR)-18))) && ((int)(datePicker.getMonth()) <= calendar.get(Calendar.MONTH) ))
                ||(((int)(datePicker.getYear()) == ((int)(calendar.get(Calendar.YEAR)-18)))
                &&((int)(datePicker.getMonth()) == calendar.get(Calendar.MONTH))
                &&((int)(datePicker.getDayOfMonth()) < calendar.get(Calendar.DAY_OF_MONTH))))

        {

            //YES ADULT
            IsAdult = true;

        }
        else
        {
            ShowToast("Debes ser mayor de 18 años para votar" + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH));
        }


        return IsAdult;
    }


    public void ShowToast(String Message)
    {
        Toast warningToast = Toast.makeText(getApplicationContext(), Message, Toast.LENGTH_SHORT);
        warningToast.setGravity(Gravity.BOTTOM,0,0);

        warningToast.show();
    }



}