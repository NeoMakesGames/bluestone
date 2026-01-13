package hytale.server.api.world;

/**
 * Represents a block in the world.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class Block {

    private final World world;
    private final BlockPos position;
    private BlockType type;
    private String state;

    public Block(World world, BlockPos position, BlockType type, String state) {
        this.world = world;
        this.position = position;
        this.type = type;
        this.state = state;
    }

    /**
     * Get the world this block is in.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Get the position of this block.
     */
    public BlockPos getPosition() {
        return position;
    }

    /**
     * Get the block type.
     */
    public BlockType getType() {
        return type;
    }

    /**
     * Set the block type.
     */
    public void setType(BlockType type) {
        this.type = type;
    }

    /**
     * Get the current state of the block.
     */
    public String getState() {
        return state;
    }

    /**
     * Set the state of the block.
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Block{type=" + type + ", position=" + position + ", state='" + state + "'}";
    }
}

