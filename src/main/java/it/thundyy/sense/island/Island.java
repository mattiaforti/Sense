package it.thundyy.sense.island;

import it.thundyy.sense.island.entity.IslandNPC;
import it.thundyy.sense.island.generator.IslandGenerator;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public class Island extends InstanceContainer {
    private final List<UUID> members = new ArrayList<>();
    private UUID owner;
    private int borderRadius;
    private int level;
    private Pos spawnPoint = new Pos(2.5, 21, 2.5);
    private IslandNPC islandNPC;
    private boolean isLocked = true;
    
    protected Island(@NotNull UUID uniqueId, @NotNull DimensionType dimensionType) {
        super(uniqueId, dimensionType);
    }
    
    public void transferOwnership(@NotNull UUID newOwner) {
        this.owner = newOwner;
    }
    
    public void addMember(UUID member) {
        members.add(member);
    }
    
    public CompletableFuture<Void> create() {
        return CompletableFuture.runAsync(() -> {
            IslandGenerator generator = new IslandGenerator(this);
            setGenerator(generator);
        });
    }
}
