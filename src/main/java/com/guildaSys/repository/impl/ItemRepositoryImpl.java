package com.guildaSys.repository.impl;

import com.guildaSys.entity.Item;
import com.guildaSys.repository.interfaces.ItemRepository;
import com.guildaSys.repository.interfaces.GenericRepository;

public class ItemRepositoryImpl extends GenericRepositoryImpl<Item, Long>  implements ItemRepository {
    public ItemRepositoryImpl() {
        super(Item.class);
    }
}
