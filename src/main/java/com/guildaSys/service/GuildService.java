package com.guildaSys.service;

import com.guildaSys.entity.Guild;
import com.guildaSys.repository.impl.GuildRepositoryImpl;
import com.guildaSys.repository.interfaces.GenericRepository;

public class GuildService extends GenericService<Guild, Long>{

    @Override
    protected GenericRepository<Guild, Long> getRepository() {
        return new GuildRepositoryImpl();
    }
}
