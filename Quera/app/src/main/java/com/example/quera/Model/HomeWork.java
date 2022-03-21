package com.example.quera.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeWork {
    public ArrayList<HomeWork> homeWorks = new ArrayList<>();
    private String name;
    private String question;
    private int classID;
    private HashMap<String,String> answers = new HashMap<>();
    private HashMap<String,Integer> points = new HashMap<>();

    public HomeWork(String name, String question,int classID) {
        this.name = name;
        this.question = question;
        this.classID = classID;
        homeWorks.add(this);
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public int getClassID() {
        return classID;
    }

    public HashMap<String, String> getAnswers() {
        return answers;
    }

    public HashMap<String, Integer> getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPoint(String user, int point) {
        points.put(user,point);
    }

    public void addAnswer (String user, String answer) {
        answers.put(user,answer);
    }
}
