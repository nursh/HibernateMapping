package OneToManyUni;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "instructor")
@Entity
public class Instructor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "instructor", cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private List<Course> courses;

    public Instructor() {

    }

    public Instructor(String firstName, String lastName, String email) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addCourse(Course course) {

        if (courses == null) {
            courses = new ArrayList<Course>();
        }

        courses.add(course);
        course.setInstructor(this);
    }
}
