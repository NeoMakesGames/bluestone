package hytale.server.api.world;

/**
 * Represents a world in Hytale.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class World {

    private final String name;

    public World(String name) {
        this.name = name;
    }

    /**
     * Get the world name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the block at the specified position.
     */
    public Block getBlockAt(BlockPos position) {
        // Stub implementation - actual implementation would query the world data
        return null;
    }

    /**
     * Get the block at the specified coordinates.
     */
    public Block getBlockAt(int x, int y, int z) {
        return getBlockAt(new BlockPos(x, y, z));
    }

    /**
     * Set a block at the specified position.
     */
    public void setBlock(BlockPos position, BlockType type) {
        // Stub implementation - actual implementation would modify the world data
    }

    @Override
    public String toString() {
        return "World{name='" + name + "'}";
    }
}

