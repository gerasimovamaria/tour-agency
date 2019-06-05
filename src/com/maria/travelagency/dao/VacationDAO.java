package com.maria.travelagency.dao;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.Vacation;

import java.sql.Date;
import java.util.List;

public interface VacationDAO extends GenericDAO<Long, Vacation> {


    List<Vacation> findAllVacationsAfterNow(Date nowDate) throws DAOException;

    List<Vacation> findAllSortVacationsAfterNow(Date nowDate, String criterion, boolean order) throws DAOException;

    List<Vacation> findVacationsByNameAfterNow(Date nowDate, String name) throws DAOException;

    List<Vacation> findVacationsByDepartureDateAfterNow(Date nowDate, Date departureDate) throws DAOException;

    List<Vacation> findVacationsByArrivalDateAfterNow(Date nowDate, Date arrivalDate) throws DAOException;

    List<Vacation> findVacationsByPriceAfterNow(Date nowDate, double price) throws DAOException;

    List<Vacation> findVacationsByTransportAfterNow(Date nowDate, String transport) throws DAOException;

    Long findLastVacationId() throws DAOException;

    String findPathImageVacationById(Long id) throws DAOException;

    List<Vacation> selectLastVacations(Date nowDate) throws DAOException;

}
