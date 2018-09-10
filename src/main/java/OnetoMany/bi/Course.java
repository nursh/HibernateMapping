package OnetoMany.bi;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Course(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course[" +
                "id=" + id +
                ", title='" + title + '\'' +
                ']';
    }
}
