package it.thundyy.sense.island.provider;

import it.thundyy.sense.island.Island;
import it.thundyy.sense.island.IslandBuilder;
import it.thundyy.sense.island.registry.IslandRegistry;
import it.thundyy.sense.user.User;
import net.minestom.server.MinecraftServer;

public class IslandProvider {
    private static final int DEFAULT_BORDER_RADIUS = 5;
    private static final int DEFAULT_LEVEL = 1;
    private static final IslandRegistry registry = IslandRegistry.getInstance();
    
    public static Island createIsland(User player) {
        Island island = IslandBuilder.builder()
                .owner(player.getUuid())
                .borderRadius(DEFAULT_BORDER_RADIUS)
                .level(DEFAULT_LEVEL)
                .build();
        
        registry.addIsland(island);
        MinecraftServer.getInstanceManager().registerInstance(island);
        player.setIsland(island);
        return island;
    }
    
    /*public boolean hasIsland(User user) {
        
        return user.getIsland() != null;
    }*/
}
