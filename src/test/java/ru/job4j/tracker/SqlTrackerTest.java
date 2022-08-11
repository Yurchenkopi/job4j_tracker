package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenSaveSeveralItemsAndFindByGenIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        Item second = new Item("Second");
        tracker.add(first);
        tracker.add(second);
        Item result = tracker.findById(first.getId());
        assertThat(result.getName(), is(first.getName()));
        result = tracker.findById(second.getId());
        assertThat(result.getName(), is(second.getName()));
    }

    @Test
    public void whenEditItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("New item");
        tracker.add(item);
        tracker.replace(item.getId(), new Item("Edited new item"));
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is("Edited new item"));
    }

    @Test
    public void whenDeleteItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("New item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenFindByNameAndCheckArrayLength() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("First");
        tracker.add(item);
        tracker.add(item);
        tracker.add(item);
        tracker.add(item);
        List<Item> result = tracker.findByName(item.getName());
        assertThat(result.size(), is(4));
    }

    @Test
    public void whenFindByNameAndCheckFirstItemName() {
        SqlTracker tracker = new SqlTracker(connection);
        Item first = new Item("First");
        tracker.add(first);
        tracker.add(first);
        tracker.add(first);
        tracker.add(first);
        Item second = new Item("Second");
        tracker.add(second);
        tracker.add(second);
        List<Item> result = tracker.findByName(first.getName());
        assertThat(result.get(2).getName(), is(first.getName()));
    }
}