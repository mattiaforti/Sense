package it.thundyy.sense.listeners.impl;

import it.thundyy.sense.SenseLoader;
import it.thundyy.sense.listeners.Listener;
import it.thundyy.sense.listeners.annotations.Listen;
import it.thundyy.sense.user.User;
import it.thundyy.sense.user.registry.UserRegistry;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.event.player.AsyncPlayerPreLoginEvent;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {
    private final SenseLoader sense = SenseLoader.getInstance();
    
    @Listen
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getPlayer() instanceof FakePlayer) return;
        
        User user = (User) event.getPlayer();
    }
    
    @Listen
    public void onPlayerJoin(PlayerLoginEvent event) {
        if (event.getPlayer() instanceof FakePlayer) return;
        User player = (User) event.getPlayer();
        
        UserRegistry.getInstance().addUser(player);
        System.out.println(player.getUuid());
        player.sendMessage("Benvenuto nella tua StoneBlock!");
    }
    
    @Listen
    public void onDisconnect(PlayerDisconnectEvent event) {
        if (event.getPlayer() instanceof FakePlayer) return;
        User player = (User) event.getPlayer();
        
        player.getPlayerConnection().disconnect();
    }
}
