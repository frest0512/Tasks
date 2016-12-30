package com.epam.task11.dao.api;

import com.epam.task11.entity.db.Manufacturer;

import java.util.List;

public interface ManufacturerDAO {
    List<Manufacturer> get();
}
