package com.guildaSys.entity;

import com.guildaSys.enums.EnumMissionStatus;
import com.guildaSys.enums.EnumRarity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotBlank
    @Column(unique = true)
    private String itemName;

    @Enumerated(EnumType.STRING)
    private EnumRarity rarity;

    public Item(){}

    public Item(Long itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getItemId(), item.getItemId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getItemId());
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", rarity=" + rarity +
                '}';
    }
}
