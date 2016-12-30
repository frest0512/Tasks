package com.epam.task11.services;

import com.epam.task11.dao.api.UserDAO;
import com.epam.task11.db.TransactionManager;
import com.epam.task11.entity.db.User;
import com.epam.task11.exeptions.DAOException;
import com.epam.task11.util.SecurityHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private UserDAO userDAO;
    private TransactionManager transactionManager;

    public UserService(UserDAO userDAO, TransactionManager transactionManager) {
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;

    }

    public User saveUser(User user) {
        return transactionManager.executeWithTransaction(connection -> {
            LOG.debug("Started transaction");
            boolean isExist = userDAO.exist(user.getUserName());
            LOG.debug("Check if user exist = " + isExist);
            if (isExist) {
                throw new DAOException("User exists");
            }
            user.setPassword(SecurityHelper.getHash(user.getPassword()));
            boolean isUserAdded = userDAO.add(user);
            if (!isUserAdded) {
                throw new DAOException("Can not add user");
            }
            return userDAO.get(user.getUserName(),user.getPassword());
        }, Connection.TRANSACTION_READ_UNCOMMITTED);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return transactionManager.execute(connection -> userDAO.get(login, SecurityHelper.getHash(password)));
    }

    public boolean isUserExist(String login) {
        return transactionManager.execute(connection -> userDAO.exist(login));
    }
    public boolean resetNumberOfFailAttempts(User user){
        return transactionManager.execute(connection -> userDAO.resetNumberOfFailAttempts(user));
    }

    public boolean incrementAndBan(User user){
        return transactionManager.execute(connection -> {
            boolean res = userDAO.incrementNumberOfFailAttempts(user);
            if(!res){
                throw new DAOException("Can not increment fail attempts");
            }
            return userDAO.setBanDate(user,new Date(System.currentTimeMillis()));
        });
    }

    public boolean blockUser(User user, Date time) {
        return transactionManager.execute(connection -> userDAO.setBanDate(user,time));
    }
    public boolean unblockUser(User user) {
        return transactionManager.executeWithTransaction(connection -> {
            boolean res = userDAO.resetNumberOfFailAttempts(user);
            if(!res){
                throw new DAOException("Cant reset number of fail attempts");
            }
            res = userDAO.setBanDate(user,null);
            if(!res){
                throw new DAOException("Cant reset ban date");
            }
            return true;
        },Connection.TRANSACTION_READ_UNCOMMITTED);
    }
}
