package de.joshizockt.cloud.core.main;

import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.IdManager;
import de.joshizockt.cloud.api.plugin.CloudPluginLoader;
import de.joshizockt.cloud.api.serverobject.BaseObject;
import de.joshizockt.cloud.core.commands.CoreHelpCommand;
import de.joshizockt.cloud.core.utils.CoreCommandManager;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.core.utils.CoreMessenger;
import de.joshizockt.cloud.core.utils.CoreServerManager;
import de.joshizockt.cloud.core.utils.MessengerType;

public class Core {

    private static CoreServerManager serverManager;
    private static CoreCommandManager commandManager;

    private static CloudConfig config;

    public static CoreMessenger messenger;

    private static CloudPluginLoader pluginLoader;

    public static void start(String[] args) throws Exception {

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

        Logger.log("CORE was successfull started.");


        IdManager.init();

        messenger = new CoreMessenger();

        pluginLoader = new CloudPluginLoader();

        commandManager.loadCommands();

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
