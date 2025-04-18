package net.ethernity.lucky.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;

public class JailGenerator {
    private static final BlockState ironBars = Blocks.IRON_BARS.getDefaultState();
    private static final BlockState floor = Blocks.STONE_BRICKS.getDefaultState();
    private static final BlockState air = Blocks.AIR.getDefaultState();

    protected static void generate(StructureWorldAccess structureWorldAccess, BlockPos blockPos) {
        for (int yPos = -1; yPos <= 3; yPos++) {
            for (int xPos = -1; xPos <= 1; xPos++) {
                for (int zPos = -1; zPos <= 1; zPos++) {
                    BlockState block = air;
                    if (yPos == -1)
                        block = floor;
                    else if (!(xPos == 0 && zPos == 0))
                        block = ironBars;

                    structureWorldAccess.setBlockState(blockPos.add(xPos, yPos, zPos), block, Block.NOTIFY_LISTENERS);
                }
            }
        }
    }
}
