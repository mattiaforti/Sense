package it.thundyy.sense.commands;

import it.thundyy.sense.SenseLoader;
import it.thundyy.sense.commands.subcommands.CreateCommand;
import it.thundyy.sense.commands.subcommands.TeleportSubcommand;
import it.thundyy.sense.user.User;
import net.minestom.server.command.builder.Command;

public class IslandCommand extends Command {
    private final SenseLoader sense = SenseLoader.getInstance();
    
    public IslandCommand() {
        super("island", "is", "stoneblock");
        
        setCondition((sender, command) -> sender instanceof User);
        setDefaultExecutor((sender, context) -> {
            User player = (User) sender;
            help(player);
        });
        
        addSubcommand(new CreateCommand());
        addSubcommand(new TeleportSubcommand());
    }
    
    private void help(User player) {
        player.sendMessage("/is create");
        player.sendMessage("/is delete");
        player.sendMessage("/is teleport");
    }
    
}
