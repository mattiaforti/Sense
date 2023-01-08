package it.thundyy.sense.user;

import it.thundyy.sense.island.Island;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
public class User extends Player {
    private Island island;
    private volatile int kills = 0;
    private int deaths = 0;
    private int level = 1;
    private int xp = 0;
    
    public User(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }
    
    public synchronized void addKill() {
        kills++;
    }
    
    public void addDeath() {
        deaths++;
    }
    
    public void addXp(int xp) {
        this.xp += xp;
    }
    
    public void addLevel() {
        level++;
    }
    
    public void removeXp(int xp) {
        this.xp -= xp;
    }
    
    public void removeLevel() {
        level--;
    }
    
    public void teleportToIsland() {
        Instance currentInstance = getInstance();
        if (island == null ||
                currentInstance == null ||
                island.getUniqueId().equals(currentInstance.getUniqueId())) {
            return;
        }
        
        setInstance(island).thenAccept((aVoid) -> {
            teleport(island.getSpawnPoint());
            sendMessage("You have been teleported to your island!");
        });
    }
}
