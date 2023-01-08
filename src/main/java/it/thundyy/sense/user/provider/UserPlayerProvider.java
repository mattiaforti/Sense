package it.thundyy.sense.user.provider;

import it.thundyy.sense.user.User;
import it.thundyy.sense.user.registry.UserRegistry;
import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class UserPlayerProvider implements PlayerProvider {
    private final UserRegistry registry = UserRegistry.getInstance();
    
    @Override
    public @NotNull Player createPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection connection) {
        User toCreate = new User(uuid, username, connection);
        if (!registry.hasUser(uuid)) {
            return toCreate;
        }
        
        User user = registry.getUser(uuid);
        
        toCreate.setIsland(user.getIsland());
        return toCreate;
    }
}
