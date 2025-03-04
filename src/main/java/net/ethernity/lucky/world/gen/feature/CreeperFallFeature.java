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

public class CreeperFallFeature extends Feature<DefaultFeatureConfig> {
    private final BlockState floor = Blocks.HONEY_BLOCK.getDefaultState();
    private final BlockState air = Blocks.AIR.getDefaultState();

    public CreeperFallFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();

        int honeY = 20;
        while (structureWorldAccess.isOutOfHeightLimit(blockPos.getY() - honeY)) {
            honeY--;
        }

        for (int yPos = 0; yPos >= -honeY; yPos--) {
            for (int xPos = -1; xPos <= 1; xPos++) {
                for (int zPos = -1; zPos <= 1; zPos++) {
                    BlockState block = air;
                    if (yPos == -honeY)
                        block = floor;

                    structureWorldAccess.setBlockState(blockPos.add(xPos, yPos, zPos), block, Block.NOTIFY_LISTENERS);
                }
            }
        }

        return true;
    }
}
