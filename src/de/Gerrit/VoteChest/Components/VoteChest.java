package de.Gerrit.VoteChest.Components;

import org.bukkit.material.Chest;


public class VoteChest extends Chest {

    private int chestId = 0;

    public int getChestId() {
        return chestId;
    }

    public void setChestId(int chestId) {
        this.chestId = chestId;
    }
}

