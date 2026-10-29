package com.ase;

import java.util.List;
import java.util.Scanner;

import com.ase.model.Course;
import com.ase.model.Student;
import com.ase.service.CourseService;
import com.ase.service.StudentService;

public class Main {
    private static StudentService service = new StudentService();
    private static CourseService courseService = new CourseService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("========================================");
        System.out.println(" Welcome to Student Management System ");
        System.out.println("========================================");

        while (running) {
            printMenu();
            System.out.print("Select an option: ");
            
            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input type!");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    listAllStudents();
                    break;
                case 2:
                    addNewStudentUI(scanner);
                    break;
                case 3:
                    triggerSearchUI(scanner);
                    break;
                case 4:
                    System.out.println("Average GPA: " + service.calculateAverageGpa());
                    break;
                case 5:
                    listAllCourses();
                    break;
                case 6:
                    addNewCourseUI(scanner);
                    break;
                case 7:
                    enrollStudentUI(scanner);
                    break;
                case 8:
                    listStudentCoursesUI(scanner);
                    break;
                case 9:
                    runCustomTeamModule(scanner);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. View All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Find Student by ID (Buggy)");
        System.out.println("4. Display Class GPA Average (Buggy)");
        System.out.println("5. View All Courses");
        System.out.println("6. Add New Course");
        System.out.println("7. Enroll Student in Course");
        System.out.println("8. View Student Courses");
        System.out.println("9. Custom Team Module ");
        System.out.println("0. Exit");
    }

    private static void runCustomTeamModule(Scanner scanner) {
        System.out.println("\n[Feature Module Placeholder]");
        System.out.println("Use AI to implement your assigned domain module (e.g., Attendance, Fees, Teacher Management).");
    }

    private static void listAllStudents() {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Student s : students) {
            s.printInfo();
        }
    }

    private static void addNewStudentUI(Scanner scanner) {
        String id = readRequiredInput(scanner, "Enter ID: ");
        String name = readRequiredInput(scanner, "Enter Name: ");
        String email = readRequiredInput(scanner, "Enter Email: ");
        
        double gpa = readValidGpa(scanner);

        service.addStudent(new Student(id, name, email, gpa));
        System.out.println("Student added successfully!");
    }

    private static String readRequiredInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static double readValidGpa(Scanner scanner) {
        while (true) {
            System.out.print("Enter GPA: ");
            try {
                double gpa = Double.parseDouble(scanner.nextLine());
                if (gpa >= 0.0 && gpa <= 4.0) {
                    return gpa;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("GPA must be a number from 0.0 to 4.0. Please try again.");
        }
    }

    private static void listAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            course.printInfo();
        }
    }

    private static void addNewCourseUI(Scanner scanner) {
        String id = readRequiredInput(scanner, "Enter Course ID: ");
        String name = readRequiredInput(scanner, "Enter Course Name: ");
        if (courseService.addCourse(new Course(id, name))) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Course ID already exists.");
        }
    }

    private static void enrollStudentUI(Scanner scanner) {
        if (courseService.getAllCourses().isEmpty()) {
            System.out.println("No courses found. Add a course first.");
            return;
        }
        listAllCourses();
        String studentId = readRequiredInput(scanner, "Enter Student ID: ");
        Student student = service.findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        String courseId = readRequiredInput(scanner, "Enter Course ID: ");
        Course course = courseService.findCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (student.enrollInCourse(course.id)) {
            System.out.println("Student enrolled in course successfully!");
        } else {
            System.out.println("Student is already enrolled in this course.");
        }
    }

    private static void listStudentCoursesUI(Scanner scanner) {
        String studentId = readRequiredInput(scanner, "Enter Student ID: ");
        Student student = service.findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        List<String> courseIds = student.getCourses();
        if (courseIds.isEmpty()) {
            System.out.println("Student is not enrolled in any courses.");
            return;
        }
        for (String courseId : courseIds) {
            Course course = courseService.findCourseById(courseId);
            if (course != null) {
                course.printInfo();
            }
        }
    }

    private static void triggerSearchUI(Scanner scanner) {
        System.out.print("Enter Student ID to Search: ");
        String id = scanner.nextLine();
        
        Student result = service.findStudentById(id);
        if (result != null) {
            result.printInfo();
        } else {
            System.out.println("Student not found.");
        }
    }
}
