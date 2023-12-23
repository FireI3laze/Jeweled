package de.foldingfan.jeweled.common;

import de.foldingfan.jeweled.Jeweled;
import de.foldingfan.jeweled.common.creativetab.CreativeModTabItems;
import de.foldingfan.jeweled.networking.ModMessages;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = Jeweled.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {

    @SubscribeEvent
    public static void onRegister(final RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, CreativeModTabItems::register);
    }

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
    }

}
