package com.maria.travelagency.dao;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.City;

import java.util.List;

public interface CityDAO extends GenericDAO<Long, City> {


    List<City> findAllCities() throws DAOException;
}