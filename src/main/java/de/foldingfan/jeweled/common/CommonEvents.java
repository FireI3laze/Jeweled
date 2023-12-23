package de.foldingfan.jeweled.common;

import de.foldingfan.jeweled.Jeweled;
import de.foldingfan.jeweled.common.blocks.SpeedBlock;
import de.foldingfan.jeweled.common.capability.effect.CustomEffectProvider;
import de.foldingfan.jeweled.common.capability.effect.CustomPlayerEffect;
import de.foldingfan.jeweled.common.capability.equippedarmor.EquippedArmor;
import de.foldingfan.jeweled.common.capability.equippedarmor.EquippedArmorProvider;
import de.foldingfan.jeweled.common.capability.maxhealth.CustomPlayerData;
import de.foldingfan.jeweled.common.capability.maxhealth.CustomPlayerDataProvider;
import de.foldingfan.jeweled.common.items.ModItems;
import de.foldingfan.jeweled.networking.ModMessages;
import de.foldingfan.jeweled.networking.packet.ArmorEquippedC2SPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CommonEvents {

    @Mod.EventBusSubscriber(modid = Jeweled.ID)
    public static class ServerEvents {

        @SubscribeEvent
        public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        }

        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {

            if (event.getObject() instanceof Player) {

                if (!event.getObject().getCapability(EquippedArmorProvider.PLAYER_EQUIPPED_ARMOR_COUNT).isPresent()) {

                    event.addCapability(new ResourceLocation(Jeweled.ID, "armor_properties"), new EquippedArmorProvider());

                }

                if (!event.getObject().getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).isPresent()) {

                    event.addCapability(new ResourceLocation(Jeweled.ID, "properties"), new CustomPlayerDataProvider());

                }

            }

        }

        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event) {

            if (event.isWasDeath()) {

                event.getOriginal().getCapability(EquippedArmorProvider.PLAYER_EQUIPPED_ARMOR_COUNT).ifPresent(oldStore -> {

                    event.getOriginal().getCapability(EquippedArmorProvider.PLAYER_EQUIPPED_ARMOR_COUNT).ifPresent(newStore -> {

                        newStore.copyFrom(oldStore);

                    });

                });

                event.getOriginal().getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).ifPresent(oldStore -> {

                    event.getOriginal().getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).ifPresent(newStore -> {

                        newStore.copyFrom(oldStore);

                    });

                });

                event.getOriginal().getCapability(CustomEffectProvider.PLAYER_EFFECT_CAPABILITY).ifPresent(oldStore -> {

                    event.getOriginal().getCapability(CustomEffectProvider.PLAYER_EFFECT_CAPABILITY).ifPresent(newStore -> {

                        newStore.copyFrom(oldStore);

                    });

                });

            }

        }

        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
            event.register(EquippedArmor.class);
            event.register(CustomPlayerData.class);
            event.register(CustomPlayerEffect.class);
        }


        @SubscribeEvent
        public static void onEntityDeath(LivingDeathEvent event) {
            if (event.getEntity() instanceof Player player) {
                SpeedBlock.toggle = false;
                player.sendSystemMessage(Component.literal("Noob"));
            }
            if (event.getEntity() instanceof Animal animal) {
                event.getSource().getEntity().sendSystemMessage(Component.literal("You will take responsibility for your actions"));
            }
        }

        @SubscribeEvent
        public static void onArmorEquipped(LivingEquipmentChangeEvent event) {

            if (event.getEntity() instanceof Player player) {

                EquipmentSlot slot = event.getSlot();
                if (slot.getType() == EquipmentSlot.Type.ARMOR) {

                    String equippedArmorNameTo = event.getTo().getDisplayName().getString();
                    String equippedArmorNameFrom = event.getFrom().getDisplayName().getString();

                    if (equippedArmorNameTo.endsWith(")]")) {

                        armorEquipped(player, 1);
                        player.sendSystemMessage(Component.literal("Jeweled armor equipped"));

                    }

                    if (equippedArmorNameFrom.endsWith(")]")) {

                        player.sendSystemMessage(Component.literal("Jeweled armor removed"));
                        armorRemoved(player);

                    }

                }

            }

        }

        private static void armorEquipped(Player player, int hurtAmount) {

            player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).ifPresent(customPlayerData -> {

                customPlayerData.setMaxHealth(customPlayerData.getMaxHealth() - hurtAmount);
                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(player.getMaxHealth() - hurtAmount);
                player.hurt(player.damageSources().magic(), hurtAmount);

            });

        }

        private static void armorRemoved(Player player) {

            player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).ifPresent(customPlayerData -> {

                customPlayerData.setMaxHealth(customPlayerData.getMaxHealth());
                player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(Math.min(player.getAttribute(Attributes.MAX_HEALTH).getValue() + 1, 20));
                player.heal(1);

            });

        }


    }

}
