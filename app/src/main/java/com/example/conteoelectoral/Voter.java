package com.example.conteoelectoral;

import java.io.Serializable;
import java.util.Date;

public class Voter implements Serializable {

//This is for the login screen

    Date BirthDate;
    Integer NationalID;

    String FirstName;
    String LastName;

    int CandidateVotedIndex;
    public Voter(Date birthDate, Integer nationalID, String firstName, String lastName)
    {
        this.BirthDate=birthDate;
        this.NationalID=nationalID;
        this.FirstName=firstName;
        this.LastName = lastName;
        CandidateVotedIndex = 0;
    }

    public void Vote(int VoteForIndex)
    {
        CandidateVotedIndex = VoteForIndex;
    }

}
