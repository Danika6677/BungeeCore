package hu.minehun.bungeecore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import hu.minehun.bungeecore.commands.LobbyCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BungeeCore extends Plugin {
    public static BungeeCore instance;

    public Configuration configuration;

    public static BungeeCore getInstance() {
        return instance;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public void onEnable() {
        instance = this;
        loadConfig();
        registerCommands();
        ProxyServer.getInstance().getConsole().sendMessage("§8§m-------------------------------------------------------------");
        ProxyServer.getInstance().getConsole().sendMessage("§a"+ BungeeCore.getInstance().getDescription().getName() + " §7bekapcsolt!");
        ProxyServer.getInstance().getConsole().sendMessage("§7Készítő: §eDanika6677");
        ProxyServer.getInstance().getConsole().sendMessage("§7Verzió: §e" + BungeeCore.getInstance().getDescription().getVersion());
        ProxyServer.getInstance().getConsole().sendMessage("§7BungeeCord: " + ChatColor.YELLOW + ProxyServer.getInstance().getName() +"§8-§a"+ ProxyServer.getInstance().getVersion());
        ProxyServer.getInstance().getConsole().sendMessage("§8&m-------------------------------------------------------------");
    }

    public void onDisable() {
        ProxyServer.getInstance().getConsole().sendMessage("§8§m-------------------------------------------------------------");
        ProxyServer.getInstance().getConsole().sendMessage("§a"+ BungeeCore.getInstance().getDescription().getName() + " §7kikapcsolt!");
        ProxyServer.getInstance().getConsole().sendMessage("§7Készítő: §eDanika6677");
        ProxyServer.getInstance().getConsole().sendMessage("§7Verzió: §e" + BungeeCore.getInstance().getDescription().getVersion());
        ProxyServer.getInstance().getConsole().sendMessage("§7BungeeCord: " + ChatColor.YELLOW + ProxyServer.getInstance().getName() +"§8-§a"+ ProxyServer.getInstance().getVersion());
        ProxyServer.getInstance().getConsole().sendMessage("§8&m-------------------------------------------------------------");
        saveConfig();
    }

    public void loadConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath(), new java.nio.file.CopyOption[0]);
            } catch (IOException e) {
                e.printStackTrace();
                String error1 = ChatColor.GRAY + "Hiba: "+ ChatColor.GREEN + e;
                ProxyServer.getInstance().getConsole().sendMessage("§8-------------------------------------------------------------");
				ProxyServer.getInstance().getConsole().sendMessage("§a"+ BungeeCore.getInstance().getDescription().getName() + " §7kikapcsolt!");
				ProxyServer.getInstance().getConsole().sendMessage("§7Készítő: §eDanika6677");
				ProxyServer.getInstance().getConsole().sendMessage("§7Verzió: §e" + BungeeCore.getInstance().getDescription().getVersion());
                ProxyServer.getInstance().getConsole().sendMessage(" ");
                ProxyServer.getInstance().getConsole().sendMessage("§7Hiba a config betöltésében!");
                ProxyServer.getInstance().getConsole().sendMessage(error1);
                ProxyServer.getInstance().getConsole().sendMessage("§8-------------------------------------------------------------");
            }
        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            ex.printStackTrace();
            String error2 = ChatColor.GRAY + "Hiba: "+ ChatColor.GREEN + ex;
			ProxyServer.getInstance().getConsole().sendMessage("§a"+ BungeeCore.getInstance().getDescription().getName() + " §7kikapcsolt!");
			ProxyServer.getInstance().getConsole().sendMessage("§7Készítő: §eDanika6677");
			ProxyServer.getInstance().getConsole().sendMessage("§7Verzió: §e" + BungeeCore.getInstance().getDescription().getVersion());
            ProxyServer.getInstance().getConsole().sendMessage(" ");
            ProxyServer.getInstance().getConsole().sendMessage("§7Hiba a config classok betöltésében!");
            ProxyServer.getInstance().getConsole().sendMessage(error2);
            ProxyServer.getInstance().getConsole().sendMessage("§8-------------------------------------------------------------");
        }
    }

    public void saveConfig() {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, new File(getDataFolder(), "config.yml"));
        } catch (IOException exc) {
            exc.printStackTrace();
            String error3 = ChatColor.GRAY + "Hiba: "+ ChatColor.GREEN + exc;
            ProxyServer.getInstance().getConsole().sendMessage("§8-------------------------------------------------------------");
			ProxyServer.getInstance().getConsole().sendMessage("§a"+ BungeeCore.getInstance().getDescription().getName() + " §7kikapcsolt!");
			ProxyServer.getInstance().getConsole().sendMessage("§7Készítő: §eDanika6677");
			ProxyServer.getInstance().getConsole().sendMessage("§7Verzió: §e" + BungeeCore.getInstance().getDescription().getVersion());
            ProxyServer.getInstance().getConsole().sendMessage(" ");
            ProxyServer.getInstance().getConsole().sendMessage("§7Hiba a config betöltésében!");
            ProxyServer.getInstance().getConsole().sendMessage(error3);
            ProxyServer.getInstance().getConsole().sendMessage("§8-------------------------------------------------------------");
        }
    }

    private void registerCommands() {
        new LobbyCommand();
    }
}

