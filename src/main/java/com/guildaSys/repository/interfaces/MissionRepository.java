package com.guildaSys.repository.interfaces;

import com.guildaSys.entity.Mission;

import java.util.Optional;

public interface MissionRepository extends GenericRepository<Mission,Long>{
    Optional<Mission> findMissionByHeroId(Long heroId);
}
