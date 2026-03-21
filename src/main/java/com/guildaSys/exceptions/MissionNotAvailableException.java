package com.guildaSys.exceptions;

import com.guildaSys.entity.Mission;

public class MissionNotAvailableException extends BusinessException {
    public MissionNotAvailableException(Mission mission) {
        super("Mission: " + mission.getTitle() + " is not available. Current status: " + mission.getStatus());
    }
}
