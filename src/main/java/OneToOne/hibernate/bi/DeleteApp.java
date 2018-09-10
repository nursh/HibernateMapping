package OneToOne.hibernate.bi;


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
            InstructorDetail detail = session.get(InstructorDetail.class, id);
            if (detail != null) {

                // Cascades delete with Instructor detail
                session.delete(detail);
            }

            // To Remove Instructor Detail without removing instructor
            // Remove Cascade.all in java code
            // set Instructor of detail to null before removing it else it raises error
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }
}
