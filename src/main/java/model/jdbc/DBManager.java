package model.jdbc;

import config.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private Connection connection;

    private DBManager(){

    }
    Connection getConnection(){
        String jdbcUrl = "jdbc:sqlite:" + ConfigManager.getInstance().getProperties("db.url");
        System.out.println(jdbcUrl);
        if(connection == null){
            try {
                connection = DriverManager.getConnection(jdbcUrl);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    void startTransaction(){
        try{
            getConnection().setAutoCommit(false);
        } catch (SQLException ex) {

        }
    }

    void startTransaction(int isolationLevel) throws IllegalArgumentException {
        try {
            getConnection().setAutoCommit(false);

            int isol = 0;
            switch (isolationLevel) {
                case 0:
                    isol = java.sql.Connection.TRANSACTION_READ_UNCOMMITTED;
                    break;
                case 1:
                    isol = java.sql.Connection.TRANSACTION_READ_COMMITTED;
                    break;
                case 2:
                    isol = java.sql.Connection.TRANSACTION_REPEATABLE_READ;
                    break;
                case 3:
                    isol = java.sql.Connection.TRANSACTION_SERIALIZABLE;
                    break;
                default:
                    throw new IllegalAccessException("Degré d'isolation inexistant!");
            }
            getConnection().setTransactionIsolation(isol);
        } catch (SQLException ex) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    void validateTransaction()  {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {
        }
    }

    void cancelTransaction() {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException ex) {

        }
    }

    static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    private static class DBManagerHolder {

        private static final DBManager INSTANCE = new DBManager();
    }
}

