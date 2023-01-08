package it.thundyy.sense.island;

import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;

import java.util.UUID;

public final class IslandBuilderImpl implements IslandBuilder {
    private UUID owner;
    private int level;
    private int borderRadius;
    
    @Override
    public IslandBuilderImpl owner(UUID owner) {
        this.owner = owner;
        return this;
    }
    
    @Override
    public IslandBuilderImpl level(int level) {
        this.level = level;
        return this;
    }
    
    @Override
    public IslandBuilderImpl borderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
        return this;
    }
    
    @Override
    public Island build() {
        Island island = new Island(owner,
                DimensionType.OVERWORLD);
        
        island.setLevel(level);
        island.setBorderRadius(borderRadius);
        return island;
    }
}
