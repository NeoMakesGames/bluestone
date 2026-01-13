# Bluestone Pack Structure

This folder contains the Bluestone Pack for Hytale, following the official Pack structure guidelines.

## Complete Folder Structure

```
Bluestone/
├── manifest.json                          # Pack metadata and configuration
├── Common/                                # Client-side assets (textures, models, etc.)
│   ├── BlockTextures/
│   │   └── Blocks/
│   │       ├── bluestone_wire_off.png    # Required: Dark/unpowered wire texture
│   │       ├── bluestone_wire_on.png     # Required: Bright/powered wire texture
│   │       ├── bluestone_switch_off.png  # Required: Switch OFF state texture
│   │       └── bluestone_switch_on.png   # Required: Switch ON state texture
│   ├── Icons/
│   │   └── ItemsGenerated/
│   │       ├── bluestone_wire.png        # Required: Inventory icon for wire
│   │       └── bluestone_switch.png      # Required: Inventory icon for switch
│   └── Models/
│       └── Blocks/
│           ├── bluestone_wire_off.blockymodel
│           ├── bluestone_wire_on.blockymodel
│           ├── bluestone_switch_off.blockymodel
│           └── bluestone_switch_on.blockymodel
└── Server/                                # Server-side logic (block definitions, etc.)
    ├── Item/
    │   └── Items/
    │       ├── bluestone_wire.json       # Block definition for wire
    │       └── bluestone_switch.json     # Block definition for switch
    └── Languages/
        └── en-US/
            └── server.lang               # English translations
```

## Required Assets to Complete

### Textures (PNG format, 16x16 or 32x32 pixels)
1. **bluestone_wire_off.png** - Dark/dull blue wire, unpowered state
2. **bluestone_wire_on.png** - Bright glowing blue wire, powered state
3. **bluestone_switch_off.png** - Switch in OFF position
4. **bluestone_switch_on.png** - Switch in ON position

### Icons (PNG format, 64x64 or 128x128 pixels)
1. **bluestone_wire.png** - Inventory icon for Bluestone Wire
2. **bluestone_switch.png** - Inventory icon for Bluestone Switch

## Block Definitions

Both blocks follow the official Hytale block definition format:

### Bluestone Wire
- **Material**: Solid (can be walked through)
- **DrawType**: Cross (renders as an X pattern, like flowers)
- **States**: "off" (unpowered) and "on" (powered)
- **Gathering**: Breaks instantly by hand, drops itself
- **Category**: Blocks.Decoration

### Bluestone Switch
- **Material**: Solid
- **DrawType**: Cube (standard block rendering)
- **States**: "off" and "on"
- **Interaction**: Right-click/Use to toggle between states
- **Gathering**: Breaks by hand, drops itself
- **Category**: Blocks.Decoration

## Installation for Testing

To test this pack in Hytale:

1. Copy the entire `Bluestone` folder to:
   ```
   %AppData%/Hytale/UserData/Packs/Bluestone/
   ```

2. Launch Hytale and go to the "Worlds" tab

3. Right-click on your world and enable the Bluestone pack

4. Join the world and open the Asset Editor (in Creative Tools) to fine-tune properties

5. Find the blocks in the "Blocks.Decoration" category in Creative Mode

## Plugin Integration

The BluestonePlugin (Java) handles the server-side logic:
- Detects when switches are toggled (BlockStateChangeEvent)
- Propagates power signals through connected wires using BFS algorithm
- Updates wire states from "off" to "on" when powered
- Manages active power sources and recalculates networks

## Notes

- Block IDs are automatically generated based on the Pack name and item filename
  - Wire: `bluestone:bluestone_wire`
  - Switch: `bluestone:bluestone_switch`

- The plugin uses these IDs to identify blocks in the game world

- All texture paths are relative to the Pack folder (Bluestone/)

- The Asset Editor can be used to visually edit properties after the pack is loaded

