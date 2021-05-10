package de.joshizockt.cloud.core.main;

import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.IdManager;
import de.joshizockt.cloud.api.event.CloudEventManager;
import de.joshizockt.cloud.api.event.server.CloudServerStartEvent;
import de.joshizockt.cloud.api.plugin.CloudPluginLoader;
import de.joshizockt.cloud.api.serverobject.BaseObject;
import de.joshizockt.cloud.core.commands.CoreHelpCommand;
import de.joshizockt.cloud.core.commands.KeyAddCommand;
import de.joshizockt.cloud.core.utils.CoreCommandManager;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.core.utils.CoreMessenger;
import de.joshizockt.cloud.core.utils.CoreServerManager;
import de.joshizockt.cloud.networking.NetworkManager;
import de.joshizockt.cloud.networking.NetworkType;

public class Core {

    private static Core instance;

    private static CoreServerManager serverManager;
    private static CoreCommandManager commandManager;

    private static CloudConfig config;

    public static CoreMessenger messenger;

    private static CloudPluginLoader pluginLoader;

    private static NetworkManager networkManager;

    public Core(String[] args) throws Exception {

        instance = this;

        Logger.log("Starting CORE");

        config = new CloudConfig("config.json", "core", true);

        if(!config.fileExists() || !config.valueExists("Port")) {
            Logger.err("Error while loading Core config.");
            System.exit(0);
        }

        int port = config.getInteger("Port");

        commandManager = new CoreCommandManager();
        serverManager = new CoreServerManager();

        commandManager.addCommand(new CoreHelpCommand());
        commandManager.addCommand(new KeyAddCommand());

        Logger.log("CORE was successfull started.");

        IdManager.init();

        messenger = new CoreMessenger();

        pluginLoader = new CloudPluginLoader();

        networkManager = new NetworkManager(NetworkType.CORE_SERVER);

        networkManager.startServer(port);

        commandManager.loadCommands();

    }

    public static Core getInstance() {
        return instance;
    }

    public static CoreCommandManager getCommandManager() {
        return commandManager;
    }

    public static CoreServerManager getServerManager() {
        return serverManager;
    }

    public static CloudConfig getConfig() {
        return config;
    }

}
