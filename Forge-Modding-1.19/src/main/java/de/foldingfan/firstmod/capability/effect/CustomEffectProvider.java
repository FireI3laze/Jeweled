package de.foldingfan.firstmod.capability.effect;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomEffectProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<CustomPlayerEffect> PLAYER_EFFECT_CAPABILITY = CapabilityManager.get(new CapabilityToken<CustomPlayerEffect>() { });

    private CustomPlayerEffect effect = null;
    private final LazyOptional<CustomPlayerEffect> optional = LazyOptional.of(this::createCustomEffectData);

    private CustomPlayerEffect createCustomEffectData() {
        if(this.effect == null){
            this.effect = new CustomPlayerEffect();
        }
        return this.effect;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_EFFECT_CAPABILITY){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCustomEffectData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCustomEffectData().loadNBTData(nbt);
    }
}
