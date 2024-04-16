package secondWork.org;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @Column(name = "registration_date")
    private Timestamp registrationDate;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
