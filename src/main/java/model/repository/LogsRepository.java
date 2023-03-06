package model.repository;

import model.dto.LogsDto;

import model.jdbc.LogsDao;

import java.sql.SQLException;
import java.util.List;

public class LogsRepository implements Repository<Integer, LogsDto> {

    LogsDao logsDao;

    public LogsRepository(LogsDao logsDao) {
        this.logsDao = logsDao;
    }

    @Override
    public Integer add(LogsDto item) throws SQLException {
        return logsDao.insert(item);
    }

    @Override
    public void remove(Integer key) {

    }

    @Override
    public LogsDto get(Integer key) {
        return null;
    }

    @Override
    public List<LogsDto> getAll() {
        return logsDao.selectAll();
    }

    @Override
    public boolean contains(Integer key) {
        return false;
    }
}
