package com.guildaSys.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Guild")
public class Guild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guildId;

    @NotBlank
    private String guildName;

    @OneToMany(mappedBy = "guild")
    private List<Hero> guildHeroes;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Guild guild)) return false;
        return Objects.equals(getGuildId(), guild.getGuildId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGuildId());
    }

    public Guild() {
    }

    public Guild(Long guildId, String guildName, List<Hero> guildHeroes) {
        this.guildId = guildId;
        this.guildName = guildName;
        this.guildHeroes = guildHeroes;
    }

    public Long getGuildId() {
        return guildId;
    }

    public void setGuildId(Long guildId) {
        this.guildId = guildId;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public List<Hero> getGuildHeroes() {
        return guildHeroes;
    }

    public void setGuildHeroes(List<Hero> guildHeroes) {
        this.guildHeroes = guildHeroes;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "guildId=" + guildId +
                ", guildName='" + guildName + '\'' +
                ", guildHeroes=" + guildHeroes +
                '}';
    }
}