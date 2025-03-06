package net.ethernity.lucky.network.packet.s2c;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record SwitchCameraPayload(int target) implements CustomPayload {
    public static final Identifier SWITCH_CAMERA_PACKET_ID = Identifier.of(LuckyWilly.MOD_ID, "switch_camera");
    public static final Id<SwitchCameraPayload> ID = new Id<>(SWITCH_CAMERA_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, SwitchCameraPayload> CODEC = CustomPayload.codecOf(
            SwitchCameraPayload::write, SwitchCameraPayload::new
    );

    private SwitchCameraPayload(PacketByteBuf buf) {
        this(buf.readInt());
    }

    private void write(PacketByteBuf buf) {
        buf.writeInt(this.target);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
