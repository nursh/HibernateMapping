package EagerLazyLoading;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class EagerLazyDemo {

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
            int id = 1;
            Instructor instructor = session.get(Instructor.class, id);
            System.out.println(instructor);
            System.out.println(instructor.getCourses());

            /*
                Lazy loading with HQL

                Query<Instructor> query =
                        session.createQuery("select i from Instructor i "
                                            + "JOIN FETCH i.courses"
                                            + "where i.id=:instructorId"
                                            ,Instructor.class);

                int id = 1;
                query.setParameter("instructorId", id);
                Instructor instructor = query.getSingleResult();

             */
            session.getTransaction().commit();
        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}

