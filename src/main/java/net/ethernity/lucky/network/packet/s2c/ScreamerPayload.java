package net.ethernity.lucky.network.packet.s2c;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ScreamerPayload() implements CustomPayload {
    public static final Identifier SCREAMER_PACKET_ID = Identifier.of(LuckyWilly.MOD_ID, "screamer");
    public static final CustomPayload.Id<ScreamerPayload> ID = new CustomPayload.Id<>(SCREAMER_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, ScreamerPayload> CODEC = CustomPayload.codecOf(
            ScreamerPayload::write, ScreamerPayload::new
    );

    private ScreamerPayload(PacketByteBuf buf) {
        this();
    }

    private void write(PacketByteBuf buf) {
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
