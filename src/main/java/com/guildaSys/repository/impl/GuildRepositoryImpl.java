package com.guildaSys.repository.impl;

import com.guildaSys.entity.Guild;
import com.guildaSys.repository.interfaces.GuildRepository;

public class GuildRepositoryImpl extends GenericRepositoryImpl<Guild,Long> implements GuildRepository {
    public GuildRepositoryImpl() {
        super(Guild.class);
    }
}
