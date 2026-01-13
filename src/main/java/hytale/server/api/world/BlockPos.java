package hytale.server.api.world;

/**
 * Represents a position in the world.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class BlockPos {

    private final int x;
    private final int y;
    private final int z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get the X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the Y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Get the Z coordinate.
     */
    public int getZ() {
        return z;
    }

    /**
     * Create a new BlockPos offset from this position.
     */
    public BlockPos offset(int dx, int dy, int dz) {
        return new BlockPos(x + dx, y + dy, z + dz);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockPos blockPos = (BlockPos) obj;
        return x == blockPos.x && y == blockPos.y && z == blockPos.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "BlockPos{x=" + x + ", y=" + y + ", z=" + z + "}";
    }
}

