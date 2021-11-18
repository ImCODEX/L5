package Repo;

import java.util.List;

public interface ICrudRepository<T> {

    T add(T obj);

    List<T> getAll();

    T update(T oldObject, T newObject);

    void delete(T obj);
}
