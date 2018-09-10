package OnetoMany.bi;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            InstructorDetail details =
                    new InstructorDetail("onetoone.youtube.com", "Reading books");
            Instructor instructor =
                    new Instructor("Nur", "Sheikh", "nur@gmail.com");

            instructor.setInstructorDetail(details);
            session.save(instructor);
            instructor.setInstructorDetail(details);


            session.getTransaction().commit();
        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}

