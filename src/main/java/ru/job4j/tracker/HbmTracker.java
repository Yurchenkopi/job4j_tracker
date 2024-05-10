package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class HbmTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(HbmTracker.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        Optional<Item> rsl = Optional.empty();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            rsl = Optional.of(item);
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.warn("Объект item не был сохранен в БД по причине возникновения исключения.");
        } finally {
            session.close();
        }
        return rsl.orElse(null);
    }

    @Override
    public boolean replace(Integer id, Item item) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            var affectedRows = session.createQuery(
                            "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", item.getCreated())
                    .setParameter("fId", item.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = affectedRows > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.warn("Объект item не был обновлен в БД по причине возникновения исключения.");
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sf.openSession();
        boolean rsl = false;
        try {
            session.beginTransaction();
            var affectedRows = session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            rsl = affectedRows > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.warn("Объект item не был удален из БД по причине возникновения исключения.");
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> rsl = Collections.emptyList();
        try {
            session.beginTransaction();
            var query = session.createQuery("from Item", Item.class);
            session.getTransaction().commit();
            rsl = query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.warn("Возникло исключение при поиске всех записей в БД.");
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> rsl = Collections.emptyList();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "FROM Item i WHERE i.name = :fKey", Item.class);
            query.setParameter("fKey", key);
            session.getTransaction().commit();
            rsl = query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.warn("Возникло исключение при поиске записи в БД по названию.");
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public Item findById(Integer id) {
        Session session = sf.openSession();
        Optional<Item> rsl = Optional.empty();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery(
                    "from Item i where i.id = :fId", Item.class);
            query.setParameter("fId", id);
            session.getTransaction().commit();
            rsl = query.uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.warn("Возникло исключение при поиске записи в БД по id.");
        } finally {
            session.close();
        }
        return rsl.orElse(null);
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}