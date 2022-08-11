package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public SqlTracker() {
    }

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
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    private Item rslSetToItem(ResultSet rslSet) throws SQLException {
        return new Item(
                rslSet.getInt("id"),
                rslSet.getString("name"),
                rslSet.getTimestamp("created").toLocalDateTime()
        );
    }

    @Override
    public Item add(Item item) {
        try (var ps = cn.prepareStatement(
                "INSERT INTO items(name, created) VALUES (?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.execute();
            try (var rslSet = ps.getGeneratedKeys()) {
                if (rslSet.next()) {
                    item.setId(rslSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (var ps = cn.prepareStatement(
                "UPDATE items SET name = ? , created = current_timestamp WHERE id = ? ;"
        )) {
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
                    data.add(rslSetToItem(rslSet));
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
                    data.add(rslSetToItem(rslSet));
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
                if (rslSet.next()) {
                    rsl = rslSetToItem(rslSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}