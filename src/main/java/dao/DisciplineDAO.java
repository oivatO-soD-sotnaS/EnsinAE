package dao;

import dto.DisciplineStudantDTO;
import dto.UserDisciplinesDTO;
import models.Discipline;
import models.User;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {
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
        conn.close();
        addRegistration(discipline.professor().id_user(), discipline.access_code(), true);
    }
    public static List<UserDisciplinesDTO> getUserDisciplines(Integer id_user, String pattern) throws SQLException {
        List<UserDisciplinesDTO> list = new ArrayList<>();

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
            User professor = RegistrationDAO.searchUser(rs.getInt("id_professor"));
            UserDisciplinesDTO discipline =
                    new UserDisciplinesDTO(rs.getInt("id_discipline"),
                            rs.getString("name"),
                            professor.name(),
                            professor.email(),
                            rs.getString("description"),
                            rs.getString("access_code"));
            list.add(discipline);
        }
        ps.close();
        conn.close();
        return list;
    }
    public static void removeUserRegistration(Integer id_user, Integer id_discipline) throws SQLException {
        String sql = "DELETE FROM registration r " +
                "WHERE r.id_user = ?" +
                " AND r.id_discipline = ?";

        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id_user);
        ps.setInt(2, id_discipline);

        ps.execute();
        ps.close();
        conn.close();
    }
    public static boolean addRegistration(Integer id_user, String access_code, Boolean status) throws SQLException {
        String sqlSelect = "SELECT id_discipline FROM discipline" +
                " WHERE access_code LIKE ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlSelect);

        ps.setString(1, access_code);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int id_discipline = rs.getInt("id_discipline");
            String sqlInsert = "INSERT INTO registration(id_user, id_discipline, status)" +
                    " VALUES(?, ?, ?)";

            ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, id_user);
            ps.setInt(2, id_discipline);
            ps.setBoolean(3, status);
            ps.execute();
        }else{
            return false;
        }
        ps.close();
        conn.close();
        return true;
    }
    public static List<DisciplineStudantDTO> getDisciplineStudants(Boolean status, Integer id_discipline, String pattern) throws SQLException {
        String sql = "SELECT u.* FROM user u " +
                "JOIN registration r ON u.id_user = r.id_user " +
                "JOIN discipline d ON d.id_discipline = r.id_discipline " +
                "WHERE r.status = ?" +
                " AND d.id_discipline= ?" +
                " AND r.id_user != d.id_professor" +
                " AND u.name LIKE ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBoolean(1, status);
        ps.setInt(2, id_discipline);
        ps.setString(3, String.format("%s%s", pattern, "%"));

        ResultSet rs = ps.executeQuery();

        List<DisciplineStudantDTO> studants = new ArrayList<>();
        while(rs.next()){
            studants.add(new DisciplineStudantDTO(rs.getInt("id_user"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("email"),
                    rs.getString("cpf")));
        }
        ps.close();
        conn.close();
        return studants;
    }
    public static void updateRegistration(String email) throws SQLException {
        String sql = "UPDATE registration" +
                " SET status = TRUE" +
                " WHERE id_user = ?";
        Connection conn = Conexao.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, RegistrationDAO.searchUser(email).id_user());

        ps.execute();
        ps.close();
        conn.close();
    }
}
