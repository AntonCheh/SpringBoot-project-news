package secondWork.org;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions")
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;
    @Column(name = "subscription_date")
    private Timestamp subscriptionsDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
