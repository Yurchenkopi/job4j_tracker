package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public void init() {
        ClassLoader loader = SqlTracker.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            createTable("items");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void query(String str) {
        try (var statement = cn.createStatement()) {
            statement.execute(str);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        query(String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s, %s);",
                tableName,
                "id SERIAL PRIMARY KEY",
                "name text",
                "created timestamp"
        ));
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (var ps = cn.prepareStatement("INSERT INTO items(name, created) VALUES (?, ?);")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (var ps = cn.prepareStatement("UPDATE items SET name = ? WHERE id = ? ;")) {
            ps.setString(1, item.getName());
            ps.setInt(2, id);
            rsl = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (var ps = cn.prepareStatement("DELETE FROM items WHERE id = ? ;")) {
            ps.setInt(1, id);
            rsl = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> data = new ArrayList<>();
        try (var ps = cn.prepareStatement("SELECT * FROM items;")) {
            try (var rslSet = ps.executeQuery()) {
                while (rslSet.next()) {
                    data.add(new Item(
                            rslSet.getInt("id"),
                            rslSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> data = new ArrayList<>();
        try (var ps = cn.prepareStatement("SELECT * FROM items WHERE name = ? ;")) {
            ps.setString(1, key);
            try (var rslSet = ps.executeQuery()) {
                while (rslSet.next()) {
                    data.add(new Item(
                            rslSet.getInt("id"),
                            rslSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Item findById(int id) {
        Item rsl = null;
        try (var ps = cn.prepareStatement("SELECT * FROM items WHERE id = ? ;")) {
            ps.setInt(1, id);
            try (var rslSet = ps.executeQuery()) {
                while (rslSet.next()) {
                    rsl = new Item(
                            rslSet.getInt("id"),
                            rslSet.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}