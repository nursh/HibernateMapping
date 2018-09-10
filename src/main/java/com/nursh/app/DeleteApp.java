package com.nursh.app;

import com.nursh.entity.Instructor;
import com.nursh.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            int id = 1;
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            if (instructor != null) {
                session.delete(instructor);
            }
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
