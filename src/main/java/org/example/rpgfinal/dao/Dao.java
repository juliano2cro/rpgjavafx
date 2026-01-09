package org.example.rpgfinal.dao;

import java.util.List;

public interface Dao<T> {

    void save(T entity);

    List<T> findAll();

    void delete(T entity);
}
