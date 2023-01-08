package it.thundyy.sense;

import it.thundyy.sense.commands.IslandCommand;
import it.thundyy.sense.island.manager.IslandManager;
import it.thundyy.sense.listeners.ListenerLoader;
import it.thundyy.sense.user.manager.UserManager;
import lombok.Getter;
import net.minestom.server.MinecraftServer;

@Getter
public class SenseLoader {
    @Getter
    private static final SenseLoader instance = new SenseLoader();
    private final UserManager userManager;
    private final IslandManager islandManager;
    private final ListenerLoader listenerLoader;
    
    private SenseLoader() {
        userManager = new UserManager();
        listenerLoader = new ListenerLoader(MinecraftServer.getGlobalEventHandler());
        islandManager = new IslandManager();
    }
    
    public void loadListeners() {
        listenerLoader.boot();
    }
    
    public void loadCommands() {
        MinecraftServer.getCommandManager().register(new IslandCommand());
    }
}
