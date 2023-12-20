package de.foldingfan.firstmod.item;

import de.foldingfan.firstmod.firstMod;
import de.foldingfan.firstmod.item.custom.ModArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, firstMod.MOD_ID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> JEWEL_SLOT = ITEMS.register("jewel_slot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> ZIRCON_JEWEL = ITEMS.register("zircon_jewel",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_HELMET_EMPTY = ITEMS.register("netherite_helmet_empty",
            () -> new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY , EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_CHESTPLATE_EMPTY = ITEMS.register("netherite_chestplate_empty",
            () -> new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_LEGGING_EMPTY = ITEMS.register("netherite_leggings_empty",
            () -> new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_BOOTS_EMPTY = ITEMS.register("netherite_boots_empty",
            () -> new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_HELMET_JEWELED = ITEMS.register("netherite_helmet_jeweled",
            () -> new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, EquipmentSlot.HEAD,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_CHESTPLATE_JEWELED = ITEMS.register("netherite_chestplate_jeweled",
            () -> new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, EquipmentSlot.CHEST,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_LEGGING_JEWELED = ITEMS.register("netherite_leggings_jeweled",
            () -> new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, EquipmentSlot.LEGS,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));

    public static final RegistryObject<Item> NETHERITE_BOOTS_JEWELED = ITEMS.register("netherite_boots_jeweled",
            () -> new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, EquipmentSlot.FEET,
                    new Item.Properties().tab(ModCreativeModeTab.MYTAB_TAB)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
