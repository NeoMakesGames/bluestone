package hytale.server.api.event.block;

import hytale.server.api.world.Block;

/**
 * Event fired when a block's state changes.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class BlockStateChangeEvent {

    private final Block block;
    private final String oldState;
    private final String newState;

    public BlockStateChangeEvent(Block block, String oldState, String newState) {
        this.block = block;
        this.oldState = oldState;
        this.newState = newState;
    }

    /**
     * Get the block that changed state.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Get the old state name.
     */
    public String getOldState() {
        return oldState;
    }

    /**
     * Get the new state name.
     */
    public String getNewState() {
        return newState;
    }
}

