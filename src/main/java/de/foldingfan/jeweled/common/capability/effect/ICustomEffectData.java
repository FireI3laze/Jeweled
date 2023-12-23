package de.foldingfan.jeweled.common.capability.effect;

import net.minecraft.nbt.CompoundTag;

public interface ICustomEffectData {

    public boolean getEffect();

    public void setEffect(boolean allowed);
    void saveNBT(CompoundTag nbt);

    void loadNBT(CompoundTag nbt);
}
