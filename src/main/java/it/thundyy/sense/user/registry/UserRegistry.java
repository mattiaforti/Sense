package it.thundyy.sense.user.registry;

import it.thundyy.sense.user.User;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class UserRegistry {
    @Getter
    private static final UserRegistry instance = new UserRegistry();
    private static final Map<UUID, User> USERS = new HashMap<>();
    
    private UserRegistry() {
    }
    
    public void addUser(User user) {
        if (USERS.containsKey(user.getUuid())) return;
        USERS.put(user.getUuid(), user);
    }
    
    public void removeUser(User user) {
        USERS.remove(user.getUuid());
    }
    
    public boolean hasUser(UUID uuid) {
        return USERS.containsKey(uuid);
    }
    
    public User getUser(UUID uuid) {
        return USERS.get(uuid);
    }
    
}
