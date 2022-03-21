package com.example.quera.Model;

import java.util.ArrayList;

public class Class {
    public static ArrayList<Class> classes = new ArrayList<>();
    private static int IDCounter = 1;
    private String name;
    private String master;
    private int ID;
    private ArrayList<Student> members = new ArrayList<>();
    private ArrayList<HomeWork> tasks = new ArrayList<>();

    public static int getIDCounter() {
        return IDCounter;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Student> getMembers() {
        return members;
    }

    public String getMaster() {
        return master;
    }

    public ArrayList<HomeWork> getTasks() {
        return tasks;
    }

    public Class(String name,String master) {
        this.master = master;
        this.name = name;
        ID = IDCounter;
        IDCounter++;
        classes.add(this);
    }

    public static Class getClassByID(int id) {
        for (Class aClass : classes) {
            if(id == aClass.getID())
                return aClass;
        }
        return null;
    }

    public HomeWork getTaskByName(String name) {
        for (HomeWork task : tasks) {
            if(task.getName().equals(name))
                return task;
        }
        return null;
    }

}
