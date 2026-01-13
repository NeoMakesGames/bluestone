package hytale.server.api.plugin;

import hytale.server.api.Server;
import java.util.logging.Logger;

/**
 * Base class for Hytale plugins.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public abstract class JavaPlugin {

    private Logger logger;
    private Server server;

    public JavaPlugin() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Called when the plugin is enabled.
     */
    public abstract void onEnable();

    /**
     * Called when the plugin is disabled.
     */
    public abstract void onDisable();

    /**
     * Get the plugin's logger.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Get the server instance.
     */
    public Server getServer() {
        return server;
    }

    /**
     * Set the server instance (called by the server during plugin load).
     */
    public void setServer(Server server) {
        this.server = server;
    }
}

