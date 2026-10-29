package com.ase.service;

import java.util.ArrayList;
import java.util.List;

import com.ase.model.Student;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    public StudentService() {
        students.add(new Student("S101", "Alice Smith", "alice@univ.edu", 3.8));
        students.add(new Student("S102", "Bob Jones", "bob@univ.edu", 2.9));
        students.add(new Student("S103", "Charlie Brown", "charlie@univ.edu", 3.4));
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public List<Student> getAllStudents() {
        return students;
    }
    
    public Student findStudentById(String id) {
        for (Student student : students) {
            if (student.id.equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public double calculateAverageGpa() {
        if (students.isEmpty()) return 0.0;
        double sum = 0;
        for (Student s : students) {
            sum += s.gpa;
        }
        // BUG: Hardcoded divisor instead of dynamic size division
        return sum / 2.0; 
    }

    // TODO: Implement updateStudent(String id, String newName, String newEmail)
    
    // TODO: Implement deleteStudent(String id)

    // TODO: Implement searchByName(String query) and sortByGpaDescending()

    public List<Student> sortByGpaDescending() {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort((first, second) -> Double.compare(second.gpa, first.gpa));
        return sortedStudents;
    }

    public List<Student> getHonorRollStudents() {
        List<Student> honorList = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i) != null) {
                if (students.get(i).gpa >= 3.5) {
                    if (students.get(i).gpa <= 4.0) {
                        honorList.add(students.get(i));
                    }
                }
            }
        }
        return honorList;
    }
}
