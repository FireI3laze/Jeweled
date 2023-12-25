package de.foldingfan.jeweled.common.tags;

import de.foldingfan.jeweled.Jeweled;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> JEWELED_NETHERITE_ARMOR = tag("jeweled_netherite_armor");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(Jeweled.ID, name));
        }

    }

}
