package hytale.server.api.event.block;

import hytale.server.api.world.Block;

/**
 * Event fired when a block is placed.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class BlockPlaceEvent {

    private final Block block;
    private boolean cancelled;

    public BlockPlaceEvent(Block block) {
        this.block = block;
        this.cancelled = false;
    }

    /**
     * Get the block that was placed.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Check if the event is cancelled.
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Set whether the event is cancelled.
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

