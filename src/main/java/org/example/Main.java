package org.example;
import org.example.entity.Course;
import org.example.entity.Professor;
import org.example.entity.Student;
import org.example.service.UniversityService;
import org.hibernate.service.Service;

import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        UniversityService service = new UniversityService();

        Professor professor =new Professor();
        professor.setName("Jack");
        professor.setDepartment("Engineering");

        Course course1 = new Course();
        course1.setTitle("java");
        course1.setCredits(3);
        course1.setProfessor(professor);

        Course course2 = new Course();
        course2.setTitle("python");
        course2.setCredits(5);
        course2.setProfessor(professor);

        Student student1 = new Student();
        student1.setName("Jack");
        student1.setEmail("jack@gmail.com");

        Set<Course> courses = new HashSet<>();
        courses.add(course1);
        courses.add(course2);

        student1.setCourses(courses);

        service.createStudent(student1);


        Student fetchStudent= service.getStudentById(student1.getStudentId());

        System.out.println(fetchStudent.getName());

        System.out.println(fetchStudent.getEmail());
        service.updateStudentEmail(fetchStudent.getStudentId(),"jackiechan@gmail.com");
        Student fetchStudent1= service.getStudentById(student1.getStudentId());


        System.out.println(fetchStudent1.getEmail());

        service.deleteStudent(student1.getStudentId());

    }
}