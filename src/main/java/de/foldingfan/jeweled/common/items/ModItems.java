package de.foldingfan.jeweled.common.items;

import de.foldingfan.jeweled.Jeweled;
import de.foldingfan.jeweled.common.items.armor.ModArmorItem;
import de.foldingfan.jeweled.common.items.armor.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Jeweled.ID);

    public static RegistryObject<Item> ZIRCON;
    public static RegistryObject<Item> RAW_ZIRCON;
    public static RegistryObject<Item> JEWEL_SLOT;
    public static RegistryObject<Item> ZIRCON_JEWEL;

    public static RegistryObject<Item> NETHERITE_HELMET_EMPTY;
    public static RegistryObject<Item> NETHERITE_CHESTPLATE_EMPTY;
    public static RegistryObject<Item> NETHERITE_LEGGING_EMPTY;
    public static RegistryObject<Item> NETHERITE_BOOTS_EMPTY;

    public static RegistryObject<Item> NETHERITE_HELMET_JEWELED;
    public static RegistryObject<Item> NETHERITE_CHESTPLATE_JEWELED;
    public static RegistryObject<Item> NETHERITE_LEGGING_JEWELED;
    public static RegistryObject<Item> NETHERITE_BOOTS_JEWELED;

    static {

        //Zircon
        ZIRCON = ITEMS.register("zircon", () -> new Item(new Item.Properties()));
        ZIRCON_JEWEL = ITEMS.register("zircon_jewel", () -> new Item(new Item.Properties()));
        RAW_ZIRCON = ITEMS.register("raw_zircon", () -> new Item(new Item.Properties()));

        //Jewel slot?
        JEWEL_SLOT = ITEMS.register("jewel_slot", () -> new Item(new Item.Properties()));

        //Netherite Armor Empty
        NETHERITE_HELMET_EMPTY = ITEMS.register("netherite_helmet_empty", () ->
                new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, ArmorItem.Type.HELMET, new Item.Properties()));
        NETHERITE_CHESTPLATE_EMPTY = ITEMS.register("netherite_chestplate_empty", () ->
                new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        NETHERITE_LEGGING_EMPTY = ITEMS.register("netherite_leggings_empty", () ->
                new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        NETHERITE_BOOTS_EMPTY = ITEMS.register("netherite_boots_empty", () ->
                new ArmorItem(ModArmorMaterials.NETHERITE_EMPTY, ArmorItem.Type.BOOTS, new Item.Properties()));

        //Netherite Armor Jeweled
        NETHERITE_HELMET_JEWELED = ITEMS.register("netherite_helmet_jeweled", () ->
                new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, ArmorItem.Type.HELMET, new Item.Properties()));
        NETHERITE_CHESTPLATE_JEWELED = ITEMS.register("netherite_chestplate_jeweled", () ->
                new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
        NETHERITE_LEGGING_JEWELED = ITEMS.register("netherite_leggings_jeweled", () ->
                new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
        NETHERITE_BOOTS_JEWELED = ITEMS.register("netherite_boots_jeweled", () ->
                new ModArmorItem(ModArmorMaterials.NETHERITE_JEWELED, ArmorItem.Type.BOOTS, new Item.Properties()));

    }

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}
