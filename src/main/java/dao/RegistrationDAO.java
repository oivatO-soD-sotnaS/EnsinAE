package dao;

import dto.InactiveUserDTO;
import models.User;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {

    public static void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user " +
                "(name, surname, email, cpf, password, access_level, status)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.name());
        ps.setString(2, user.surname());
        ps.setString(3, user.email());
        ps.setString(4, user.cpf());
        ps.setString(5, user.password());
        ps.setString(6, user.access_level());
        ps.setBoolean(7, user.status());

        ps.execute();
        ps.close();
        conn.close();
    }
    public static User searchUser(String email) throws SQLException{
        String sql = "SELECT * FROM user WHERE email LIKE ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = null;

        if(rs.next()){
            user = new User(rs.getInt("id_user"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("password"),
                    rs.getString("access_level"),
                    rs.getBoolean("status")
            );
        }
        ps.close();
        conn.close();
        return user;
    }
    public static User searchUser(Integer id_user) throws SQLException{
        String sql = "SELECT * FROM user WHERE id_user LIKE ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_user);

        ResultSet rs = ps.executeQuery();
        User user = null;

        if(rs.next()){
            user = new User(rs.getInt("id_user"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("cpf"),
                    rs.getString("password"),
                    rs.getString("access_level"),
                    rs.getBoolean("status")
            );
        }

        ps.close();
        conn.close();
        return user;
    }
    public static void updateUser(User user) throws SQLException {
        String sql = "UPDATE user " +
                "SET name = ?, " +
                "surname = ?, " +
                "email = ?, " +
                "cpf = ?, " +
                "password = ?" +
                "WHERE id_user = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.name());
        ps.setString(2, user.surname());
        ps.setString(3, user.email());
        ps.setString(4, user.cpf());
        ps.setString(5, user.password());
        ps.setInt(6, user.id_user());

        ps.execute();
        ps.close();
        conn.close();
    }
    public static void setUserActive(String email) throws SQLException {
        String sql = "UPDATE user " +
                "SET status = TRUE " +
                "WHERE email LIKE ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);

        ps.execute();
        ps.close();
        conn.close();
    }
    public static List<InactiveUserDTO> getInactiveUsers() throws SQLException {
        String sql = "SELECT * FROM user" +
                " WHERE status = FALSE";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<InactiveUserDTO> users = new ArrayList<>();
        while(rs.next()){
            users.add(new InactiveUserDTO(rs.getInt("id_user"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("cpf")));
        }
        return users;
    }


}
