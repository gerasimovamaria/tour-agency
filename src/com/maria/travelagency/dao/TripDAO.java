package com.maria.travelagency.dao;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.Trip;

import java.sql.Date;
import java.util.List;

public interface TripDAO extends GenericDAO<Long, Trip> {


    List<Trip> findAllTripsAfterNow(Date nowDate) throws DAOException;

    List<Trip> findAllSortTripsAfterNow(Date nowDate, String criterion, boolean order) throws DAOException;

    List<Trip> findTripsByNameAfterNow(Date nowDate, String name) throws DAOException;

    List<Trip> findTripsByDepartureDateAfterNow(Date nowDate, Date departureDate) throws DAOException;

    List<Trip> findTripsByArrivalDateAfterNow(Date nowDate, Date arrivalDate) throws DAOException;

    List<Trip> findTripsByPriceAfterNow(Date nowDate, double price) throws DAOException;

    List<Trip> findTripsByTransportAfterNow(Date nowDate, String transport) throws DAOException;

    Long findLastTripId() throws DAOException;

    String findPathImageTripById(Long id) throws DAOException;

    List<Trip> selectLastTrips(Date nowDate) throws DAOException;
}
