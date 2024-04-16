package secondWork.org;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure("hibernate.cfg.xml").build();
//        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
//        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
//
//        Session session = sessionFactory.openSession();

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        // Создание менеджера сущностей JPA
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Начало транзакции для JPA EntityManager
        entityManager.getTransaction().begin();

        // Открытие сессии Hibernate
        Session session = sessionFactory.openSession();

        // Получение списка объектов PurchaseList из базы данных с использованием JPA EntityManager
        List<PurchaseList> purchaseList = entityManager.createQuery("SELECT p FROM PurchaseList p", PurchaseList.class).getResultList();

        // Итерация по списку PurchaseList и конвертация данных в LinkedPurchaseList
        for (PurchaseList purchase : purchaseList) {
            LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
            LinkedPurchaseKey key = new LinkedPurchaseKey();

            // Получение и установка идентификатора студента
            int studentId = findStudentId(purchase.getStudentName(), entityManager);
            key.setStudentId(studentId);

            // Получение и установка идентификатора курса
            int courseId = findCourseId(purchase.getCourseName(), entityManager);
            key.setCourseId(courseId);

            linkedPurchase.setId(key);

            // Сохранение объекта LinkedPurchaseList в базе данных с помощью Hibernate Session
            session.save(linkedPurchase);
        }

        // Завершение транзакции для JPA EntityManager
        entityManager.getTransaction().commit();

        // Закрытие ресурсов
        entityManager.close();
        entityManagerFactory.close();
        session.close();
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private static int findStudentId(String studentName, EntityManager entityManager) {
        // Реализация поиска идентификатора студента по его имени с использованием JPA EntityManager
        // ...
        return 0; // Заглушка, замените на реальную логику
    }

    private static int findCourseId(String courseName, EntityManager entityManager) {
        // Реализация поиска идентификатора курса по его названию с использованием JPA EntityManager
        // ...
        return 0; // Заглушка, замените на реальную логику
    }
}


