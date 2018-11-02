package io.nambm.sachviet.repository.generic;

import java.util.List;

public interface GenericRepository<T> {

    void insert(T entity);

    void insertBatch(List<T> entities);

    void updateBatch(List<T> entities);

    void insertOrReplace(T entity);

    void update(T entity, String... properties);

    List<T> searchAll();

    //todo: search by value with column names

    List<T> searchExactColumn(List<String> values, String columnName);

    T findById(T entity);

    T delete(T entity);
}
