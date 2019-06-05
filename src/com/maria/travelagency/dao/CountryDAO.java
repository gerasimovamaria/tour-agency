package com.maria.travelagency.dao;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.Country;

import java.util.List;

public interface CountryDAO extends GenericDAO<Long, Country> {


    List<Country> findAllCountries() throws DAOException;
}