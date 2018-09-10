package OneToManyUni;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;


    // It isn't cascade.all because we don't want to delete an instructor, when we delete a course
    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    public Course(String title) {
        this.title = title;
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<Review>();
        }

        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Course[" +
                "id=" + id +
                ", title='" + title + '\'' +
                ']';
    }
}
