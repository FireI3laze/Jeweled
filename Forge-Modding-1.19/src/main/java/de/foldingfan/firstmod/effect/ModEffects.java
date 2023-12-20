package de.foldingfan.firstmod.effect;

import de.foldingfan.firstmod.firstMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, firstMod.MOD_ID);

    public static final RegistryObject<MobEffect> NEGATEDABSOPTION = MOB_EFFECTS.register("negated_absoption",
            () -> new NegatedAbsoption(MobEffectCategory.HARMFUL, 9999999));

    public static final RegistryObject<MobEffect> DRAINED = MOB_EFFECTS.register("drained",
            () -> new Drained(MobEffectCategory.NEUTRAL, 12891242));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
