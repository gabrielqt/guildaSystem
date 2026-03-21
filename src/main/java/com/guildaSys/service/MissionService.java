package com.guildaSys.service;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Hero;
import com.guildaSys.entity.Mission;
import com.guildaSys.exceptions.HeroAlreadyInMissionException;
import com.guildaSys.exceptions.InsufficientHeroXpException;
import com.guildaSys.exceptions.MissionNotAvailableException;
import com.guildaSys.repository.impl.MissionRepositoryImpl;
import com.guildaSys.repository.interfaces.GenericRepository;
import com.guildaSys.repository.interfaces.MissionRepository;
import com.guildaSys.enums.EnumMissionStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class MissionService extends GenericService <Mission, Long>{

    private final MissionRepository missionRepository = new MissionRepositoryImpl();
    @Override
    protected GenericRepository<Mission, Long> getRepository() {
        return missionRepository;
    }

    public Mission joinMission(Hero hero, Mission mission){
        if (mission.getMinimalXp() > hero.getXp()) {
            throw new InsufficientHeroXpException(hero.getXp(), mission.getMinimalXp());
        }
        if(!mission.getStatus().equals(EnumMissionStatus.AVAILABLE)){
            throw new MissionNotAvailableException(mission);
        }
        // Validate if the hero is in a mission IN_PROGRESS
        List<Mission> missionsHero = missionRepository.findMissionByHeroId(hero.getIdHero());
        for(Mission m : missionsHero){
            if (m.getStatus().equals(EnumMissionStatus.IN_PROGRESS)){
                throw new HeroAlreadyInMissionException(hero.getNickname(), m.getTitle());
            }
        }

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            mission.getMissionHeroes().add(hero);
            missionRepository.save(mission);
            tx.commit();
            return mission;
        } catch (Exception e){
            if(tx.isActive())tx.rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }



    // REFATORAR Optional<List

}
