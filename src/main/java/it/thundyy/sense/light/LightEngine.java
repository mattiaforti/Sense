package it.thundyy.sense.light;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.Section;
import net.minestom.server.instance.block.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LightEngine {
    
    private static final byte FULLBRIGHT = 15; // 14
    private static final byte DARKNESS   = 7; // 7
    private static final int  ARRAY_SIZE = 16 * 16 * 16 / (8 / 4); // blocks / bytes per block
    
    private static byte[] recalculatedArray;
    
    public static void recalculateInstance(Instance instance) {
        List<Chunk> chunks = instance.getChunks().stream().toList();
        chunks.forEach((chunk -> {
            boolean[][] exposed = new boolean[16][16];
            for (boolean[] booleans : exposed) {
                Arrays.fill(booleans, true);
            }
            List<Section> sections = new ArrayList<>(chunk.getSections());
            Collections.reverse(sections);
            for (Section section : sections) {
                recalculatedArray = new byte[ARRAY_SIZE];
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 15; y > -1; y--) {
                            Block block = Block.fromStateId((short) section.blockPalette().get(x, y, z));
                            if (!SectionUtils.lightCanPassThrough(block)) exposed[x][z] = false;
                            if (exposed[x][z]) {
                                set(SectionUtils.getCoordinatesIndex(x, y, z), FULLBRIGHT);
                            } else {
                                set(SectionUtils.getCoordinatesIndex(x, y, z), DARKNESS);
                            }
                        }
                    }
                }
                section.setSkyLight(recalculatedArray);
                section.setBlockLight(recalculatedArray);
            }
            chunk.setBlock(1, 1, 1, chunk.getBlock(1, 1, 1));
        }));
    }
    
    public static void set(final int index, final int value) {
        final int shift = (index & 1) << 2;
        final int i = index >>> 1;
        
        recalculatedArray[i] = (byte) ((recalculatedArray[i] & (0xF0 >>> shift)) | (value << shift));
    }
}
