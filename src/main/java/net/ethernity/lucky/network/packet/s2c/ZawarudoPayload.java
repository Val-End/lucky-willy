package net.ethernity.lucky.network.packet.s2c;

import net.ethernity.lucky.LuckyWilly;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record ZawarudoPayload(Boolean active) implements CustomPayload {
    public static final Identifier ZAWARUDO_PACKET_ID = Identifier.of(LuckyWilly.MOD_ID, "zawarudo");
    public static final Id<ZawarudoPayload> ID = new Id<>(ZAWARUDO_PACKET_ID);
    public static final PacketCodec<RegistryByteBuf, ZawarudoPayload> CODEC = CustomPayload.codecOf(
            ZawarudoPayload::write, ZawarudoPayload::new
    );

    private ZawarudoPayload(PacketByteBuf buf) {
        this(buf.readBoolean());
    }

    private void write(PacketByteBuf buf) {
        buf.writeBoolean(this.active);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}