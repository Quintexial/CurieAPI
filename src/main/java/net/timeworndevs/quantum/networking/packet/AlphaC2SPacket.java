package net.timeworndevs.quantum.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.timeworndevs.quantum.event.PlayerTickHandler;
import net.timeworndevs.quantum.util.Clamp;
import net.timeworndevs.quantum.util.IEntityDataSaver;
import net.timeworndevs.quantum.util.RadiationData;

public class AlphaC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {


        int radiationComputed = PlayerTickHandler.calculateRadiation((ServerWorld) player.getWorld(), player, "alpha");

        double radiationDivision = PlayerTickHandler.calculateDivision(player, "alpha");


        RadiationData.addRad((IEntityDataSaver) player, "alpha", (int) Clamp.clamp(radiationComputed / radiationDivision, 1, 100));

        RadiationData.syncRad(((IEntityDataSaver) player).getPersistentData().getInt("radiation.alpha"),
                "alpha", player);


    }
}
