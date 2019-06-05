package com.maria.travelagency.dao;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.Order;
import com.maria.travelagency.entity.OrderTourInfo;

import java.sql.Date;
import java.util.List;

public interface OrderDAO extends GenericDAO<Long, Order> {

    List<OrderTourInfo> findAllUserOrdersByUserId(Long userId) throws DAOException;

    List<OrderTourInfo> findAllUserOrdersByUserIdAfterNow(Long userId, Date nowDate) throws DAOException;

    List<OrderTourInfo> findAllUserOrdersByUserIdBeforeNow(Long userId, Date nowDate) throws DAOException;

    Date findDepartureDateById(Long id) throws DAOException;

    int findTotalPriceById(Long id) throws DAOException;

    long findUserIdByOrderId(Long id) throws DAOException;
}