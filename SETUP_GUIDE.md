# Bluestone Pack Setup Guide

This guide explains how the Bluestone pack is structured according to the official Hytale Pack guidelines.

## ✅ What's Already Complete

### 1. Pack Manifest
- ✅ `manifest.json` - Pack metadata configured correctly

### 2. Block Definitions (JSON)
- ✅ `bluestone_wire.json` - Following official block definition format
- ✅ `bluestone_switch.json` - Following official block definition format

Both files include:
- Translation properties
- Icon paths
- Categories (Blocks.Decoration)
- Block type configuration (Material, DrawType, Group)
- Gathering/breaking behavior
- Texture paths for different states
- Particle colors and sounds
- State definitions (off/on)
- Switch includes interaction for toggling states

### 3. Translation Files
- ✅ `en-US/server.lang` - English translations for both blocks

### 4. Block Models
- ✅ Basic blockymodel files for wire and switch (off/on states)

### 5. Folder Structure
- ✅ Complete Pack folder hierarchy following official guidelines

## ⚠️ What Still Needs to be Created

### Required Texture Files (PNG)
You need to create these 6 texture files:

#### Block Textures (16x16 or 32x32 px):
1. `Common/BlockTextures/Blocks/bluestone_wire_off.png`
   - Dark/dull blue cross pattern
   - Unpowered state

2. `Common/BlockTextures/Blocks/bluestone_wire_on.png`
   - Bright glowing blue cross pattern
   - Powered state

3. `Common/BlockTextures/Blocks/bluestone_switch_off.png`
   - Lever/switch in DOWN position
   - Gray/stone color

4. `Common/BlockTextures/Blocks/bluestone_switch_on.png`
   - Lever/switch in UP position
   - Active appearance

#### Inventory Icons (64x64 or 128x128 px):
5. `Common/Icons/ItemsGenerated/bluestone_wire.png`
   - Inventory icon for wire
   - Transparent background

6. `Common/Icons/ItemsGenerated/bluestone_switch.png`
   - Inventory icon for switch
   - Transparent background

**Note:** Placeholder `.txt` files have been created in each location with detailed requirements.

## 📋 Block Definition Details

### Bluestone Wire
```
Type: Block (Decoration)
Material: Solid (but walkable)
DrawType: Cross (renders as + pattern)
States: off (dark blue) | on (bright blue)
Breaking: Instant by hand, drops itself
Stack Size: 64
Category: Blocks.Decoration
```

### Bluestone Switch
```
Type: Block (Decoration)
Material: Solid
DrawType: Cube (standard block)
States: off | on
Interaction: Right-click to toggle state
Breaking: By hand, drops itself
Stack Size: 64
Category: Blocks.Decoration
```

## 🎮 How the Plugin Integrates

The `BluestonePlugin.java` handles server-side logic:

1. **Block Identification**: Uses block type IDs that match the JSON filenames
   - Wire: `bluestone:bluestone_wire`
   - Switch: `bluestone:bluestone_switch`

2. **Event Handling**:
   - `BlockStateChangeEvent` - Detects when switch is toggled
   - `BlockPlaceEvent` - Updates circuits when blocks are placed
   - `BlockBreakEvent` - Updates circuits when blocks are broken

3. **Signal Propagation**:
   - BFS algorithm to find connected wires
   - Updates wire states from "off" to "on"
   - Tracks active power sources
   - Recalculates entire network on changes

## 🚀 Testing Instructions

### Step 1: Create Textures
Create the 6 required PNG files (see specifications above)

### Step 2: Build the Plugin
```bash
gradlew shadowJar
```

### Step 3: Install the Pack
Copy the `Bluestone` folder to:
```
%AppData%/Hytale/UserData/Packs/Bluestone/
```

### Step 4: Install the Plugin JAR
Copy `build/libs/Bluestone-1.0.0.jar` to your server's plugins folder

### Step 5: Enable in Hytale
1. Launch Hytale
2. Go to "Worlds" tab
3. Right-click your world
4. Enable the "Bluestone" pack
5. Join the world

### Step 6: Test in Game
1. Open Creative Mode inventory
2. Find blocks in "Blocks.Decoration" category
3. Place Bluestone Wire and Switches
4. Right-click switches to toggle
5. Watch wires light up when connected to powered switches

## 🔧 Using the Asset Editor

After loading the pack, you can fine-tune properties:

1. In-game, open inventory
2. Click "Creation Tools" tab
3. Open "Asset Editor"
4. Select "Bluestone" pack
5. Edit block properties visually
6. Changes save automatically

You can modify:
- Material properties
- Breaking behavior
- Sounds and particles
- Textures
- Drop items
- Stack sizes
- Categories

## 📁 File Reference

All paths are relative to `src/main/resources/Bluestone/`:

```
Bluestone/
├── manifest.json                                     ✅ Complete
├── Common/
│   ├── BlockTextures/Blocks/
│   │   ├── bluestone_wire_off.png                   ⚠️ Need to create
│   │   ├── bluestone_wire_on.png                    ⚠️ Need to create
│   │   ├── bluestone_switch_off.png                 ⚠️ Need to create
│   │   └── bluestone_switch_on.png                  ⚠️ Need to create
│   ├── Icons/ItemsGenerated/
│   │   ├── bluestone_wire.png                       ⚠️ Need to create
│   │   └── bluestone_switch.png                     ⚠️ Need to create
│   └── Models/Blocks/
│       ├── bluestone_wire_off.blockymodel           ✅ Complete
│       ├── bluestone_wire_on.blockymodel            ✅ Complete
│       ├── bluestone_switch_off.blockymodel         ✅ Complete
│       └── bluestone_switch_on.blockymodel          ✅ Complete
└── Server/
    ├── Item/Items/
    │   ├── bluestone_wire.json                      ✅ Complete
    │   └── bluestone_switch.json                    ✅ Complete
    └── Languages/en-US/
        └── server.lang                              ✅ Complete
```

## 🐛 Troubleshooting

### Blocks don't appear in game
- Verify all 6 texture PNG files are created
- Check manifest.json syntax
- Ensure pack is enabled in world settings
- Verify JSON files are valid (use a JSON validator)

### Switch doesn't toggle
- Check that Interactions are defined in bluestone_switch.json
- Verify the plugin JAR is loaded on the server
- Check server console for errors

### Wires don't power up
- Ensure plugin is running (check server logs)
- Verify block IDs match in JSON and plugin code
- Test with simple circuit (switch + 1 wire)

### Textures are missing/black
- Verify PNG files are in correct locations
- Check file names match exactly (case-sensitive)
- Ensure texture paths in JSON match file locations

## 📚 References

This pack follows the official Hytale documentation:
- "Adding a Block" guide
- Pack folder structure standards
- Block definition JSON format
- Translation file format

## ✨ Next Steps

1. Create the 6 required texture files
2. Test in Hytale with the pack enabled
3. Use Asset Editor for fine-tuning
4. Add more blocks (repeaters, torches, etc.)
5. Enhance plugin with advanced features

