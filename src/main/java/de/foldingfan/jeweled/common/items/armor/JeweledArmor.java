package de.foldingfan.jeweled.common.items.armor;

import com.google.common.collect.ImmutableMap;
import de.foldingfan.jeweled.common.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class JeweledArmor extends ArmorItem {

    public JeweledArmor(ArmorItem.Type type) {
        super(ModArmorMaterials.NETHERITE_JEWELED, type, new Item.Properties());
    }

    //Full Set Effect
    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.NETHERITE_JEWELED,
                            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1)).build();

    //Max Health Debuff
    private static final Map<ArmorMaterial, MobEffectInstance> PIECE_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.NETHERITE_JEWELED,
                            new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(), 200, 1)).build();

    //One effect each armor piece
    private static final Map<ArmorMaterial, MobEffectInstance> CHESTPLATE_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.NETHERITE_JEWELED,
                            new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(), 200, 1)).build();

    private static final Map<ArmorMaterial, MobEffectInstance> LEGGINGS_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.NETHERITE_JEWELED,
                            new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(), 200, 1)).build();

    private static final Map<ArmorMaterial, MobEffectInstance> BOOTS_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.NETHERITE_JEWELED,
                            new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(), 200, 1)).build();


    public JeweledArmor(ArmorMaterial material, ArmorItem.Type type, Properties settings) {
        super(material, type, settings);
    }

    @SuppressWarnings("removal")
    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {

        if(!world.isClientSide()) {
            hasNetheriteHelmetJeweledOn(player);
            hasNetheriteChestplateJeweledOn(player);
            hasNetheriteLeggingsJeweledOn(player);
            hasNetheriteBootsJeweledOn(player);

            if(hasFullSuitOfArmorOn(player)) {
                evaluateArmorEffects(player);
            }

        }

    }

    private void evaluateArmorEffects(Player player) {

        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {

            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);
            }

        }

    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());

        if(hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addEffect(new MobEffectInstance(mapStatusEffect.getEffect(),
                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));

            //if(new Random().nextFloat() > 0.6f) { // 40% of damaging the armor! Possibly!
            //    player.getInventory().hurtArmor(DamageSource.MAGIC, 1f, new int[]{0, 1, 2, 3});
            //}
        }
    }

    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack breastplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !breastplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {
        ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    private void hasNetheriteHelmetJeweledOn(Player player) {
        ItemStack helmetSlot = player.getInventory().getArmor(3);
        if (!helmetSlot.isEmpty()) {
            for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : PIECE_TO_EFFECT_MAP.entrySet()) {
                ArmorMaterial mapArmorMaterial = entry.getKey();
                MobEffectInstance mapStatusEffect = entry.getValue();
                ArmorItem helmet = ((ArmorItem)player.getInventory().getArmor(3).getItem());
                if (helmet.getMaterial() == mapArmorMaterial) {
                    if(player.getMaxHealth() > 2) {
                        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
                        helmetSlot.setDamageValue(0);
                        /*
                        if(!hasPlayerEffect) {
                            player.addEffect(new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(),
                                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200));
                        }
                        */
                    }
                }
            }
        }
    }
    private void hasNetheriteChestplateJeweledOn(Player player) {
        ItemStack chestplateSlot = player.getInventory().getArmor(2);
        if (!chestplateSlot.isEmpty()) {
            for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : CHESTPLATE_TO_EFFECT_MAP.entrySet()) {
                ArmorMaterial mapArmorMaterial = entry.getKey();
                MobEffectInstance mapStatusEffect = entry.getValue();
                ArmorItem chestplate = ((ArmorItem)player.getInventory().getArmor(2).getItem());
                if (chestplate.getMaterial() == mapArmorMaterial) {
                    AttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);
                    if(player.getMaxHealth() > 2) {
                        boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
                        chestplateSlot.setDamageValue(0);
                        /*
                        if(!hasPlayerEffect) {
                            player.addEffect(new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(),
                                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200));
                        }
                        */
                    }
                }
            }
        }
    }

    private void hasNetheriteLeggingsJeweledOn(Player player) {
        ItemStack leggingsSlot = player.getInventory().getArmor(1);
        if (!leggingsSlot.isEmpty()) {
            for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : LEGGINGS_TO_EFFECT_MAP.entrySet()) {
                ArmorMaterial mapArmorMaterial = entry.getKey();
                MobEffectInstance mapStatusEffect = entry.getValue();
                ArmorItem leggings = ((ArmorItem)player.getInventory().getArmor(1).getItem());
                if(player.getMaxHealth() > 2) {
                    boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
                    leggingsSlot.setDamageValue(0);
                    /*
                        if(!hasPlayerEffect) {
                            player.addEffect(new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(),
                                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200));
                        }
                    */
                }
            }
        }
    }

    private void hasNetheriteBootsJeweledOn(Player player) {
        ItemStack bootsSlot = player.getInventory().getArmor(0);
        if (!bootsSlot.isEmpty()) {
            for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : BOOTS_TO_EFFECT_MAP.entrySet()) {
                ArmorMaterial mapArmorMaterial = entry.getKey();
                MobEffectInstance mapStatusEffect = entry.getValue();
                ArmorItem boots = ((ArmorItem)player.getInventory().getArmor(0).getItem());
                if(player.getMaxHealth() > 2) {
                    boolean hasPlayerEffect = player.hasEffect(mapStatusEffect.getEffect());
                    bootsSlot.setDamageValue(0);
                    /*
                        if(!hasPlayerEffect) {
                            player.addEffect(new MobEffectInstance(ModEffects.NEGATEDABSOPTION.get(),
                                    mapStatusEffect.getDuration(), mapStatusEffect.getAmplifier()));
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200));
                        }
                    */
                }
            }
        }
    }
}
