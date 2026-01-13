package hytale.server.api.world;

/**
 * Represents a block type.
 * This is a stub class - actual implementation will come from the Hytale server JAR.
 */
public class BlockType {

    private final String id;
    private final String name;

    public BlockType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the block type ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the block type name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BlockType{id='" + id + "', name='" + name + "'}";
    }
}

