package de.foldingfan.jeweled.common.capability.equippedarmor;

import net.minecraft.nbt.CompoundTag;

public class EquippedArmor {

    public static final String NBT_EQUIPPED_ARMOR_COUNT = "count";
    public static final int MIN_COUNT = 0;
    public static final int MAX_COUNT = 4;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount(int add) {
        this.count = Math.min(count + add, MAX_COUNT);
    }

    public void subCount(int sub) {
        this.count = Math.max(count - sub, MIN_COUNT);
    }

    public void copyFrom(EquippedArmor source) {
        this.count = source.count;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt(NBT_EQUIPPED_ARMOR_COUNT, count);
    }

    public void loadNBTData(CompoundTag nbt) {
        count = nbt.getInt(NBT_EQUIPPED_ARMOR_COUNT);
    }

}
