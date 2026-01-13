package com.yourname.bluestone;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.server.core.universe.world.meta.BlockState;

public class ActivatorState extends BlockState {

    public boolean active = false;

    public static final Codec<ActivatorState> CODEC = BuilderCodec.builder(ActivatorState.class, ActivatorState::new, BlockState.BASE_CODEC)
            .append(new KeyedCodec<>("Active", Codec.BOOLEAN), (state, o) -> state.active = o, state -> state.active)
            .build();

    // Interaction logic would go here. 
    // Example:
    // public void onInteract(Player player) {
    //     this.active = !this.active;
    // }
}
