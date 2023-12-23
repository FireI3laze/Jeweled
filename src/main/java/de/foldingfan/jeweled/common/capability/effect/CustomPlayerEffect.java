package de.foldingfan.jeweled.common.capability.effect;

import net.minecraft.nbt.CompoundTag;

public class CustomPlayerEffect {

    private boolean effect;
    public boolean getEffect() {
        return effect;
    }
    public void setEffect(boolean allowed){
        if (allowed) {
            effect = true;
        }
        else  effect = false;
    }

    public void copyFrom(CustomPlayerEffect source){
        this.effect = source.effect;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("effect", effect);
    }

    public void loadNBTData(CompoundTag nbt) {
        effect = nbt.getBoolean("effect");
    }
}
