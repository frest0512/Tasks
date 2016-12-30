package com.epam.task11.services;

import com.epam.task11.dao.api.ManufacturerDAO;
import com.epam.task11.db.TransactionManager;
import com.epam.task11.entity.db.Manufacturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ManufacturerService {
    private static final Logger LOG = LogManager.getLogger(UserService.class);
    private ManufacturerDAO manufacturerDAO;
    private TransactionManager transactionManager;

    public ManufacturerService(ManufacturerDAO manufacturerDAO, TransactionManager transactionManager) {
        this.manufacturerDAO = manufacturerDAO;
        this.transactionManager = transactionManager;

    }

    public List<Manufacturer> getAllManufacturers() {
        return transactionManager.execute(connection -> manufacturerDAO.get());
    }
}
