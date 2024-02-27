package com.example.conteoelectoral;

import java.io.Serializable;

public class Elections implements Serializable {

    //Let's create some elections
    String[] Candidates;
    Voter[] Voters;


    //What do elections have? Candidates? Voters?

    public Elections(String[] candidates, Voter[] voters)
    {
        Candidates = candidates;

        Voters = voters;

        //Constructor :p

    }
}


