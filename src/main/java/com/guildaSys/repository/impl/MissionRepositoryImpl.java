package com.guildaSys.repository.impl;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Mission;
import com.guildaSys.repository.interfaces.MissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class MissionRepositoryImpl extends GenericRepositoryImpl<Mission, Long> implements MissionRepository{
    public MissionRepositoryImpl() {
        super(Mission.class);
    }

    @Override
    public Optional<Mission> findMissionByHeroId(Long heroId) {
        EntityManager em = JPAUtil.getEntityManager();
        TypedQuery<Mission> query = em.createQuery(
                "SELECT m FROM Mission m JOIN m.missionHeroes h WHERE h.id = :heroId", Mission.class
        );
        return query.getResultStream().findFirst();
    }
}
