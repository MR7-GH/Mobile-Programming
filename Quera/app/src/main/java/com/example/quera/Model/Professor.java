package com.example.quera.Model;

import android.view.inspector.StaticInspectionCompanionProvider;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

public class Professor extends User{

    public static ArrayList<Professor> professors = new ArrayList<>();
    private ArrayList<Integer> classes = new ArrayList<>();
    private String university;
    public Professor(String name, String username, String password, String university) {
        super(name,username,password);
        this.university = university;
        professors.add(this);
    }

    public String getUniversity() {
        return university;
    }

    public ArrayList<Integer> getClasses() {
        return classes;
    }

    public void addClass(int id) {
        classes.add(id);
    }

    public void removeClass(int id) {
        if(classes.contains(id))
            classes.remove(id);
    }
}


