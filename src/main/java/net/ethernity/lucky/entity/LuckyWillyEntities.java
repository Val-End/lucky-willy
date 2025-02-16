package net.ethernity.lucky.entity;

import net.ethernity.lucky.LuckyWilly;
import net.ethernity.lucky.entity.sonic.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class LuckyWillyEntities {
    public static final EntityType<SonicEntity> SONIC = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "sonic"),
            EntityType.Builder.create(SonicEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("sonic")
    );

    public static final EntityType<TailsEntity> TAILS = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "tails"),
            EntityType.Builder.create(TailsEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("tails")
    );

    public static final EntityType<KnucklesEntity> KNUCKLES = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "knuckles"),
            EntityType.Builder.create(KnucklesEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("knuckles")
    );

    public static final EntityType<AmyEntity> AMY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "amy"),
            EntityType.Builder.create(AmyEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("amy")
    );

    public static final EntityType<SilverEntity> SILVER = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "silver"),
            EntityType.Builder.create(SilverEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("silver")
    );

    public static final EntityType<ShadowEntity> SHADOW = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "shadow"),
            EntityType.Builder.create(ShadowEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("shadow")
    );

    public static final EntityType<MetalSonicEntity> METAL_SONIC = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "sonic_robot"),
            EntityType.Builder.create(MetalSonicEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("sonic_robot")
    );

    public static final EntityType<EggmanEntity> EGGMAN = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(LuckyWilly.MOD_ID, "eggman"),
            EntityType.Builder.create(EggmanEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 2.1f).build("eggman")
    );

    public static void initialize() {
        LuckyWillyEntities.registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(SONIC, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(TAILS, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(KNUCKLES, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(AMY, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(SILVER, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(SHADOW, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(METAL_SONIC, SonicEntity.createSonicAttributes());
        FabricDefaultAttributeRegistry.register(EGGMAN, SonicEntity.createSonicAttributes());
    }
}
