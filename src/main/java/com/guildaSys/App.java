package com.guildaSys;


import com.guildaSys.entity.Hero;
import com.guildaSys.entity.Mission;
import com.guildaSys.service.HeroService;
import com.guildaSys.service.MissionService;

import java.util.Optional;

public class App {

    public static void main(String[] args) {

        HeroService heroService = new HeroService();
        MissionService missionService = new MissionService();
        Hero h = heroService.findById(3L)
                        .orElseThrow(() -> new RuntimeException("Hero Not Found"));

        System.out.println(h.getXp());

//        missionService.joinMission(3L,5L);

        missionService.completeMission(5L);

        System.out.println(h.getXp());


    }
}
