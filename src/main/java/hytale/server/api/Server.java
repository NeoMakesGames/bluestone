package hytale.server.api;

import hytale.server.api.event.Listener;
import hytale.server.api.plugin.JavaPlugin;

/**
 * Represents the Hytale server.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public interface Server {

    /**
     * Get the plugin manager.
     */
    PluginManager getPluginManager();

    /**
     * Get the scheduler.
     */
    Scheduler getScheduler();

    /**
     * Plugin manager interface.
     */
    interface PluginManager {
        /**
         * Register event listeners.
         */
        void registerEvents(Listener listener, JavaPlugin plugin);
    }

    /**
     * Scheduler interface.
     */
    interface Scheduler {
        /**
         * Run a task later.
         */
        void runTaskLater(JavaPlugin plugin, Runnable task, long delayTicks);
    }
}

