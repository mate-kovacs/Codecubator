package com.codecool.poop.model.assignments;

import com.codecool.poop.model.Rooms;
import com.codecool.poop.model.Skills;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="assignment_type",
        discriminatorType = DiscriminatorType.STRING)
@Table(name = "assignments")
public abstract class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @ElementCollection
    @CollectionTable(name = "exp_rewards")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<Skills,Integer> expRewards;
    private Integer codeCoinReward;
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    private Rooms room;

    protected Assignment() {}

    public Assignment(String name, String description, Map<Skills, Integer> expRewards, Integer codeCoinReward, Rooms room) {
        if (!isValidexpRewards(expRewards)) {
            throw new IllegalArgumentException("Invalid reward map");
        }
        this.name = name;
        this.description = description;
        this.expRewards = expRewards;
        this.codeCoinReward = codeCoinReward;
        this.creationDate = LocalDateTime.now();
        this.room = room;
    }

    public abstract Integer getMaxPoints();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Skills, Integer> getExpRewards() {
        return expRewards;
    }

    public void setExpRewards(Map<Skills, Integer> expRewards) {
        this.expRewards = expRewards;
    }

    public Integer getCodeCoinReward() {
        return codeCoinReward;
    }

    public void setCodeCoinReward(Integer codeCoinReward) {
        this.codeCoinReward = codeCoinReward;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean isValidexpRewards(Map<Skills, Integer> expRewards) {
        return expRewards != null && expRewards.size() > 0 && isNoNegativeInRewardsMap(expRewards);
    }

    private boolean isNoNegativeInRewardsMap(Map<Skills, Integer> expRewards) {
        for (Integer value : expRewards.values()) {
            if (value < 0) return false;
        }
        return true;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", expRewards=" + expRewards +
                ", codeCoinReward=" + codeCoinReward +
                ", creationDate=" + creationDate +
                '}';
    }

}
