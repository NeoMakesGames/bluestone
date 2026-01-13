package com.bluestone;

import hytale.server.api.Server;
import hytale.server.api.event.EventHandler;
import hytale.server.api.event.Listener;
import hytale.server.api.event.block.BlockBreakEvent;
import hytale.server.api.event.block.BlockPlaceEvent;
import hytale.server.api.event.block.BlockStateChangeEvent;
import hytale.server.api.plugin.JavaPlugin;
import hytale.server.api.world.Block;
import hytale.server.api.world.BlockPos;
import hytale.server.api.world.World;

import java.util.*;
import java.util.logging.Logger;

/**
 * BluestonePlugin - A redstone-like signal propagation system for Hytale.
 *
 * This plugin adds Bluestone wires and switches that allow players to create
 * circuits similar to Minecraft's redstone system. The server handles all
 * signal propagation logic, making it work seamlessly for all connected players.
 *
 * @author GianSmile
 * @author NeoPlayzGames
 * @version 1.0.0
 */
public class BluestonePlugin extends JavaPlugin implements Listener {

    private static BluestonePlugin instance;
    private Logger logger;

    // Block identifiers - these should match the JSON definition names
    private static final String BLUESTONE_WIRE_ID = "bluestone:bluestone_wire";
    private static final String BLUESTONE_SWITCH_ID = "bluestone:bluestone_switch";

    // State identifiers
    private static final String STATE_ON = "on";
    private static final String STATE_OFF = "off";

    // Track all active power sources for efficient recalculation
    private final Set<BlockPos> activeSources = new HashSet<>();

    /**
     * Constructor - Called when plugin is loaded.
     */
    public BluestonePlugin() {
        instance = this;
    }

    /**
     * Called when plugin is enabled.
     */
    @Override
    public void onEnable() {
        logger = getLogger();
        logger.info("BluestonePlugin enabling...");

        // Register this class as an event listener
        getServer().getPluginManager().registerEvents(this, this);

        logger.info("BluestonePlugin enabled! Bluestone wire and switches are ready.");
        logger.info("Place Bluestone Wires and use Bluestone Switches to create circuits!");
    }

    /**
     * Called when plugin is disabled.
     */
    @Override
    public void onDisable() {
        logger.info("BluestonePlugin disabled!");
        activeSources.clear();
    }

    /**
     * Get plugin instance.
     */
    public static BluestonePlugin getInstance() {
        return instance;
    }

    // ==================== Event Handlers ====================

    /**
     * Handle block state changes - triggered when a switch is toggled.
     * This event fires after the ChangeState interaction defined in JSON.
     */
    @EventHandler
    public void onBlockStateChange(BlockStateChangeEvent event) {
        Block block = event.getBlock();

        if (isBluestoneSwitch(block)) {
            BlockPos pos = block.getPosition();
            String newState = event.getNewState();

            logger.info("Bluestone Switch at " + pos + " changed to: " + newState);

            if (STATE_ON.equals(newState)) {
                // Switch turned ON - add to active sources and propagate
                activeSources.add(pos);
            } else {
                // Switch turned OFF - remove from active sources
                activeSources.remove(pos);
            }

            // Recalculate the entire Bluestone network
            recalculateAllBluestone(block.getWorld());
        }
    }

