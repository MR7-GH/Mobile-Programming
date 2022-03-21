package com.example.quera.Model;

import java.util.ArrayList;

public class Student extends User{

    public static ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Integer> classes = new ArrayList<>();
    private String stNum;

    public Student(String name, String username, String password, String stNum) {
        super(name, username, password);
        this.stNum = stNum;
        students.add(this);
    }

    public String getStNum() {
        return stNum;
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

    public static Student getStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username))
                return student;
        }
        return null;
    }
}
