package it.thundyy.sense.commands.subcommands;

import it.thundyy.sense.SenseLoader;
import it.thundyy.sense.island.Island;
import it.thundyy.sense.user.User;
import net.minestom.server.command.builder.Command;
import org.jetbrains.annotations.NotNull;

public class TeleportSubcommand extends Command {
    
    public TeleportSubcommand() {
        super("teleport", "tp", "go");
        
        setCondition((sender, command) -> sender instanceof User);
        setDefaultExecutor((sender, context) -> {
            User player = (User) sender;
            
            teleport(player);
        });
    }
    
    private void teleport(@NotNull User player) {
        if (player.getIsland() == null) {
            player.sendMessage("You don't have an island!");
            player.sendMessage("Use /island create to create one.");
            return;
        }
        
        player.sendMessage("Teleporting...");
        player.teleportToIsland();
    }
}
