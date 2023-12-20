package de.foldingfan.firstmod.capability.maxhealth;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.commons.lang3.concurrent.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomPlayerDataProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<CustomPlayerData> PLAYER_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<CustomPlayerData>() { });

    private CustomPlayerData maxHealth = null;
    private final LazyOptional<CustomPlayerData> optional = LazyOptional.of(this::createCustomPlayerData);

    private CustomPlayerData createCustomPlayerData() {
        if(this.maxHealth == null){
            this.maxHealth = new CustomPlayerData();
        }
        return this.maxHealth;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_DATA_CAPABILITY){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createCustomPlayerData().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCustomPlayerData().loadNBTData(nbt);
    }
}
