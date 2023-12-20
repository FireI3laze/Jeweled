package de.foldingfan.firstmod.capability.maxhealth;

import net.minecraft.nbt.CompoundTag;

public interface ICustomPlayerData {
    float getMaxHealth();
    float getTheoreticalMaxHealth();
    void setMaxHealth(float maxHealth);
    public void addMaxHealth(float add);
    public void subMaxHealth(float sub);
    void saveNBT(CompoundTag nbt);

    void loadNBT(CompoundTag nbt);
}
