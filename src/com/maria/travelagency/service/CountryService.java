package com.maria.travelagency.service;

import com.maria.travelagency.entity.Country;
import com.maria.travelagency.service.exception.ServiceException;

import java.util.List;

public interface CountryService {

    List<Country> findAllCountries() throws ServiceException;

    boolean checkCreateCountry(String enterName) throws ServiceException;

    boolean checkEditCountry(String enterId, String enterName) throws ServiceException;

    boolean delete(Long id) throws ServiceException;

    Country findEntityById(Long id) throws ServiceException;
}
