package de.foldingfan.firstmod.capability.maxhealth;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;

public abstract class PlayerDataStorage /*implements ICustomPlayerData*/ {
    /*
    public CompoundTag writeNBT(Capability<ICustomPlayerData> capability, ICustomPlayerData instance, Direction side) {
        CompoundTag compound = new CompoundTag();
        compound.putFloat("maxHealth", instance.getMaxHealth());
        return compound;
    }

    public void readNBT(Capability<ICustomPlayerData> capability, ICustomPlayerData instance, Direction side, CompoundTag nbt) {
        if (nbt == null) {
            CompoundTag compound = (CompoundTag) nbt;
            instance.setMaxHealth(compound.getFloat("maxHealth"));
        }
    }
    +/
     */
}
