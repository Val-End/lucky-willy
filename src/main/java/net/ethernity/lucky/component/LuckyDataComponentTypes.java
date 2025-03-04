package net.ethernity.lucky.component;

import net.ethernity.lucky.component.type.LuckysComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public class LuckyDataComponentTypes {
    public static final ComponentType<LuckysComponent> LUCKYS = register(
            "luckys_component", builder -> builder.codec(LuckysComponent.CODEC).packetCodec(LuckysComponent.PACKET_CODEC).cache()
    );

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, id, builderOperator.apply(ComponentType.builder()).build());
    }

    public static void initialize() {
    }
}
