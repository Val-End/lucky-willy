package net.ethernity.lucky.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class LavaTrapFeature extends Feature<DefaultFeatureConfig> {
    private final BlockState lava = Blocks.LAVA.getDefaultState();

    public LavaTrapFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        JailGenerator.generate(structureWorldAccess, blockPos);
        structureWorldAccess.setBlockState(blockPos.add(0, 3, 0), this.lava, Block.NOTIFY_LISTENERS);
        return true;
    }
}
