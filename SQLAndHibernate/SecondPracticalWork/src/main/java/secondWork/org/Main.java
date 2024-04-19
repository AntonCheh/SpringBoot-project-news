package secondWork.org;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
  public static void main(String[] args) {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
      .configure("hibernate.cfg.xml").build();
    Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
    SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

    Session session = sessionFactory.openSession();

    List<PurchaseList> purchaseList = session.createQuery("FROM PurchaseList", PurchaseList.class).getResultList();

    for (PurchaseList purchase : purchaseList) {
      String studentName = purchase.getStudentName();
      String courseName = purchase.getCourseName();

      Query studentQuery = session.createQuery("SELECT s.id FROM Students s WHERE s.name = :studentName");
      studentQuery.setParameter("studentName", studentName);
      Integer studentId = (Integer) studentQuery.getSingleResult();

      Query courseQuery = session.createQuery("SELECT c.id FROM Courses c WHERE c.name = :courseName");
      courseQuery.setParameter("courseName", courseName);
      Integer courseId = (Integer) courseQuery.getSingleResult();

      // Создаем новый объект LinkedPurchaseList с составным ключом и найденными идентификаторами студента и курса
      LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
      SubscriptionKey subscriptionKey = new SubscriptionKey(studentId, courseId);
      linkedPurchase.setId(subscriptionKey);

      // Сохраняем объект LinkedPurchaseList в базу данных
      session.beginTransaction();
      session.save(linkedPurchase);
      session.getTransaction().commit();
    }

    session.close();
    sessionFactory.close();
  }
}


