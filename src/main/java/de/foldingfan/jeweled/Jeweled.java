package de.foldingfan.jeweled;

import com.mojang.logging.LogUtils;
import de.foldingfan.jeweled.common.blocks.ModBlocks;
import de.foldingfan.jeweled.common.effects.ModEffects;
import de.foldingfan.jeweled.common.items.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Jeweled.ID)
public class Jeweled {
    public static final String ID = "jeweled";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Jeweled() {

        ModItems.register();
        ModBlocks.register();
        ModEffects.register();

        MinecraftForge.EVENT_BUS.register(this);

    }

}
