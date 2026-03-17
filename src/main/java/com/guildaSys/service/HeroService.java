package com.guildaSys.service;

import com.guildaSys.entity.Hero;
import com.guildaSys.repository.impl.HeroRepositoryImpl;
import com.guildaSys.repository.interfaces.GenericRepository;

import java.util.Optional;

public class HeroService extends GenericService<Hero, Long>{

    private final HeroRepositoryImpl heroRepository = new HeroRepositoryImpl();

    @Override
    protected GenericRepository<Hero, Long> getRepository() {
        return heroRepository;
    }

    public Optional<Hero> findByNickname(String nickname){
        return heroRepository.findByNickname(nickname);
    }
}
