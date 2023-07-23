package org.example;
import org.example.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    public static void main(String[] args) {
        // Создаем SessionFactory с помощью hibernate.cfg.xml
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        // Создаем сессию для взаимодействия с базой данных
        Session session = sessionFactory.getCurrentSession();

        try {
            // Открываем транзакцию
            session.beginTransaction();

            // Создаем и сохраняем задачу в базе данных
            Task task = new Task();
            task.setDescription("Complete the project");
            task.setCompleted(false);

            session.save(task);

            // Закрываем транзакцию
            session.getTransaction().commit();

            // Выводим информацию о сохраненной задаче
            System.out.println("Task saved. ID: " + task.getId());
        } finally {
            // Закрываем сессию и завершаем работу с Hibernate
            session.close();
            sessionFactory.close();
        }
    }
}
