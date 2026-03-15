package com.guildaSys.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("bankPU");

    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if (em == null || em.isOpen()) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        if (em != null ||  em.isOpen()) {
            em.close();
        }
        threadLocal.remove();
    }

}