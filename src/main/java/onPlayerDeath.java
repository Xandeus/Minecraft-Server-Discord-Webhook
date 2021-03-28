import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.Server;

import java.io.IOException;
import java.net.MalformedURLException;

public class onPlayerDeath implements Listener {
    final String url;
    final String message;

    public onPlayerDeath(String url, String message) {
        this.url = url;
        this.message = message;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Server s = Bukkit.getServer();
        DiscordWebhook wh = new DiscordWebhook(url);
        String messgage = String.format(message, event.getDeathMessage());
        wh.setContent(messgage);
        try {
            wh.execute();
            s.shutdown();
        } catch (MalformedURLException e) {
            System.out.println("[MinecraftDiscordWebhook] Invalid webhook URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
