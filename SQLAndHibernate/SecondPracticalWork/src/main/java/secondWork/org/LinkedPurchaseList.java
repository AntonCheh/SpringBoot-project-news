package secondWork.org;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
  @Table(name = "linked_purchase_list")
  public class LinkedPurchaseList  {

  @EmbeddedId
  private SubscriptionKey id;

  @ManyToOne
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  private Students student;

  @ManyToOne
  @JoinColumn(name = "course_id", insertable = false, updatable = false)
  private Courses course;

  public SubscriptionKey getId() {
    return id;
  }

  public void setId(SubscriptionKey id) {
    this.id = id;
  }

  public Students getStudent() {
    return student;
  }

  public void setStudent(Students student) {
    this.student = student;
  }

  public Courses getCourse() {
    return course;
  }

  public void setCourse(Courses course) {
    this.course = course;
  }
}
