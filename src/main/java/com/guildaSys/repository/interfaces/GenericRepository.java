package com.guildaSys.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    Optional<T> save(T item);
    boolean deleteById(ID id);
}
