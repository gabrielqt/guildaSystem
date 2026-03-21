package com.guildaSys.entity;

import com.guildaSys.enums.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heroId;

    @NotBlank
    @Column(unique = true)
    private String nickname;

    @Min(0)
    @Max(15000)
    @NotNull
    private Integer xp;

    @ManyToMany
    @JoinTable(
            name="hero_inventory",    // create the intermediate table named "player_inventory"
            joinColumns = @JoinColumn(name="hero_id"),    // the column that points to my table (Hero) is hero_id
            inverseJoinColumns = @JoinColumn(name="item_id") // column the other side
    )
    private Set<Item> inventory = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Hero hero)) return false;
        return Objects.equals(heroId, hero.heroId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(heroId);
    }

    public Hero() {
    }

    public Hero(Long heroId, String nickname, Integer xp, Set<Item> inventory) {
        this.heroId = heroId;
        this.nickname = nickname;
        this.xp = xp;
        this.inventory = inventory;
    }

    public Long getIdHero() {
        return heroId;
    }

    public void setIdHero(Long heroId) {
        this.heroId = heroId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getXp() {
        return xp;
    }

    public void addXp(Integer xp){
        setXp(this.getXp() + xp);
    }

    public void setXp(Integer xp) {
        if (xp > 15000){
            this.xp = 15000;
        }
        else {
            this.xp = xp;
        }
    }

    public Set<Item> getInventory() {
        return inventory;
    }

    public void setInventory(Set<Item> inventory) {
        this.inventory = inventory;
    }

    public Level getLevel() {
        return Level.getLevelByXp(this.xp);
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    @Override
    public String toString() {
        return
                "- heroId=" + heroId + '\n' +
                "- nickname=" + nickname + '\n' +
                "- level=" + getLevel() + '\n';
    }


}
