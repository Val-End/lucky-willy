package net.ethernity.lucky.component.type;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Consumer;

public record LuckysComponent(int lucky) implements TooltipAppender {
    public static final Codec<LuckysComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(Codecs.UNSIGNED_BYTE
                    .optionalFieldOf("luckys", 1)
                    .forGetter(LuckysComponent::lucky)
            ).apply(instance, LuckysComponent::new)
    );

    public static final PacketCodec<ByteBuf, LuckysComponent> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.VAR_INT, LuckysComponent::lucky, LuckysComponent::new);

    public LuckysComponent(int lucky) {
        this.lucky = lucky;
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        if (this.lucky == 1)
            return;

        int realLucky = this.lucky - 1;
        tooltip.accept(
                Text.literal("")
                        .append(Text.literal(realLucky * 100 + "%").setStyle(Style.EMPTY.withColor(realLucky > 0 ? Formatting.GREEN : Formatting.RED)))
                        .append(Text.literal(" Lucky"))
        );
    }
}