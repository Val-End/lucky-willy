package net.ethernity.lucky.world.gen.feature;

import com.mojang.serialization.Codec;
import net.ethernity.lucky.block.LuckyWilllyBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FourLuckyPyramidFeature extends Feature<DefaultFeatureConfig> {
    private final BlockState wall = Blocks.SANDSTONE.getDefaultState();
    private final BlockState lucky = LuckyWilllyBlocks.LUCKY_BLOCK.getDefaultState();
    private final BlockState gold = Blocks.GOLD_BLOCK.getDefaultState();
    private final BlockState air = Blocks.AIR.getDefaultState();

    public FourLuckyPyramidFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();

        for (int yPos = -1; yPos <= 2; yPos++) {
            for (int xPos = -2; xPos <= 2; xPos++) {
                for (int zPos = -2; zPos <= 2; zPos++) {
                    int absX = Math.abs(xPos);
                    int absZ = Math.abs(zPos);

                    BlockState block = this.air;
                    if (yPos == -1 || (yPos < 2 && (absX == 2 || absZ == 2)))
                        block = this.wall;
                    else if (yPos == 0 && absX == 1 && absZ == 1)
                        block = this.lucky;
                    else if (yPos == 2 && absX == 2 && absZ == 2)
                        block = this.gold;

                    structureWorldAccess.setBlockState(blockPos.add(xPos, yPos, zPos), block, Block.NOTIFY_LISTENERS);
                }
            }
        }

        return true;
    }
}
