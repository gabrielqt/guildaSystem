package com.guildaSys.service;

import com.guildaSys.config.JPAUtil;
import com.guildaSys.entity.Hero;
import com.guildaSys.entity.Mission;
import com.guildaSys.exceptions.*;
import com.guildaSys.repository.impl.HeroRepositoryImpl;
import com.guildaSys.repository.impl.MissionRepositoryImpl;
import com.guildaSys.repository.interfaces.GenericRepository;
import com.guildaSys.repository.interfaces.HeroRepository;
import com.guildaSys.repository.interfaces.MissionRepository;
import com.guildaSys.enums.EnumMissionStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


import java.util.List;
import java.util.Optional;

public class MissionService extends GenericService<Mission, Long> {

    private final MissionRepository missionRepository = new MissionRepositoryImpl();

    @Override
    protected GenericRepository<Mission, Long> getRepository() {
        return missionRepository;
    }

    public Mission joinMission(Long heroId, Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ObjectNotFoundException("Mission not found"));
        HeroRepository heroRepository = new HeroRepositoryImpl();

        Hero hero = heroRepository.findById(heroId)
                .orElseThrow(() -> new ObjectNotFoundException("Hero not found"));

        if (mission.getMinimalXp() > hero.getXp()) {
            throw new InsufficientHeroXpException(hero.getXp(), mission.getMinimalXp());
        }
        if (!mission.getStatus().equals(EnumMissionStatus.AVAILABLE)) {
            throw new MissionNotAvailableException(mission);
        }
        // Validate if the hero is in a mission IN_PROGRESS
        List<Mission> missionsHero = missionRepository.findMissionByHeroId(hero.getIdHero());
        for (Mission m : missionsHero) {
            if (m.getStatus().equals(EnumMissionStatus.IN_PROGRESS)
            || m.getStatus().equals(EnumMissionStatus.AVAILABLE)) {
                throw new HeroAlreadyInMissionException(hero.getNickname(), m.getTitle());
            }
        }

        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            mission.getMissionHeroes().add(hero);
            missionRepository.save(mission);
            tx.commit();
            return mission;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

    public void completeMission(Long missionId) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Mission mission = missionRepository.findById(missionId)
                    .orElseThrow(() -> new ObjectNotFoundException("Mission not found"));

            if (mission.getStatus().equals(EnumMissionStatus.COMPLETED)) {
                throw new MissionIsAlreadyCompleted(mission.getTitle());
            }
            mission.getMissionHeroes().forEach(hero -> hero.addXp(mission.getXpReward()));
            mission.setStatus(EnumMissionStatus.COMPLETED);
            missionRepository.save(mission);
            tx.commit();
            System.out.println("Mission completed.");
        } catch (Exception e) {
            System.out.println("It was not possible to finish mission");
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            JPAUtil.closeEntityManager();
        }
    }

}
