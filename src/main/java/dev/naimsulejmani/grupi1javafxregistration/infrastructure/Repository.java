package dev.naimsulejmani.grupi1javafxregistration.infrastructure;

import java.util.List;

public interface Repository<T, TId> {
    void add(T model);//INSERT
    void modify(TId id, T model); // UPDATE
    void removeById(TId id); // DELETE

    List<T> findAll(); // SELECT
    T findById(TId id); // SELECT ..WHERE

}
