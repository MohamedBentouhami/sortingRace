package model.jdbc;

import model.dto.LogsDto;
import model.repository.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogsDao implements Dao<Integer, LogsDto> {

    private Connection connection;

    private LogsDao() {
        connection = DBManager.getInstance().getConnection();
    }

    public static LogsDao getInstance() {

        return StationDaoHolder.getInstance();
    }

    @Override
    public Integer insert(LogsDto item) throws SQLException {
        if (item == null) {
            throw new IllegalArgumentException("Aucune élément donné en paramètre");
        }
        Integer id = 0;
        String sql = "INSERT INTO STUDENTS(lastname,firstname) values(?, ? )";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getTypeSort());
            pstmt.setInt(2, item.getMaxSize());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return id;
    }

    @Override
    public void delete(Integer key) {

    }

    @Override
    public void update(LogsDto item) {

    }

    @Override
    public LogsDto select(Integer key) {

        LogsDto dto = null;
        String sql = "select id,timestamp,sort_type,max_size from simulation where id = " + key;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            while (rs.next()) {
                dto = new LogsDto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4));
                count++;
            }
            if (count > 1) {
                throw new IllegalArgumentException("Record pas unique ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public List<LogsDto> selectAll() {
        List<LogsDto> logsDtoList = new ArrayList<>();
        String sql = "select id,timestamp,sort_type,max_size from simulation";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                logsDtoList.add(new LogsDto(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return logsDtoList;
    }


    private static class StationDaoHolder {

        private static LogsDao getInstance() {
            return new LogsDao();
        }

    }
}
