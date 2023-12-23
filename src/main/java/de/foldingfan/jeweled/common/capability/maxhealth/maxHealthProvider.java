package de.foldingfan.jeweled.common.capability.maxhealth;
/*
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

public class maxHealthProvider implements ICapabilityProvider, ICustomPlayerData, INBTSerializable<CompoundTag> {
    public static Capability<PlayerMaxHealth> PLAYER_MAXHEALTH = CapabilityManager.get(new CapabilityToken<PlayerMaxHealth>() { });

    private PlayerMaxHealth playerMaxHealth = null;
    private int maxHealth;
    private final LazyOptional<PlayerMaxHealth> optional = LazyOptional.of(this::createPlayerMaxHealth);

    private PlayerMaxHealth createPlayerMaxHealth() {
        if (this.playerMaxHealth == null) {
            this.playerMaxHealth = new PlayerMaxHealth();
        }

        return this.playerMaxHealth;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MAXHEALTH) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerMaxHealth().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMaxHealth().loadNBTData(nbt);

    }
}
*/