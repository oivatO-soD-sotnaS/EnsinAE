package dao;

import DTO.User;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    public static void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user " +
                "(name, surname, email, cpf, password, access_level, status)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getCpf());
        ps.setString(5, user.getPassword());
        ps.setString(6, user.getAccess_level());
        ps.setBoolean(7, user.getStatus());
        ps.execute();
    }
    public static User searchUser(String email) throws SQLException{
        String sql = "SELECT * FROM user WHERE email LIKE ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        User user = null;

        if(rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setCpf(rs.getString("cpf"));
            user.setPassword(rs.getString("password"));
            user.setAccess_level(rs.getString("access_level"));
            user.setStatus(rs.getBoolean("status"));
        }
        return user;
    }
    public static List<User> listUserInSubject(){

        return null;
    }
    public static void removeUserFromSubject(Integer id){

    }
    public static void addUserToSubject(Integer id){

    }
}
