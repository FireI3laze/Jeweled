package de.foldingfan.jeweled.common.capability.equippedarmor;

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

public class EquippedArmorProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<EquippedArmor> PLAYER_EQUIPPED_ARMOR_COUNT = CapabilityManager.get(new CapabilityToken<EquippedArmor>() { });

    private EquippedArmor equippedArmor = null;
    private final LazyOptional<EquippedArmor> optional = LazyOptional.of(this::createPlayerEquippedArmor);

    private EquippedArmor createPlayerEquippedArmor() {
        if (this.equippedArmor == null) {

            this.equippedArmor = new EquippedArmor();

        }

        return this.equippedArmor;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == PLAYER_EQUIPPED_ARMOR_COUNT) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {

        CompoundTag nbt = new CompoundTag();
        createPlayerEquippedArmor().saveNBTData(nbt);

        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {

        createPlayerEquippedArmor().loadNBTData(nbt);

    }

}
