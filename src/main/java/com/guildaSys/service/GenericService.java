package com.guildaSys.service;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.repository.interfaces.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public abstract class GenericService <T, ID>
{
    protected abstract GenericRepository<T, ID> getRepository();

    public Optional<T> findById(ID id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return getRepository().findById(id);
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public List<T> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return getRepository().findAll();
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public Optional<T> save(T item) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        Optional<T> obj;
        try{
            tx.begin();
            obj = getRepository().save(item);
            tx.commit();
        } catch(RuntimeException e) {
            if(tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        finally {
            JPAUtil.closeEntityManager();
        }
        return obj;

    }

    public boolean deleteById(ID id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        boolean isDeleted;
        try{
            tx.begin();
            isDeleted = getRepository().deleteById(id);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
        return isDeleted;
    }

}


