package dev.naimsulejmani.grupi1javafxregistration.infrastructure;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//SINGLETON Design Pattern - Creational Pattern
public class DbConnection {
    private static Connection connection;

    @Getter
    @Setter
    private static String connectionString = "jdbc:sqlserver://127.0.0.1:1433;encrypt=false;databaseName=Shitjet;username=sa;password=C@cttusEducation";

    private DbConnection() {
        //parandalo krijimin e objekteve nga jasht
    }

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(connectionString);
        }
        return connection;
    }


}
