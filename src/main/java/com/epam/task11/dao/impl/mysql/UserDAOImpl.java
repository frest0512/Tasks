package com.epam.task11.dao.impl.mysql;

import com.epam.task11.dao.api.UserDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Role;
import com.epam.task11.entity.db.User;
import com.epam.task11.exeptions.DAOException;
import com.epam.task11.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Date;

import static com.epam.task11.dao.utils.Mapper.mapp;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOG = LogManager.getLogger(UserService.class);

    @Override
    public User get(String login, String password) {
        Connection connection = ConnectionThreadLocal.getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users u " +
                "inner join roles r " +
                "on u.roleid = r.idrole " +
                "WHERE username = ? and password = ?")) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = unmapReflect(resultSet);
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        }
        return user;
    }

    @Override
    public boolean exist(String login) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            LOG.warn("exist Exception + " + e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean add(User user) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (\n" +
                "`username`,\n" +
                "`first_name`,\n" +
                "`last_name`,\n" +
                "`email`,\n" +
                "`password`,\n" +
                "`avatar_name`,\n" +
                "`is_to_subscribe`,\n" +
                "`roleid`) VALUES(?,?,?,?,?,?,?,(select `idrole` from roles where name = 'user'))")) {
            map(preparedStatement, user);
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            LOG.warn("add Exception + " + e);
            throw new DAOException(e);
        }

    }

    @Override
    public boolean resetNumberOfFailAttempts(User user) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET fail_attempts=0 WHERE username = ?")) {
            preparedStatement.setString(1, user.getUserName());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            LOG.warn("add Exception + " + e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean incrementNumberOfFailAttempts(User user) {
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET fail_attempts=fail_attempts+1 WHERE username = ?")) {
            preparedStatement.setString(1, user.getUserName());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            LOG.warn("add Exception + " + e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean setBanDate(User user, Date time) {
        Timestamp timestamp = null;
        String query = "UPDATE users SET date_ban=? WHERE username = ?";
        if (time != null) {
            timestamp = new Timestamp(time.getTime());
            query = "UPDATE users SET date_ban=? WHERE username = ? and fail_attempts >=5";
        }
        Connection connection = ConnectionThreadLocal.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1,timestamp);
            preparedStatement.setString(2, user.getUserName());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            LOG.warn("add Exception + " + e);
            throw new DAOException(e);
        }
    }

    private void map(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getLastName());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setString(6, user.getAvatarName());
        preparedStatement.setBoolean(7, user.isSubscribe());
    }

    private User unmapReflect(ResultSet resultSet) throws SQLException {
        User user = new User();
        Class<User> userClass = User.class;
        Class<Role> roleClass = Role.class;
        Field[] userFields = userClass.getDeclaredFields();
        mapp(userFields, user, resultSet);
        Role role = new Role();
        Field[] roleFields = roleClass.getDeclaredFields();
        mapp(roleFields, role, resultSet);
        user.setRole(role);
        return user;
    }
}
