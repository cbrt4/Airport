package repository;

import java.util.List;

public interface RepositoryInterface<T> {

    T get(int id);

    void add(T entity);

    void remove(int id);

    void update(int id);

    List<T> getAll();

}