package de.foldingfan.jeweled.networking;

import de.foldingfan.jeweled.Jeweled;
import de.foldingfan.jeweled.networking.packet.ArmorEquippedC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel channel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Jeweled.ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = channel;

        channel.messageBuilder(ArmorEquippedC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ArmorEquippedC2SPacket::new)
                .encoder(ArmorEquippedC2SPacket::toBytes)
                .consumerMainThread(ArmorEquippedC2SPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

}

