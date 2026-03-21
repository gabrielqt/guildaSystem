package com.guildaSys.repository.impl;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Guild;
import com.guildaSys.entity.Hero;
import com.guildaSys.repository.interfaces.HeroRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class HeroRepositoryImpl extends GenericRepositoryImpl<Hero, Long> implements HeroRepository {

    public HeroRepositoryImpl() {
        super(Hero.class);
    }

    public Optional<Hero> findByNickname(String nickname){
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Hero> query = em.createQuery(
                "SELECT h FROM Hero h WHERE LOWER(h.nickname) like LOWER(:nickname)", Hero.class);
        query.setParameter("nickname", "%" + nickname + "%");
        return query.getResultStream().findFirst();
    }

    public Optional<Hero> findByIdHeroWithGuildAndInventory(Long idHero)
    {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Hero> query = em.createQuery(
                "SELECT h FROM Hero h JOIN FETCH h.guild JOIN FETCH h.inventory WHERE h.heroId = :idHero", Hero.class
        );
        query.setParameter("idHero", idHero);
        return query.getResultStream().findFirst();
    }

    public List<Hero> getHeroesByGuildSortedByXp (Guild guild){
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Hero> query = em.createQuery(
                "SELECT h FROM Hero h WHERE h.guild = :guild ORDER BY h.xp DESC", Hero.class
        );
        query.setParameter("guild", guild);
        return query.getResultList();
    }
}
