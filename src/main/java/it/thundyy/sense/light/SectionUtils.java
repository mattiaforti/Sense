package it.thundyy.sense.light;

import net.minestom.server.instance.block.Block;

public class SectionUtils {
    private final static int DIMENSION = 16;
    
    public static int getCoordinatesIndex(int x, int y, int z) {
        return y << (DIMENSION / 2) | z << (DIMENSION / 4) | x;
    }
    
    public static boolean lightCanPassThrough(Block block) {
        return block == null || !block.isSolid() || block.isAir() || block.compare(Block.BARRIER);
    }
}
