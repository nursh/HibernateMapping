package com.onetoone.app;

import com.onetoone.entity.Instructor;
import com.onetoone.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateApp {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {

//            InstructorDetail details =
//                    new InstructorDetail("onetoone.youtube.com", "Reading books");
//            Instructor instructor =
//                    new Instructor("Nur", "Sheikh", "nur@gmail.com");
//            instructor.setInstructorDetail(details);
//
            InstructorDetail details =
                    new InstructorDetail("layla.youtube.com", "Watching youtube videos");
            Instructor instructor =
                    new Instructor("Layla", "Masrur", "lmasrur@gmail.com");
            instructor.setInstructorDetail(details);
            session.beginTransaction();

            // It will also save the instructor detail because of Cascade.all
            session.save(instructor);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
