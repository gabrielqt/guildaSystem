package com.guildaSys.service;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Guild;
import com.guildaSys.entity.Hero;
import com.guildaSys.entity.Item;
import com.guildaSys.enums.Operator;
import com.guildaSys.exceptions.MaximunItensInInvetoryException;
import com.guildaSys.repository.impl.HeroRepositoryImpl;
import com.guildaSys.repository.interfaces.GenericRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class HeroService extends GenericService<Hero, Long>{

    private final HeroRepositoryImpl heroRepository = new HeroRepositoryImpl();

    @Override
    protected GenericRepository<Hero, Long> getRepository() {
        return heroRepository;
    }

    public Optional<Hero> findByNickname(String nickname){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return heroRepository.findByNickname(nickname);
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public void joinGuild(Guild guild, Hero hero) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            guild.getGuildHeroes().add(hero);
            hero.setGuild(guild);
            tx.commit();
            System.out.println(hero.getNickname() + " entrou na Guild: " + guild.getGuildName());
        } catch (RuntimeException e) {
            if(tx.isActive())tx.rollback();
            throw e;
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }

    public void exitGuild(Guild guild, Hero hero) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            guild.getGuildHeroes().remove(hero);
            hero.setGuild(null);
            tx.commit();
            System.out.println(hero.getNickname() + " entrou na Guild: " + guild.getGuildName());
        } catch (RuntimeException e) {
            if(tx.isActive())tx.rollback();
            throw e;
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }


// FIX THIS: USE HEROID AND ITEMID (ELSE: LAZY EXCEPTION)
    public void addItemInHeroInventory(Item item, Hero hero) {
        if (hero.getInventory().size() > 5) {
            throw new MaximunItensInInvetoryException();
        }
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            hero.getInventory().add(item);
            heroRepository.save(hero);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive())tx.rollback();
            throw e;
        } finally{
            JPAUtil.closeEntityManager();
        }
    }


}