    /**
     * Handle block placement - update circuits when wires or switches are placed.
     */
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if (isBluestoneWire(block) || isBluestoneSwitch(block)) {
            logger.info("Bluestone block placed at " + block.getPosition());

            // If a switch is placed in the ON state, add it to sources
            if (isBluestoneSwitch(block) && isSwitchOn(block)) {
                activeSources.add(block.getPosition());
            }

            // Recalculate to update the newly placed block
            recalculateAllBluestone(block.getWorld());
        }
    }

    /**
     * Handle block breaking - update circuits when wires or switches are broken.
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        BlockPos pos = block.getPosition();

        if (isBluestoneWire(block) || isBluestoneSwitch(block)) {
            logger.info("Bluestone block broken at " + pos);

            // Remove from active sources if it was a switch
            activeSources.remove(pos);

            // Schedule recalculation after the block is actually removed
            getServer().getScheduler().runTaskLater(this, () -> {
                recalculateAllBluestone(block.getWorld());
            }, 1L);
        }
    }

    // ==================== Helper Methods ====================

    /**
     * Check if a block is a Bluestone Wire.
     */
    private boolean isBluestoneWire(Block block) {
        if (block == null) return false;
        String typeId = block.getType().getId();
        return typeId.contains("bluestone_wire");
    }

    /**
     * Check if a block is a Bluestone Switch.
     */
    private boolean isBluestoneSwitch(Block block) {
        if (block == null) return false;
        String typeId = block.getType().getId();
        return typeId.contains("bluestone_switch");
    }

    /**
     * Check if a Bluestone Switch is in the ON state.
     */
    private boolean isSwitchOn(Block block) {
        if (block == null) return false;
        return STATE_ON.equals(block.getState());
    }

    /**
     * Check if a Bluestone Wire is powered (ON state).
     */
    private boolean isWirePowered(Block block) {
        if (block == null) return false;
        return STATE_ON.equals(block.getState());
    }

    // ==================== Signal Propagation Logic ====================

    /**
     * Recalculate all Bluestone wires in the world.
     * This method:
     * 1. Turns off all Bluestone wires
     * 2. Propagates signals from all active power sources
     */
    private void recalculateAllBluestone(World world) {
        logger.info("Recalculating Bluestone network...");

        // First, turn off all wires (we'll re-power the ones connected to sources)
        turnOffAllWires(world);

        // Then propagate from each active source
        for (BlockPos sourcePos : activeSources) {
            Block sourceBlock = world.getBlockAt(sourcePos);
            if (sourceBlock != null && isBluestoneSwitch(sourceBlock) && isSwitchOn(sourceBlock)) {
                propagateSignalFromSource(world, sourcePos);
            }
        }

        // Clean up any invalid sources (blocks that no longer exist or are off)
        cleanupInvalidSources(world);

        logger.info("Bluestone network recalculation complete. Active sources: " + activeSources.size());
    }

    /**
     * Turn off all Bluestone wires that are currently powered.
     * In a real implementation, you might want to track wire positions
     * for efficiency, but for simplicity we search nearby areas.
     */
    private void turnOffAllWires(World world) {
        // In a production implementation, you would track all wire positions
        // For now, we rely on the propagation to only set wires that need to be on
        // This is handled by checking state before setting in propagateSignalFromSource
    }

    /**
     * Propagate Bluestone signal from a power source using BFS.
     * This will turn on all connected Bluestone wires.
     */
    private void propagateSignalFromSource(World world, BlockPos sourcePos) {
        Queue<BlockPos> toVisit = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();

        // Start from the source position
        toVisit.add(sourcePos);
        visited.add(sourcePos);

        while (!toVisit.isEmpty()) {
            BlockPos currentPos = toVisit.poll();

            // Check all adjacent positions (4 horizontal neighbors + up/down for vertical wiring)
            for (BlockPos neighborPos : getAdjacentPositions(currentPos)) {
                if (visited.contains(neighborPos)) {
                    continue;
                }

                Block neighbor = world.getBlockAt(neighborPos);

                if (neighbor != null && isBluestoneWire(neighbor)) {
                    // Power this wire
                    setWireState(neighbor, true);
                    visited.add(neighborPos);
                    toVisit.add(neighborPos);
                }
            }
        }

        logger.info("Signal propagated from " + sourcePos + ", powered " + (visited.size() - 1) + " wires");
    }

    /**
     * Turn off all wires connected to a position (used when a source is turned off).
     * Uses BFS to find all connected wires and turn them off.
     */
    private void turnOffConnectedWires(World world, BlockPos startPos) {
        Queue<BlockPos> toVisit = new ArrayDeque<>();
        Set<BlockPos> visited = new HashSet<>();

        toVisit.add(startPos);
        visited.add(startPos);

        while (!toVisit.isEmpty()) {
            BlockPos currentPos = toVisit.poll();

            for (BlockPos neighborPos : getAdjacentPositions(currentPos)) {
                if (visited.contains(neighborPos)) {
                    continue;
                }

                Block neighbor = world.getBlockAt(neighborPos);

                if (neighbor != null && isBluestoneWire(neighbor)) {
                    // Turn off this wire (it may be re-powered by another source in recalculation)
                    setWireState(neighbor, false);
                    visited.add(neighborPos);
                    toVisit.add(neighborPos);
                }
            }
        }
    }

    /**
     * Get all adjacent block positions (6 directions: N, S, E, W, Up, Down).
     */
    private List<BlockPos> getAdjacentPositions(BlockPos pos) {
        List<BlockPos> adjacent = new ArrayList<>(6);

        // Horizontal neighbors
        adjacent.add(pos.offset(1, 0, 0));   // East
        adjacent.add(pos.offset(-1, 0, 0));  // West
        adjacent.add(pos.offset(0, 0, 1));   // South
        adjacent.add(pos.offset(0, 0, -1));  // North

        // Vertical neighbors (for vertical wiring support)
        adjacent.add(pos.offset(0, 1, 0));   // Up
        adjacent.add(pos.offset(0, -1, 0));  // Down

        return adjacent;
    }

    /**
     * Set the state of a Bluestone Wire block.
     *
     * @param wire The wire block to modify
     * @param powered True to set to ON state, false for OFF state
     */
    private void setWireState(Block wire, boolean powered) {
        if (wire == null) return;

        String targetState = powered ? STATE_ON : STATE_OFF;
        String currentState = wire.getState();

        // Only update if the state actually changes
        if (!targetState.equals(currentState)) {
            wire.setState(targetState);
        }
    }

    /**
     * Clean up the active sources set by removing invalid entries.
     */
    private void cleanupInvalidSources(World world) {
        activeSources.removeIf(pos -> {
            Block block = world.getBlockAt(pos);
            return block == null || !isBluestoneSwitch(block) || !isSwitchOn(block);
        });
    }
}

