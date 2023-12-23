package de.foldingfan.jeweled.common.blocks;

import de.foldingfan.jeweled.Jeweled;
import de.foldingfan.jeweled.common.items.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Jeweled.ID);

    public static RegistryObject<Block> ZIRCON_BLOCK;
    public static RegistryObject<Block> ZIRCON_ORE;
    public static RegistryObject<Block> DEEPSLATE_ZIRCON_ORE;

    public static RegistryObject<Block> SPEED_BLOCK;

    static {

        ZIRCON_BLOCK = registerBlock("zircon_block", () ->
                new Block(BlockBehaviour.Properties.of().strength(6f).requiresCorrectToolForDrops()));
        ZIRCON_ORE = registerBlock("zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().
                strength(6f).requiresCorrectToolForDrops(), UniformInt.of(5, 8)));
        DEEPSLATE_ZIRCON_ORE  = registerBlock("deepslate_zircon_ore", () ->
                new DropExperienceBlock(BlockBehaviour.Properties.of().strength(6f).requiresCorrectToolForDrops(),
                        UniformInt.of(5, 8)));

        SPEED_BLOCK  = registerBlock("speed_block", () ->
                new SpeedBlock(BlockBehaviour.Properties.of().strength(6f).requiresCorrectToolForDrops()));

    }

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
