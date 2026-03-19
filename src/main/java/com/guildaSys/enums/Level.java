package com.guildaSys.enums;

public enum Level {
    RECRUIT(1, 100),
    SQUIRE(2, 300),
    WARRIOR(3, 500),
    KNIGHT(4, 800),
    GUARDIAN(5, 1500),
    CHAMPION(6, 2000),
    LEGEND(7, 3000),
    WARLORD(8, 10000),
    HERO(9, 12500),
    MYTHIC(10, 15000);

    public final int number;
    public final int requiredXp;

    Level(int number, int requiredXp) {
        this.number = number;
        this.requiredXp = requiredXp;
    }

    public static Level getLevelByXp(int xp){
        Level current = RECRUIT;
        for(Level l : Level.values()){
            if (l.requiredXp >= xp){current = l;}
        }
        return current;
    }
}