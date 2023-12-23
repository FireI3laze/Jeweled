package de.foldingfan.jeweled.common.capability.effect;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CustomPlayerCapabilities {
    public static Capability<CustomPlayerEffect> PLAYER_EFFECT_CAPABILITY;

    public static void registerCapabilities() {
        PLAYER_EFFECT_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    }
}
