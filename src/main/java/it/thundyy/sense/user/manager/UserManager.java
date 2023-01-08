package it.thundyy.sense.user.manager;

import it.thundyy.sense.island.Island;
import it.thundyy.sense.user.User;

public class UserManager {
    
    public void reset(User user) {
        user.sendMessage("You have been reset!");
        
        user.setKills(0);
        user.setDeaths(0);
        user.setLevel(1);
        user.setXp(0);
    }
    
    public void changeIsland(User user, Island island) {
        if (user.getIsland() != null) {
            user.sendMessage("You already have an island!");
            return;
        }
        
        user.setIsland(island);
        user.sendMessage("You have been moved to a new island!");
        user.teleportToIsland();
    }
}
