package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vo.Discipline;
import vo.User;
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

        ps.setString(1, user.name());
        ps.setString(2, user.surname());
        ps.setString(3, user.email());
        ps.setString(4, user.cpf());
        ps.setString(5, user.password());
        ps.setString(6, user.access_level());
        ps.setBoolean(7, user.status());

        ps.execute();
        ps.close();
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
        return user;
    }
    public static User searchUser(Integer id) throws SQLException{
        String sql = "SELECT * FROM user WHERE id LIKE ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);

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
    }





    public static ObservableList<Discipline> listDisciplines(User user) throws SQLException {
        Connection conn = Conexao.getConnection();
        ObservableList<Discipline> list = FXCollections.observableArrayList();
        String sql = "SELECT u.* FROM user u " +
                "JOIN registration r ON u.id_user = r.id_user " +
                "JOIN discipline d ON d.id_discipline = r.id_discipline " +
                "WHERE r.status = TRUE";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Discipline discipline = new Discipline(rs.getInt("id_discipline"),
                    searchUser(rs.getInt("id_professor")),
                    rs.getString("name"),
                    rs.getString("description"));

            list.add(discipline);
            }
        return list;
    }
    public static void createDiscipline(Discipline discipline) throws SQLException {
        String sql = "INSERT INTO discipline " +
                "(name, id_professor, description) " +
                "VALUES (?, ?, ?)";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, String.valueOf(discipline.name()));
        ps.setInt(2, discipline.professor().id_user());
        ps.setString(3, discipline.description());

        ps.execute();
        ps.close();
    }
    public static List<User> listUsersInDiscipline(Discipline discipline) throws SQLException {
        String sql = "";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);


        return null;
    }
    public static void removeUserFromDiscipline(Integer id){

    }
    public static void addUserToDiscipline(Integer id){

    }
}
