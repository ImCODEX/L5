package Repo;

import CustomExceptions.CustomExceptions;

import java.util.List;

/**
 * Absolute Base Repository
 * @param <T>
 */
public interface ICrudRepository<T> {

    T add(T obj);

    List<T> getAll();

    T find(Integer id) throws CustomExceptions;

    T update(T oldObject, T newObject);

    void delete(T obj);
}
