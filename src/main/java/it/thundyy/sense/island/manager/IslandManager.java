package it.thundyy.sense.island.manager;

import it.thundyy.sense.island.Island;
import it.thundyy.sense.island.provider.IslandProvider;
import it.thundyy.sense.user.User;

public class IslandManager {
    
    public void register(User player) {
        Island island = IslandProvider.createIsland(player);
        
        island.create().whenComplete((aVoid, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                return;
            }
            
            player.setInstance(island).thenAcceptAsync((aVoid1) -> {
                player.teleport(island.getSpawnPoint());
                player.sendMessage("Sei stato teletrasportato sull'isola");
            });
        });
    }
    
}
