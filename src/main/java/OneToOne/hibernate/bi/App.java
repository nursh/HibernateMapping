package OneToOne.hibernate.bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 2;
            InstructorDetail detail = session.get(InstructorDetail.class, id);
            System.out.println("Details: " + detail);
            System.out.println("Instructor: " + detail.getInstructor());


            session.getTransaction().commit();
        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}

