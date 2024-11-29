package dev.ikx.rt.impl.internal.network;

import dev.ikx.rt.impl.internal.capability.CapabilityRegistryHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class NetworkManager {

    public static void registerFTBUltimineTag() {
        FTBUltimineTag.CHANNEL.register(FTBUltimineTag.class);
    }

    public static class FTBUltimineTag {

        private static final String NAME = "FTB_ULTIMINE_TAG";
        private static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);

        public static void sendClientCustomPacket(EntityPlayer player) {
            PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
            CapabilityRegistryHandler.FTBUltimineTag capability = player.getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null);
            buffer.writeBoolean(Objects.requireNonNull(capability).isAllow());

            CHANNEL.sendTo(new FMLProxyPacket(buffer, NAME), ((EntityPlayerMP) player));
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public static void onClientCustomPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
            ByteBuf buf = event.getPacket().payload();
            boolean allow = buf.readBoolean();

            Minecraft minecraft = Minecraft.getMinecraft();
            minecraft.addScheduledTask(() -> {
                EntityPlayer player = minecraft.player;
                Objects.requireNonNull(player.getCapability(CapabilityRegistryHandler.FTB_ULTIMINE_CAPABILITY, null)).setAllow(allow);
            });
        }

    }

}
