package OneToManyUni;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseandReviewDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Course war = new Course("War strategy and weapon handling");
            war.addReview(new Review("What a manly course, Loved it"));
            war.addReview(new Review("What is your Occupation!!"));
            war.addReview(new Review("Men and their macho stereotypes"));

            session.save(war);

            session.getTransaction().commit();
        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}

