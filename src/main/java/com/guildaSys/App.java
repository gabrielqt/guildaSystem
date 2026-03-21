package com.guildaSys;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Guild;
import com.guildaSys.entity.Hero;
import com.guildaSys.repository.impl.GuildRepositoryImpl;
import com.guildaSys.repository.interfaces.GuildRepository;
import com.guildaSys.repository.interfaces.HeroRepository;
import com.guildaSys.repository.impl.HeroRepositoryImpl;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        GuildRepository repo = new GuildRepositoryImpl();

        Optional<List<Guild>> hero = repo.findAll();

        hero.ifPresent(guilds -> {
            for (Guild guild : guilds) {
                for (Hero h : guild.getGuildHeroes()){
                    System.out.println(h.getNickname());
                }
            }
        });





        System.out.println("JPA inicializado");
    }
}
