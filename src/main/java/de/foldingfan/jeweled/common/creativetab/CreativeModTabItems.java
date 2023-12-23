package de.foldingfan.jeweled.common.creativetab;

import de.foldingfan.jeweled.Jeweled;
import de.foldingfan.jeweled.common.blocks.ModBlocks;
import de.foldingfan.jeweled.common.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModTabItems {
    public CreativeModTabItems() {
        // NO OP
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Jeweled.ID);

    public static void register(RegisterEvent.RegisterHelper<CreativeModeTab> helper) {
        helper.register("general", CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.creative_tab"))
                .icon(() -> new ItemStack(ModItems.NETHERITE_HELMET_JEWELED.get()))
                .displayItems((params, output) -> CreativeModTabItems.append(output))
                .build());

        CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());

    }

    public static void append(CreativeModeTab.Output output) {

        add(output, ModItems.ZIRCON);
        add(output, ModItems.ZIRCON_JEWEL);
        add(output, ModItems.RAW_ZIRCON);
        add(output, ModItems.JEWEL_SLOT);
        add(output, ModItems.NETHERITE_HELMET_EMPTY);
        add(output, ModItems.NETHERITE_CHESTPLATE_EMPTY);
        add(output, ModItems.NETHERITE_LEGGING_EMPTY);
        add(output, ModItems.NETHERITE_BOOTS_EMPTY);
        add(output, ModItems.NETHERITE_HELMET_JEWELED);
        add(output, ModItems.NETHERITE_CHESTPLATE_JEWELED);
        add(output, ModItems.NETHERITE_LEGGING_JEWELED);
        add(output, ModItems.NETHERITE_BOOTS_JEWELED);

        addBlock(output, ModBlocks.ZIRCON_ORE);
        addBlock(output, ModBlocks.DEEPSLATE_ZIRCON_ORE);
        addBlock(output, ModBlocks.ZIRCON_BLOCK);
        addBlock(output, ModBlocks.SPEED_BLOCK);

    }

    private static void add(CreativeModeTab.Output output, RegistryObject<? extends Item> registryObject) {
        output.accept(registryObject.get());
    }
    private static void addBlock(CreativeModeTab.Output output, RegistryObject<? extends Block> registryObject) {
        output.accept(registryObject.get());
    }

}
