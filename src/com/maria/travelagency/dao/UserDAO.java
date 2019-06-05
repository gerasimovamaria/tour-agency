package com.maria.travelagency.dao;

import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.User;
import com.maria.travelagency.entity.UserOrderNumber;

import java.util.List;

public interface UserDAO extends GenericDAO<Long, User> {


    List<User> findAllUsers() throws DAOException;

    List<UserOrderNumber> findAllUsersWithOrderCount() throws DAOException;

    User findUserByName(String name) throws DAOException;

    double findMoneyByUserId(Long id) throws DAOException;

    String findPasswordByUserId(Long id) throws DAOException;

    boolean updatePasswordByUserId(Long id, String password) throws DAOException;

    boolean updateUserBalance(Long id, double money) throws DAOException;

    boolean updateUserBalanceAddition(Long id, double moneyToAdd) throws DAOException;

}
