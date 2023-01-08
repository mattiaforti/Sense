package it.thundyy.sense.commands.subcommands;

import it.thundyy.sense.SenseLoader;
import it.thundyy.sense.user.User;
import net.minestom.server.command.builder.Command;
import org.jetbrains.annotations.NotNull;

public class CreateCommand extends Command {
    private final SenseLoader sense = SenseLoader.getInstance();
    
    public CreateCommand() {
        super("create");
        
        setCondition((sender, command) -> sender instanceof User);
        setDefaultExecutor((sender, context) -> {
            User player = (User) sender;
            
            createIsland(player);
        });
    }
    
    private void alreadyHasIsland(@NotNull User player) {
        player.sendMessage("You already have an island!");
        player.sendMessage("Use /island delete to delete it.");
        player.sendMessage("Use /island teleport to teleport to it.");
    }
    
    private void createIsland(@NotNull User player) {
        if (player.getIsland() != null) {
            alreadyHasIsland(player);
            return;
        }
        
        player.sendMessage("Creating island...");
        sense.getIslandManager().register(player);
        ;
    }
}
