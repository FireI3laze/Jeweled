package de.foldingfan.firstmod.event;

import de.foldingfan.firstmod.block.custom.Speed_Block;
import de.foldingfan.firstmod.capability.effect.CustomEffectProvider;
import de.foldingfan.firstmod.capability.effect.CustomPlayerEffect;
import de.foldingfan.firstmod.capability.maxhealth.*;
//import de.foldingfan.firstmod.capability.maxhealth.CustomPlayerData;
import de.foldingfan.firstmod.item.ModArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import de.foldingfan.firstmod.firstMod;
//import de.foldingfan.firstmod.capability.maxhealth.PlayerMaxHealth;
//import de.foldingfan.firstmod.capability.maxhealth.maxHealthProvider;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = firstMod.MOD_ID)
public class ModEvents{

    public static float maxHealth;

    @SubscribeEvent //not used atm

    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event){
        AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
        System.out.println("Test12");
        if(event.getEntity().getMaxHealth() != 20) {
            attribute.setBaseValue(20);
            System.out.println("Test123");
        }
    }

/*
    @SubscribeEvent
    public void init(FMLClientLaunchHandler event) {
        // Erstelle eine Instanz der PlayerHealthManager-Klasse
        healthManager = new PlayerHealthManager();

        // Registriere den Event-Listener
        MinecraftForge.EVENT_BUS.register(this);
    }

 */

    /*
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(maxHealthProvider.PLAYER_MAXHEALTH).isPresent()) {
                event.addCapability(new ResourceLocation(firstMod.MOD_ID, "properties"), new maxHealthProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(maxHealthProvider.PLAYER_MAXHEALTH).ifPresent(oldStore -> {
                event.getOriginal().getCapability(maxHealthProvider.PLAYER_MAXHEALTH).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerMaxHealth.class);
    }
    */

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            Speed_Block.toggle = false;
            player.sendSystemMessage(Component.literal("Noob"));
        }
        if (event.getEntity() instanceof Animal animal) {
            event.getSource().getEntity().sendSystemMessage(Component.literal("You will take responsibility for your actions"));
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            player.sendSystemMessage(Component.literal("I can see what you are doing"));
        }
        if (event.getEntity() instanceof Player player){

            // Setze die maximalen Leben des Spielers
            CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
            playerData.setMaxHealth(player.getMaxHealth());
            maxHealth = player.getMaxHealth(); //Übergangslösung die nur im Singleplayer funktioniert. Problem ist das der Player neue NBT Daten bekommt wenn er stirbt
            player.sendSystemMessage(Component.literal("" + playerData.getMaxHealth()));
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(firstMod.MOD_ID, "properties"), new CustomPlayerDataProvider());
            }/*
            if (event.getObject() instanceof Player) {
                if (!event.getObject().getCapability(CustomEffectProvider.PLAYER_EFFECT_CAPABILITY).isPresent()) {
                    event.addCapability(new ResourceLocation(firstMod.MOD_ID, "properties"), new CustomEffectProvider());
                }
            }*/
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()) {
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

            CustomPlayerData playerData = event.getEntity().getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
            //float maxHealth = playerData.getMaxHealth();
            AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
            attribute.setBaseValue(maxHealth);
            event.getEntity().sendSystemMessage(Component.literal("Max Health manipulated to " + maxHealth));
            System.out.println("Max Health manipulated");
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(CustomPlayerData.class);
        event.register(CustomPlayerEffect.class);
    }

    @SubscribeEvent
    public static void onArmorEquipped(LivingEquipmentChangeEvent event) {
        if (event.getEntity() instanceof Player player) // filters to player
        {
            EquipmentSlot slotChanged = event.getSlot(); // get the slots
            if (slotChanged.getIndex() >= 0 && slotChanged.getIndex() < 4) // filters to the value for the armor slots
            {
                event.getEntity().sendSystemMessage(Component.literal("Any Swap"));
                try {
                    switch (slotChanged.getIndex()) {
                        case 0: // value for the armor boots slot (and apparently for changing the items in the main-hand under certain conditions)
                            try {
                                ArmorItem boots = (ArmorItem) player.getInventory().getArmor(0).getItem(); // gets the item of the player in the armor slot (makes an error when the slot is empty, so try/catch)
                                if (boots.getMaterial() == ModArmorMaterials.NETHERITE_JEWELED){ // checks if the armor is from the correct set
                                    CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                    playerData.setMaxHealth(playerData.getMaxHealth()); // checks the current max health
                                    AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                    //attribute.setBaseValue(playerData.getMaxHealth() - 1); // applying the new max health to the player
                                    //player.hurt(DamageSource.MAGIC, 1); // damaging the player by the amount of max health lost
                                }
                            } catch (Exception e) { //triggers when the check of the armor material occurs an error (because it is empty)
                                CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                playerData.setMaxHealth(player.getMaxHealth()); // checks the current max health
                                //AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                //attribute.setBaseValue(playerData.getMaxHealth() + 1); // applying the new max health to the player (+1 because if the slot changes to no armor
                            }
                            break;

                        case 1:
                            try {
                                ArmorItem leggings = (ArmorItem) player.getInventory().getArmor(1).getItem();
                                if (leggings.getMaterial() == ModArmorMaterials.NETHERITE_JEWELED){
                                    CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                    playerData.setMaxHealth(player.getMaxHealth());
                                    AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                    attribute.setBaseValue(playerData.getMaxHealth() - 1);
                                    player.hurt(DamageSource.MAGIC, 1);
                                }
                            } catch (Exception e) {
                                CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                playerData.setMaxHealth(player.getMaxHealth());
                                AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                attribute.setBaseValue(playerData.getMaxHealth() + 1);
                            }
                            break;

                        case 2:
                            try {
                                ArmorItem chestplate = (ArmorItem) player.getInventory().getArmor(2).getItem();
                                if (chestplate.getMaterial() == ModArmorMaterials.NETHERITE_JEWELED){
                                    CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                    playerData.setMaxHealth(player.getMaxHealth());
                                    AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                    attribute.setBaseValue(playerData.getMaxHealth() - 2);
                                    player.hurt(DamageSource.MAGIC, 2);
                                }
                            } catch (Exception e) {
                                CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                playerData.setMaxHealth(player.getMaxHealth());
                                AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                attribute.setBaseValue(playerData.getMaxHealth() + 2);
                            }
                            break;

                        case 3:
                            try {
                                ArmorItem helmet = (ArmorItem) player.getInventory().getArmor(3).getItem();
                                if (helmet.getMaterial() == ModArmorMaterials.NETHERITE_JEWELED){
                                    CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                    playerData.setMaxHealth(player.getMaxHealth());
                                    AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                    attribute.setBaseValue(playerData.getMaxHealth() - 1);
                                    player.hurt(DamageSource.MAGIC, 1);
                                }
                            } catch (Exception e) {
                                CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                                playerData.setMaxHealth(player.getMaxHealth());
                                AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                                attribute.setBaseValue(playerData.getMaxHealth() + 1);
                            }
                            break;
                    }

                } catch (Exception e) {
                    event.getEntity().sendSystemMessage(Component.literal("Empty"));
                }
            }

            /*
            if (slotChanged.getIndex() > 0 && slotChanged.getIndex() < 5) // Check out the class EntityEquipmentSlot
            {
                //ItemStack newArmor = event.getTo();
                //boolean isSlotOccupied = !newArmor.isEmpty();
                try {
                    event.getEntity().sendSystemMessage(Component.literal("Armor Material on slot 0 = " + ("" + boots.getMaterial())));
                } catch (Exception e) {
                    event.getEntity().sendSystemMessage(Component.literal("Empty"));
                }
            }

             */
            /*
            for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
                ArmorMaterial mapArmorMaterial = entry.getKey();
                MobEffectInstance mapStatusEffect = entry.getValue();
                //if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                    //CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                    //playerData.setMaxHealth(player.getMaxHealth());
                    //AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                    //attribute.setBaseValue(playerData.getMaxHealth() - 1);
                //}
                //if(hasCorrectHelmetOn(mapArmorMaterial, player)) {
                    //CustomPlayerData playerData = player.getCapability(CustomPlayerDataProvider.PLAYER_DATA_CAPABILITY).orElseThrow(NullPointerException::new);
                    //playerData.setMaxHealth(player.getMaxHealth());
                    //AttributeInstance attribute = event.getEntity().getAttribute(Attributes.MAX_HEALTH);
                    //attribute.setBaseValue(playerData.getMaxHealth() - 1);
                //}
            }
             */
        }
    }

    /*@Nullable
    @Override
    public INBT writeNBT(Capability<ICustomPlayerData> capability, ICustomPlayerData instance, Direction side) {
        CompoundTag compound = new CompoundTag();
        compound.putInt("maxHealth", instance.getMaxHealth());
        return compound;
    }

    @Override
    public void readNBT(Capability<ICustomPlayerData> capability, ICustomPlayerData instance, Direction side, INBT nbt) {
        if (nbt instanceof CompoundNBT) {
            CompoundNBT compound = (CompoundNBT) nbt;
            instance.setMaxHealth(compound.getInt("maxHealth"));
        }
    }*/


    // Beim Tod des Spielers, die maximalen Leben wiederherstellen

}
