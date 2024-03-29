package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistResumeException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sqlExpression) {
        execute(sqlExpression, PreparedStatement::execute);
    }

    public <T> T execute(String sqlExpression, SqlExecutor<T> sqlExecutor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlExpression)) {
            return sqlExecutor.execute(ps);
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> sqlExecutor) {
        try (Connection conn = connectionFactory.getConnection()){
            try {
                conn.setAutoCommit(false);
                T res = sqlExecutor.execute(conn);
                conn.commit();
                return res;
            }catch (SQLException e){
                conn.rollback();
                throw ExceptionUtil.convertException(e);
            }
        } catch (SQLException e) {
            throw  new StorageException(e);
        }
    }
}
