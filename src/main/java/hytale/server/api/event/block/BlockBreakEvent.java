package hytale.server.api.event.block;

import hytale.server.api.world.Block;

/**
 * Event fired when a block is broken.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class BlockBreakEvent {

    private final Block block;
    private boolean cancelled;

    public BlockBreakEvent(Block block) {
        this.block = block;
        this.cancelled = false;
    }

    /**
     * Get the block that was broken.
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

