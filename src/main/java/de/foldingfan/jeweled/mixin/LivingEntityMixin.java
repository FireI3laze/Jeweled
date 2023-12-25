package de.foldingfan.jeweled.mixin;

import de.foldingfan.jeweled.common.tags.ModTags;
import de.foldingfan.jeweled.networking.ModMessages;
import de.foldingfan.jeweled.networking.packet.ArmorEquippedC2SPacket;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "onEquipItem", at = @At(value = "HEAD"))
    private void onArmorEquip(EquipmentSlot slot, ItemStack oldItem, ItemStack newItem, CallbackInfo ci) {

        if (!slot.equals(EquipmentSlot.MAINHAND) && !slot.equals(EquipmentSlot.OFFHAND)) {

            if (!newItem.isEmpty() && newItem.is(ModTags.Items.JEWELED_NETHERITE_ARMOR)) {

                ModMessages.sendToServer(new ArmorEquippedC2SPacket(newItem.getDisplayName().getString()));

            }

        }

    }


}
