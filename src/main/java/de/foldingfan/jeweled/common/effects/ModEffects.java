package de.foldingfan.jeweled.common.effects;

import de.foldingfan.jeweled.Jeweled;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Jeweled.ID);

    public static final RegistryObject<MobEffect> NEGATEDABSOPTION = MOB_EFFECTS.register("negated_absoption",
            () -> new NegatedAbsoption(MobEffectCategory.HARMFUL, 9999999));

    public static final RegistryObject<MobEffect> DRAINED = MOB_EFFECTS.register("drained",
            () -> new Drained(MobEffectCategory.NEUTRAL, 12891242));

    public static void register() {
        MOB_EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
