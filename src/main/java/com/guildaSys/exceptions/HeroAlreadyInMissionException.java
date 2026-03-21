package com.guildaSys.exceptions;

public class HeroAlreadyInMissionException extends BusinessException {
    public HeroAlreadyInMissionException(String heroNickname, String missionTitle) {
        super("The hero: " + heroNickname + " is already in a mission in progress: " + missionTitle);
    }
}
