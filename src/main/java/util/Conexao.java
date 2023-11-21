package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/db_ensinae_model",
              "root",
              "2xHmfcC2$@aMXd#m"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
