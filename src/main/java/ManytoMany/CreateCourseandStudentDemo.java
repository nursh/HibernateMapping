package ManytoMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseandStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Course war = new Course("War strategy and weapon handling");
            session.save(war);

            Student stud1 = new Student("John", "Mack", "Jmack@yahoo.com");
            Student stud2 = new Student("Goat", "Legs", "crazyLegs@gmail.com");

            war.addStudent(stud1);
            war.addStudent(stud2);

            session.save(stud1);
            session.save(stud2);

            session.getTransaction().commit();
        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }

}

