package com.guildaSys.exceptions;

public class MissionIsAlreadyCompleted extends BusinessException {
    public MissionIsAlreadyCompleted(String missionTitle) {
        super("Mission " + missionTitle + " is already completed");
    }
}
