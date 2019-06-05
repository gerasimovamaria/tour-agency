package com.maria.travelagency.service.impl;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.dao.jdbc.JdbcCountryDAO;
import com.maria.travelagency.entity.Country;
import com.maria.travelagency.util.Validator;
import com.maria.travelagency.service.CountryService;
import com.maria.travelagency.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class CountryServiceImpl implements CountryService {

    private final static Logger LOG = Logger.getLogger(CountryServiceImpl.class);

    private final static int COUNTRY_ID_FOR_INSERT = 0;

    @Override
    public List<Country> findAllCountries() throws ServiceException {
        try {
            return JdbcCountryDAO.getInstance().findAllCountries();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    public boolean checkCreateCountry(String enterName) throws ServiceException {
        boolean flag = false;
        if (Validator.validateCountryAndCityName(enterName)){
            try {
                Country country = new Country();
                country.setId(COUNTRY_ID_FOR_INSERT);
                country.setNameCountry(enterName);

                if (JdbcCountryDAO.getInstance().create(country)){
                    flag = true;
                }
            } catch (DAOException e) {
                throw new ServiceException("Failed to create country.", e);
            }
        }
        return flag;
    }


    public boolean checkEditCountry(String enterId, String enterName) throws ServiceException {
        boolean flag = false;
        if (Validator.validateCountryAndCityName(enterName)){
            try {
                Country country = new Country();
                country.setId(Long.parseLong(enterId));
                country.setNameCountry(enterName);

                if (JdbcCountryDAO.getInstance().update(country)){
                    flag = true;
                }
            } catch (DAOException e) {
                throw new ServiceException("Failed to create country.", e);
            }
        }
        return flag;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return JdbcCountryDAO.getInstance().delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Country findEntityById(Long id) throws ServiceException {
        try {
            return JdbcCountryDAO.getInstance().findEntityById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
