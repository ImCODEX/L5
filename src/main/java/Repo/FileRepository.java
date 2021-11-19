package Repo;



import CustomExceptions.CustomExceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic Class that implements ICrudRepository
 * @param <T>: T can be any object
 */

public abstract class FileRepository<T> implements ICrudRepository<T> {


    protected List<T> repoList;

    public FileRepository() {
        this.repoList = new ArrayList<>();

    }

    @Override
    public T add(T obj) {
        this.repoList.add(obj);
        return obj;
    }

    @Override
    public T update(T oldObject, T newObject){
        this.repoList.remove(oldObject);
        this.repoList.add(newObject);
        return newObject;
    }

    @Override
    public List<T> getAll() {
        return this.repoList;
    }

    @Override
    public abstract T find(Integer id) throws CustomExceptions;

    @Override
    public void delete(T obj) {
        this.repoList.remove(obj);
    }

}
