package it.thundyy.sense;

import com.google.gson.JsonParser;
import it.thundyy.sense.light.LightEngine;
import it.thundyy.sense.user.User;
import it.thundyy.sense.user.provider.UserPlayerProvider;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class SenseApplication {
    private static final String MOJANG_PROFILE_TEMPLATE = "https://api.mojang.com/users/profiles/minecraft/%s";
    
    public static void main(String[] args) {
        // Initialization
        MinecraftServer minecraftServer = MinecraftServer.init();
        MojangAuth.init();
        
        MinecraftServer.getConnectionManager().setPlayerProvider(new UserPlayerProvider());
        MinecraftServer.getConnectionManager().setUuidProvider((playerConnection, username) -> {
            String url = String.format(MOJANG_PROFILE_TEMPLATE, username);
            
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder()
                    .uri(java.net.URI.create(url))
                    .timeout(java.time.Duration.ofSeconds(10))
                    .build();
            var jsonElement = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> JsonParser.parseString(response.body()))
                    .join();
            var uuid = jsonElement.getAsJsonObject().get("id").getAsString();
            return UUID.fromString(uuid);
        });
        
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        // Create the instance
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();
        
        generateWorld(instanceContainer);
        
        SenseLoader senseLoader = SenseLoader.getInstance();
        senseLoader.loadListeners();
        senseLoader.loadCommands();
        // Add an event callback to specify the spawning instance (and the spawn position)
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 41, 0));
            
            player.scheduleNextTick((entity) -> LightEngine.recalculateInstance(instanceContainer));
        });
        
        // Start the server on port 25565
        
        minecraftServer.start("0.0.0.0", 25565);
    }
    
    private static void generateWorld(InstanceContainer instanceContainer) {
        // Set the ChunkGenerator
        instanceContainer.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 40, Block.DIRT);
        });
    }
}
