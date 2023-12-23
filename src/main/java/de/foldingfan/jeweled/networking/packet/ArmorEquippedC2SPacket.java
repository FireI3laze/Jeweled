package de.foldingfan.jeweled.networking.packet;

import de.foldingfan.jeweled.common.capability.maxhealth.CustomPlayerDataProvider;
import de.foldingfan.jeweled.common.items.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ArmorEquippedC2SPacket {

    private final String equipmentArmorName;

    public ArmorEquippedC2SPacket(String equipmentArmorName) {
        this.equipmentArmorName = equipmentArmorName;
    }

    public ArmorEquippedC2SPacket(FriendlyByteBuf buf) {
        this.equipmentArmorName = buf.readUtf();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(equipmentArmorName);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

        NetworkEvent.Context context =  supplier.get();

        context.enqueueWork(() -> {


        });

        return true;
    }



}
