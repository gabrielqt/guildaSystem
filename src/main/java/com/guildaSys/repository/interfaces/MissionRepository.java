package com.guildaSys.repository.interfaces;

import com.guildaSys.entity.Mission;

import java.util.List;

public interface MissionRepository extends GenericRepository<Mission,Long>{
    List<Mission> findMissionByHeroId(Long heroId);
}




