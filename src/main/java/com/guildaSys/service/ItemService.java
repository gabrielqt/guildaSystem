package com.guildaSys.service;

import com.guildaSys.entity.Item;
import com.guildaSys.repository.impl.ItemRepositoryImpl;
import com.guildaSys.repository.interfaces.GenericRepository;

public class ItemService extends GenericService<Item, Long> {
    @Override
    protected GenericRepository<Item, Long> getRepository(){
        return new ItemRepositoryImpl();
    }
}
