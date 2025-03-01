package net.ethernity.lucky.entity;

import net.ethernity.lucky.LuckyWilly;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWillyEntities {
    public static final EntityType<TrotumanEntity> TROTUMAN = register(
            "trotuman", EntityType.Builder.create(TrotumanEntity::new, SpawnGroup.AMBIENT)
                    .dimensions(0.6F, 1.8F)
                    .eyeHeight(1.62F)
                    .maxTrackingRange(32)
                    .trackingTickInterval(2)
    );

    public static final EntityType<PaintingEntity> PAINTING = register(
            "painting", EntityType.Builder.create(PaintingEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.8F, 0.3F)
    );



    private static EntityType register(String id, EntityType.Builder builder) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(LuckyWilly.MOD_ID, id), builder.build(id));
    }

    public static void initialize() {
        LuckyWillyEntities.registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(TROTUMAN, TrotumanEntity.createTrotumanAttributes());
        FabricDefaultAttributeRegistry.register(PAINTING, PaintingEntity.createAttributes());
    }
}
