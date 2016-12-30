package com.epam.task11.db;

import com.epam.task11.exeptions.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class TransactionManager {
    private DataSource dataSource;
    private static final Logger LOG = LogManager.getLogger(TransactionManager.class);
    private static final String DATA_SOURCE_CONTEXT = "java:comp/env/jdbc/mydb";

    public TransactionManager() throws NamingException {
        Context initialContext = new InitialContext();
        dataSource = (DataSource) initialContext.lookup(DATA_SOURCE_CONTEXT);
    }

    public <T> T executeWithTransaction(TransactionOperation<T> op, int isoLevel) {
        LOG.debug("Executing Transaction started");
        T result = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(isoLevel);
            ConnectionThreadLocal.setConnection(connection);
            LOG.debug("Executing transaction");
            result = op.doInTrans(connection);
            connection.commit();

        } catch (SQLException e) {
            LOG.warn("Exception appeared in transaction, trying to rollback");
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                LOG.warn("Rollback problem");
                throw new DAOException(e1);
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e1) {
                LOG.warn("Close connection problem");
                throw new DAOException(e1);
            }
            ConnectionThreadLocal.setConnection(null);
        }
        LOG.debug("Transaction finished");
        return result;
    }

    public <T> T execute(TransactionOperation<T> op) {
        LOG.debug("Executing nonTransaction started");
        T result = null;
        try (Connection connection = dataSource.getConnection()) {
            ConnectionThreadLocal.setConnection(connection);
            result = op.doInTrans(connection);
        } catch (SQLException e) {
            LOG.warn("Exception appeared in transaction, trying to rollback");
            throw new DAOException(e);
        } finally {
            ConnectionThreadLocal.setConnection(null);
        }
        LOG.debug("Executing nonTransaction finished");
        return result;
    }
}
