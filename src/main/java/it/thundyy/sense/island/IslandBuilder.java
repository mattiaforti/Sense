package it.thundyy.sense.island;

import java.util.UUID;

public sealed interface IslandBuilder permits IslandBuilderImpl {
    
    static IslandBuilderImpl builder() {
        return new IslandBuilderImpl();
    }
    
    IslandBuilderImpl owner(UUID owner);
    
    IslandBuilderImpl level(int level);
    
    IslandBuilderImpl borderRadius(int borderRadius);
    
    Island build();
}
