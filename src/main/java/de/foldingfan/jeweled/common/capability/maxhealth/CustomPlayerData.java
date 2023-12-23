package de.foldingfan.jeweled.common.capability.maxhealth;

import net.minecraft.nbt.CompoundTag;

public class CustomPlayerData {
    private float maxHealth;
    private boolean effect;
    private float theoreticalMaxHealth = 20;
    private final float MIN_MAXHEALTH = 2;
    private final float MAX_MAXHEALTH = 30;

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getTheoreticalMaxHealth() {
        return theoreticalMaxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setTheoreticalMaxHealth(float theoreticalMaxHealth) {
        this.theoreticalMaxHealth = theoreticalMaxHealth;
    }

    public boolean getModEffect() {
        return effect;
    }

    public void setModEffect(boolean allowed) {
        if (allowed) {
            effect = true;
        } else effect = false;
    }

    public void addMaxHealth(float add) {
        this.maxHealth = Math.min(maxHealth + add, MAX_MAXHEALTH);
        this.theoreticalMaxHealth = Math.min(theoreticalMaxHealth + add, MAX_MAXHEALTH);
    }

    public void subMaxHealth(float sub) {
        this.maxHealth = Math.max(maxHealth + sub, MIN_MAXHEALTH);
    }

    public void copyFrom(CustomPlayerData source) {
        this.maxHealth = source.maxHealth;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putFloat("maxHealth", maxHealth);
    }

    public void loadNBTData(CompoundTag nbt) {
        maxHealth = nbt.getFloat("maxHealth");
    }
}

