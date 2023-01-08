package it.thundyy.sense.island.entity;

import it.thundyy.sense.island.Island;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.entity.fakeplayer.FakePlayerOption;

import java.util.UUID;

public class IslandNPC extends FakePlayer {
    private final Island island;
    
    public IslandNPC(Island island) {
        super(
                UUID.randomUUID(),
                "Island NPC",
                new FakePlayerOption()
                        .setInTabList(false)
                        .setRegistered(false),
                player -> {
                    Pos spawnPoint = island.getSpawnPoint();
                    player.setBoundingBox(0, 0, 0);
                    
                    player.setInstance(island).thenAccept((aVoid) -> {
                        player.teleport(spawnPoint);
                    });
                }
        );
        
        this.island = island;
    }
    
    @Override
    public void spawn() {
    
    }
}
