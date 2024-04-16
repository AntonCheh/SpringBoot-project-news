package secondWork.org;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int salary;
    private int age;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
