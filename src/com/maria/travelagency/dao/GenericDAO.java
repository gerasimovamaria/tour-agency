package com.maria.travelagency.dao;


import com.maria.travelagency.dao.exception.DAOException;
import com.maria.travelagency.entity.Entity;

public interface GenericDAO<K, T extends Entity> {
    

    T findEntityById(K id) throws DAOException;


    boolean delete(K id) throws DAOException;


    boolean create(T entity) throws DAOException;

    boolean update(T entity) throws DAOException;
}
