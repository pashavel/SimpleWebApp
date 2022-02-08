package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
@Component
public class DbConnection {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    private  final Logger LOGGER =
            Logger.getLogger(DbConnection.class.getName());
    private  Optional<Connection> connection = Optional.empty();

    public  Optional<Connection> getConnection() {

        if (connection.isEmpty()) {
            try {
                connection = Optional.ofNullable(
                        DriverManager.getConnection(url, user, password));
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
