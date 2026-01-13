package com.yourname.bluestone;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import javax.annotation.Nonnull;

public class BluestoneMod extends JavaPlugin {

    public BluestoneMod(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        
        // Register the Wire State
        this.getBlockStateRegistry().registerBlockState(
            BluestoneWireState.class, 
            "YourName_Bluestone_Wire", 
            BluestoneWireState.CODEC
        );

        // Register the Activator State
        this.getBlockStateRegistry().registerBlockState(
            ActivatorState.class, 
            "YourName_Bluestone_Activator", 
            ActivatorState.CODEC
        );
    }
}
