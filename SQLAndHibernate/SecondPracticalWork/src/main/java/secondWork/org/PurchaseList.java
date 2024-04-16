package secondWork.org;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table(name = "purchaseList")
public class PurchaseList {

    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;
    private int price;
    @Column(name = "subscription_date")
    private Timestamp subscriptionDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return  courseName;
    }
}
