package com.guildaSys.repository.impl;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.repository.interfaces.GenericRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class GenericRepositoryImpl<T, ID> implements GenericRepository<T, ID> {

    private final Class<T> entityClass;

    protected GenericRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;  // receives the class of the entity
    }
    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = JPAUtil.getEntityManager();
        return Optional.ofNullable(em.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        return em.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass
        ).getResultList();

    }

    @Override
    public Optional<T> save(T obj) {
        EntityManager em = JPAUtil.getEntityManager();
        return Optional.ofNullable(em.merge(obj));
    }

    @Override
    public boolean deleteById(ID id) {
        EntityManager em = JPAUtil.getEntityManager();
        T entity = em.find(entityClass, id);
        if (entity == null) {
            return false;
        }
        em.remove(entity);
        return true;
    }
}
