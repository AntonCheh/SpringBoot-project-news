package secondWork.org;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Courses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private int duration;
  @Enumerated(EnumType.STRING)
  private CoursesType type;
  private String description;
  @Column(name = "teacher_id")
  private int teacherId;
  @Column(name = "students_count")
  private int studentsCount;
  private int price;
  @Column(name = "price_Per_Hour")
  private float pricePerHour;
}
