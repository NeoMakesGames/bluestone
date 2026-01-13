package com.yourname.bluestone;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.universe.world.chunk.state.TickableBlockState;
import com.hypixel.hytale.server.core.universe.world.meta.BlockState;
import com.hypixel.hytale.server.core.universe.world.storage.ChunkStore;

public class BluestoneWireState extends BlockState implements TickableBlockState {

    // Define the 'Power' property (0-15)
    public int power = 0;

    // Register the property in the Codec so it saves/loads
    public static final Codec<BluestoneWireState> CODEC = BuilderCodec.builder(BluestoneWireState.class, BluestoneWireState::new, BlockState.BASE_CODEC)
            .append(new KeyedCodec<>("Power", Codec.INT), (state, o) -> state.power = o, state -> state.power)
            .build();

    @Override
    public void tick(float v, int i, ArchetypeChunk<ChunkStore> archetypeChunk, Store<ChunkStore> store, CommandBuffer<ChunkStore> commandBuffer) {
        // Logic: Check neighbors to find the highest power source
        int maxNeighborPower = 0;
        
        // Pseudo-code for neighbor checking (API specific implementation required)
        // for (Direction dir : Direction.values()) {
        //     BlockState neighbor = world.getBlock(this.pos.offset(dir));
        //     if (neighbor instanceof BluestoneWireState) {
        //         maxNeighborPower = Math.max(maxNeighborPower, ((BluestoneWireState) neighbor).power);
        //     } else if (neighbor instanceof ActivatorState && ((ActivatorState) neighbor).active) {
        //         maxNeighborPower = 16; // Source
        //     }
        // }

        // Decay signal
        int newPower = Math.max(0, maxNeighborPower - 1);

        // Update state if changed
        if (this.power != newPower) {
            this.power = newPower;
            // Mark chunk/block for update
        }
    }
}
