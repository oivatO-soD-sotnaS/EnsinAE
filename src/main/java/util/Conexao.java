package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection(
              "jdbc:mysql://db-ensinae.chx1voeacn1x.sa-east-1.rds.amazonaws.com:1433/db_ensinae",
              "admin",
              "j2JJKCDPUV768QHzM7vRIEckyofRJv"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
