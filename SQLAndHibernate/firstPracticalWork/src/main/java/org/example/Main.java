package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Query<Object[]> query = session.createQuery(
                "SELECT table.courseName, MIN(table.subscriptionDate), MAX(table.subscriptionDate), COUNT(table) " +
                        "FROM PurchaseList table " + "GROUP BY table.courseName", Object[].class);

        List<Object[]> results = query.getResultList();

        // Создаем карту для хранения информации о каждом курсе
        Map<String, Object[]> courseInfoMap = new HashMap<>();

        // Определяем продолжительность периода продаж и количество покупок для каждого курса
        for (Object[] result : results) {
            String courseName = (String) result[0];
            Date minSubscriptionDate = new Date(((java.sql.Timestamp) result[1]).getTime());
            Date maxSubscriptionDate = new Date(((java.sql.Timestamp) result[2]).getTime());
            Long purchaseCount = (Long) result[3]; // Приводим к типу Long
            Object[] courseInfo = {minSubscriptionDate, maxSubscriptionDate, purchaseCount};
            courseInfoMap.put(courseName, courseInfo);
        }

        // Выводим информацию о количестве покупок и среднем количестве покупок в месяц для каждого курса
        for (Map.Entry<String, Object[]> entry : courseInfoMap.entrySet()) {
            String courseName = entry.getKey();
            Object[] courseInfo = entry.getValue();

            // Определяем продолжительность периода продаж в месяцах
            long months = ((Date) courseInfo[1]).getMonth() - ((Date) courseInfo[0]).getMonth();


            // Определяем среднее количество покупок в месяц
            double averagePerMonth = (double) ((Long) courseInfo[2]) / months;

            System.out.println("Course Name: " + courseName + ", Purchase Count: " + courseInfo[2] + ", Average per Month: " + averagePerMonth);
        }

        session.close();
        sessionFactory.close();
    }
}