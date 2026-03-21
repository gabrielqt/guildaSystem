package com.guildaSys.exceptions;

public class InsufficientHeroXpException extends BusinessException {
    public InsufficientHeroXpException(Integer heroXp, Integer missionXp) {
        super("Insufficient XP: hero has " + heroXp + " but mission requires " + missionXp + ".");    }
}
