package it.thundyy.sense.island.registry;

import it.thundyy.sense.island.Island;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class IslandRegistry {
    @Getter
    private static final IslandRegistry instance = new IslandRegistry();
    private static final Map<UUID, Island> ISLANDS = new HashMap<>();
    
    private IslandRegistry() {
    }
    
    public void addIsland(Island island) {
        ISLANDS.put(island.getOwner(), island);
    }
    
    public void removeIsland(Island island) {
        ISLANDS.remove(island.getOwner());
    }
    
    public void forEachIsland(Consumer<Island> consumer) {
        ISLANDS.values().forEach(consumer);
    }
}
