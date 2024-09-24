package org.example.service;

import org.example.entity.Student;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UniversityService {

    public void createStudent(Student student) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }


    public Student getStudentById(int studentid) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, studentid);
        session.close();
        return student;
    }

    public void updateStudentEmail(int studentId, String newEmail) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Retrieve the student object
            Student student = session.get(Student.class, studentId);

            if (student != null) {
                // Update the student's email
                student.setEmail(newEmail);

                // Changes will be tracked automatically; no need to call session.update(student)
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Consider using a logger instead
        }
    }

    public void deleteStudent(int Studentid) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class, Studentid);
        session.delete(student);
        session.getTransaction().commit();
        session.close();

    }
    public List<Student> getAllStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student").list();
        session.getTransaction().commit();
        session.close();
        return students;
    }

        public List<Student> getStudentsEnrolledCourse(String courseTitle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql="SELECT s FROM Student s JOIN s.courses c where c.title =:courseTitle";
        List<Student> students = session.createQuery(hql,Student.class).setParameter("courseTitle", courseTitle).list();
        session.getTransaction().commit();
        session.close();
        return students;
    }
}
