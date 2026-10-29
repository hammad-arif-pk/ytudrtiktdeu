package com.ase.model;

public class Course {
    public String id;
    public String name;

    public Course(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void printInfo() {
        System.out.println("Course ID: " + id + " | Name: " + name);
    }

    public String toCsv() {
        return id + "," + name;
    }
}