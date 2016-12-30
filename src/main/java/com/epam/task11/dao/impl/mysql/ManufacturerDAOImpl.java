package com.epam.task11.dao.impl.mysql;

import com.epam.task11.dao.api.ManufacturerDAO;
import com.epam.task11.db.ConnectionThreadLocal;
import com.epam.task11.entity.db.Manufacturer;
import com.epam.task11.exeptions.DAOException;
import com.epam.task11.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.task11.dao.utils.Mapper.mapp;

public class ManufacturerDAOImpl implements ManufacturerDAO {
    private static final Logger LOG = LogManager.getLogger(UserService.class);

    @Override
    public List<Manufacturer> get() {
        Connection connection = ConnectionThreadLocal.getConnection();
        List<Manufacturer> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM manufacturers")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(unmapReflect(resultSet));
            }
        } catch (SQLException e) {
            LOG.warn("get Exception + " + e);
            throw new DAOException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Manufacturer unmapReflect(ResultSet resultSet) throws SQLException, IllegalAccessException {
        Manufacturer manufacturer = new Manufacturer();
        Class<Manufacturer> manufacturerClass = Manufacturer.class;
        Field[] fields = manufacturerClass.getDeclaredFields();
        mapp(fields, manufacturer, resultSet);
        return manufacturer;
    }
}
