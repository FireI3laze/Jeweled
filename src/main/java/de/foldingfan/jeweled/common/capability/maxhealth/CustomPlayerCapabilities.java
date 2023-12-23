package de.foldingfan.jeweled.common.capability.maxhealth;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CustomPlayerCapabilities {
    public static Capability<ICustomPlayerData> PLAYER_DATA_CAPABILITY;

    public static void registerCapabilities() {
        PLAYER_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    }
}
