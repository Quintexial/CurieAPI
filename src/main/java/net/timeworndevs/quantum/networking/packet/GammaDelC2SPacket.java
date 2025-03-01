package net.timeworndevs.quantum.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.timeworndevs.quantum.event.PlayerTickHandler;
import net.timeworndevs.quantum.util.IEntityDataSaver;
import net.timeworndevs.quantum.util.RadiationData;

import java.util.Objects;

public class GammaDelC2SPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ServerWorld world = ((ServerWorld) player.getWorld());


        int gamma = PlayerTickHandler.calculateRadiation(world, player, "gamma");
        if (gamma == 0) {
            RadiationData.delRad((IEntityDataSaver) player, "gamma", 1);

            RadiationData.syncRad(((IEntityDataSaver) player).getPersistentData().getInt("radiation.gamma"),
                    "gamma", player);
        }

    }
}