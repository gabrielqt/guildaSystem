package com.guildaSys;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Hero;
import com.guildaSys.repository.interfaces.HeroRepository;
import com.guildaSys.repository.impl.HeroRepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class App {

    public static void main(String[] args) {

        HeroRepository repo = new HeroRepositoryImpl();

        Optional<Hero> hero = repo.findByIdHeroWithGuildAndInventory(1L);

        hero.ifPresent(System.out::println);





        System.out.println("JPA inicializado");
    }
}