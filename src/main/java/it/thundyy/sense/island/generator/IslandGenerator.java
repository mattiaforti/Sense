package it.thundyy.sense.island.generator;

import it.thundyy.sense.island.Island;
import lombok.RequiredArgsConstructor;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import net.minestom.server.instance.generator.UnitModifier;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class IslandGenerator implements Generator {
    private final Island island;
    
    
    
    @Override
    public void generate(@NotNull GenerationUnit unit) {
        UnitModifier modifier = unit.modifier();
        modifier.fillHeight(0, 40, Block.STONE);
    
        unit.fork(setter -> {
            // cube of air
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    for (int z = 0; z < 5; z++) {
                        setter.setBlock(x, y + 20, z, Block.AIR); // 0 20 0
                    }
                }
            }
            
            island.setLocked(false);
        });
    }
    
}
