package com.guildaSys;

import com.bank.config.JPAUtil;
import jakarta.persistence.EntityManager;

public class App {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        em.close();

        System.out.println("JPA inicializado");
    }
}