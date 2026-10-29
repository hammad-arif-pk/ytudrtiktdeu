package com.ase.service;

import java.util.ArrayList;
import java.util.List;

import com.ase.model.Course;

public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public boolean addCourse(Course course) {
        if (findCourseById(course.id) != null) {
            return false;
        }
        courses.add(course);
        return true;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public Course findCourseById(String id) {
        for (Course course : courses) {
            if (course.id.equalsIgnoreCase(id)) {
                return course;
            }
        }
        return null;
    }
}