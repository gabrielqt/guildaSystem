package com.guildaSys.repository.interfaces;

import com.guildaSys.entity.Guild;
import com.guildaSys.entity.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroRepository extends GenericRepository<Hero, Long> {
    Optional<Hero> findByNickname(String nickname);
    Optional<Hero> findByIdHeroWithGuildAndInventory(Long idHero);
    Optional<List<Hero>> getHeroesByGuildSortedByXp (Guild guild);
}
