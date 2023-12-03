package dao;

import javafx.collections.ObservableList;
import models.DisciplineTable;
import vo.Discipline;
import vo.User;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public static void createDiscipline(Discipline discipline) throws SQLException {
        String sql = "INSERT INTO discipline " +
                "(id_professor, name, description, access_code) " +
                "VALUES (?, ?, ?, ?)";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, discipline.professor().id_user());
        ps.setString(2, discipline.name());
        ps.setString(3, discipline.description());
        ps.setString(4, discipline.access_code());

        ps.execute();
        ps.close();
    }
    public static List<DisciplineTable> listDisciplinesUserIsIn(Integer id_user, String pattern) throws SQLException {
        List<DisciplineTable> list = new ArrayList<>();

        String sql = "SELECT d.* FROM discipline d " +
                "JOIN registration r ON d.id_discipline = r.id_discipline " +
                "JOIN user u ON u.id_user = r.id_user " +
                "WHERE r.status = TRUE" +
                " AND u.id_user = ?" +
                " AND d.name LIKE ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id_user);
        ps.setString(2, pattern);

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            User professor = searchUser(rs.getInt("id_professor"));
            DisciplineTable discipline =
                    new DisciplineTable(rs.getInt("id_discipline"),
                    rs.getString("name"),
                            professor.name(),
                            professor.email(),
                            rs.getString("description"),
                            rs.getString("access_code"));
            list.add(discipline);
        }
        return list;
    }
    public static void removeUserRegistration(Integer id_user, Integer id_discipline) throws SQLException {
        String sql = "DELETE FROM registration r " +
                "WHERE r.id_user = ? AND r.id_discipline = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id_user);
        ps.setInt(2, id_discipline);

        ps.execute();
    }
    public static void addRegistration(Integer id_user, String access_code) throws SQLException {
        String sqlSelect = "SELECT id_discipline FROM discipline" +
                " WHERE access_code LIKE ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSelect);
        ps.setString(1, access_code);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int id_discipline = rs.getInt("id_discipline");
            String sqlInsert = "INSERT INTO registration(id_user, id_discipline, status)" +
                    " VALUES(?, ?, FALSE)";

            ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, id_user);
            ps.setInt(2, id_discipline);

            ps.execute();
        }
    }




    public static ObservableList<User> listUsersInDiscipline(Discipline discipline) throws SQLException {
        String sql = "SELECT u.* FROM user u " +
                "JOIN registration r ON u.id_user = r.id_user " +
                "JOIN discipline d ON d.id_discipline = r.id_discipline " +
                "WHERE r.status = TRUE AND d.access_code= ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, discipline.access_code());

        return null;
    }
    public static Discipline searchDiscipline(String accessCode) throws SQLException {
        return null;
    }

    public static void removeUserFromDiscipline(Integer id){

    }
}
