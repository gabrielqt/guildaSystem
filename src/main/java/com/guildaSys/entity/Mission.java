package com.guildaSys.entity;


import com.guildaSys.enums.EnumMissionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missionId;

    @NotBlank
    @Size(min = 5, max = 100)
    @Column(unique = true)
    private String title;

    @Min(0)
    @Max(15000)
    private Integer minimalXp;

    @Min(0)
    @Max(15000)
    private Integer xpReward;

    @ManyToMany
    @JoinTable(
            name = "mission_heroes",
            joinColumns = @JoinColumn(name="mission_id"),
            inverseJoinColumns = @JoinColumn (name = "hero_id")
    )
    private Set<Hero> missionHeroes = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private EnumMissionStatus status;

    public Mission(Long missionId, String title, Integer minimalXp, Integer xpReward, Set<Hero> missionHeroes, EnumMissionStatus status) {
        this.missionId = missionId;
        this.title = title;
        this.minimalXp = minimalXp;
        this.xpReward = xpReward;
        this.missionHeroes = missionHeroes;
        this.status = status;
    }

    public Mission() {}

    public Long getMissionId() {
        return missionId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mission mission)) return false;
        return Objects.equals(getMissionId(), mission.getMissionId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMissionId());
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinimalXp() {
        return minimalXp;
    }

    public void setMinimalXp(Integer minimalXp) {
        this.minimalXp = minimalXp;
    }

    public Integer getXpReward() {
        return xpReward;
    }

    public void setXpReward(Integer xpReward) {
        this.xpReward = xpReward;
    }

    public Set<Hero> getMissionHeroes() {
        return missionHeroes;
    }

    public void setMissionHeroes(Set<Hero> missionHeroes) {
        this.missionHeroes = missionHeroes;
    }

    public EnumMissionStatus getStatus() {
        return status;
    }

    public void setStatus(EnumMissionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "missionId=" + missionId +
                ", title='" + title + '\'' +
                ", minimalXp=" + minimalXp +
                ", xpReward=" + xpReward +
                ", missionHeroes=" + missionHeroes +
                ", status=" + status +
                '}';
    }
}
