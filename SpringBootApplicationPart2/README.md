
SpringBootApplicationPart2 - Data Collector

Описание проекта
Этот проект представляет собой приложение Spring Boot, 
которое собирает данные из различных источников, 
включая файлы разных форматов и веб-страницы, и сохраняет их в базе данных PostgreSQL. 
Проект также предоставляет API для управления категориями и новостями, 
включая функциональность CRUD (создание, чтение, обновление, удаление)
с использованием Spring Data JPA и Liquibase для управления схемой базы данных.

Структура проекта
Основные классы и их функции

CategoryController - REST-контроллер для управления категориями новостей.
NewsController - REST-контроллер для управления новостями.
CategoryService - Сервис для бизнес-логики, связанной с категориями.
NewsService - Сервис для бизнес-логики, связанной с новостями.
CategoryTable - JPA сущность для категории новостей.
NewsTable - JPA сущность для новости.
CRUDservice - Интерфейс для CRUD операций.

Установка
Требования
Java 8+
Maven
Docker
PostgreSQL

Установка
Перейдите в директорию проекта:
cd SpringBootApplicationPart2
Запустите контейнер базы данных PostgreSQL в Docker:

docker run --name postgres -e POSTGRES_DB=mydatabase -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
Установите зависимости с помощью Maven:
mvn install

Настройка
Настройки подключения к базе данных 
PostgreSQL и Liquibase находятся в файле application.yml:


spring:
datasource:
url: jdbc:postgresql://localhost:5432/mydatabase
username: 
password: 
driver-class-name: org.postgresql.Driver
jpa:
show-sql: true
hibernate:
ddl-auto: create
liquibase:
change-log: classpath:db/changelog/db.changelog-master.xml
enabled: true

Использование
Запустите приложение Spring Boot:


mvn spring-boot:run
Приложение будет доступно по адресу http://localhost:8080.

Примеры использования API
Управление категориями
Получение списка всех категорий:

URL: /api/categories
HTTP-метод: GET
Пример ответа:

[
{ "id": 1, "title": "Web-разработка" },
{ "id": 2, "title": "Нейросети" }
]
Получение категории по ID:

URL: /api/categories/{id}
HTTP-метод: GET
Пример ответа:
json
Копировать код
{ "id": 1, "title": "Web-разработка" }
Создание новой категории:

URL: /api/categories
HTTP-метод: POST
Пример запроса:
json
Копировать код
{ "title": "Дизайн" }
Пример ответа:

{ "id": 3, "title": "Дизайн" }
Обновление существующей категории:

URL: /api/categories/{id}
HTTP-метод: PUT
Пример запроса:

{ "id": 3, "title": "Web-дизайн" }
Пример ответа:

{ "id": 3, "title": "Web-дизайн" }
Удаление категории по ID:

URL: /api/categories/{id}
HTTP-метод: DELETE
Управление новостями
Получение новости по ID:

URL: /api/news/{id}
HTTP-метод: GET
Пример ответа:

{
"id": 1,
"title": "Заголовок новости",
"text": "Текст новости",
"date": "2023-10-20T12:00:00Z",
"category": "Web-разработка"
}
Получение списка новостей:

URL: /api/news
HTTP-метод: GET
Пример ответа:

[
{
"id": 1,
"title": "Заголовок новости 1",
"text": "Текст новости 1",
"date": "2023-10-20T12:00:00Z",
"category": "Web-разработка"
},
{
"id": 2,
"title": "Заголовок новости 2",
"text": "Текст новости 2",
"date": "2023-10-21T10:30:00Z",
"category": "Дизайн"
}
]
Создание новой новости с категорией:

URL: /api/news
HTTP-метод: POST
Пример запроса:

{
"title": "Новая новость",
"text": "Текст новости",
"category": "Нейросети"
}
Пример ответа:

{
"id": 3,
"title": "Новая новость",
"text": "Текст новости",
"date": "2023-10-22T14:45:00Z",
"category": "Нейросети"
}
Обновление существующей новости с категорией:

URL: /api/news/{id}
HTTP-метод: PUT
Пример запроса:

{
"id": 3,
"title": "Обновлённая новость",
"text": "Обновлённые новости",
"category": "Нейросети"
}
Пример ответа:

{
"id": 3,
"title": "Обновлённая новость",
"text": "Обновлённые новости",
"date": "2023-10-22T14:55:00Z",
"category": "Нейросети"
}
Получение новостей по идентификатору категории:

URL: /api/news/category/{id}
HTTP-метод: GET
Пример ответа:

[
{
"id": 1,
"title": "Заголовок новости 1",
"text": "Текст новости 1",
"date": "2023-10-20T12:00:00Z",
"category": "Web-разработка"
},
{
"id": 4,
"title": "Заголовок новости 2",
"text": "Текст новости 2",
"date": "2023-10-21T10:30:00Z",
"category": "Web-разработка"
}
]
Миграции с использованием Liquibase
Файлы миграции для создания таблиц news и category находятся в директории 
src/main/resources/db/changelog. 
Они используют SQL синтаксис для определения схемы базы данных.