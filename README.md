# Bluestone - Hytale Redstone-like Plugin & Pack

A Hytale plugin and pack that adds **Bluestone** - a redstone-like wire and signal propagation system. Place wires and use switches to create circuits!

> **✨ Server-authoritative design!** All circuit logic runs server-side, so all players see the same state without client-side mods.

![Status](https://img.shields.io/badge/Status-In%20Development-yellow)
![Hytale](https://img.shields.io/badge/Hytale-Early%20Access-blue)
![Java](https://img.shields.io/badge/Java-25-orange)

---

## Features

✅ **Bluestone Wire** - Conducts signals like redstone dust  
✅ **Bluestone Switch** - Toggle power source (like a lever)  
✅ **Signal Propagation** - Automatic power flow through connected wires using BFS  
✅ **Server-Side Logic** - All players see synchronized circuit states  
✅ **Multiple Power Sources** - Supports multiple switches in one network  
✅ **Official Pack Format** - Follows Hytale's "Adding a Block" guide exactly  

---

## Quick Start

### Prerequisites

- **Java 25 JDK** - [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Hytale Early Access** - With pack/plugin support enabled
- **Gradle** (included via wrapper)

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/bluestone.git
cd bluestone
```

### 2. Build the Plugin

```bash
# Windows
gradlew.bat shadowJar

# Linux/Mac
./gradlew shadowJar
```

Your plugin JAR will be in: `build/libs/Bluestone-1.0.0.jar`

### 3. Create Textures ⚠️ Required

Before the pack works visually, you need to create **6 texture files**:

- 4 block textures (16x16 or 32x32 PNG)
- 2 inventory icons (64x64 or 128x128 PNG)

See detailed specifications:
- **[SETUP_GUIDE.md](SETUP_GUIDE.md)** - Complete setup instructions
- `src/main/resources/Bluestone/Common/BlockTextures/Blocks/*.txt` - Texture specs
- `src/main/resources/Bluestone/Common/Icons/ItemsGenerated/*.txt` - Icon specs

### 4. Install the Pack

Copy the pack folder to Hytale's UserData:

```bash
# Windows (PowerShell)
xcopy /E /I src\main\resources\Bluestone "%AppData%\Hytale\UserData\Packs\Bluestone"

# Linux/Mac
cp -r src/main/resources/Bluestone ~/Library/Application Support/Hytale/UserData/Packs/
```

### 5. Install the Plugin

Copy `build/libs/Bluestone-1.0.0.jar` to your Hytale server's `plugins/` folder.

### 6. Enable in Hytale

1. Launch Hytale
2. Go to **Worlds** tab
3. Right-click your world → **Enable "Bluestone" pack**
4. Join the world

### 7. Test the Blocks

1. Open **Creative Mode** inventory
2. Find blocks in **"Blocks.Decoration"** category
3. Place **Bluestone Wires** and **Switches**
4. **Right-click switches** to toggle them ON/OFF
5. Watch connected wires **light up** when powered! ⚡

**📖 For detailed setup, see [SETUP_GUIDE.md](SETUP_GUIDE.md)**

---

## How It Works

### The Plugin (Server-Side Logic)

Located in `src/main/java/com/bluestone/BluestonePlugin.java`:

- **Event Listening**: Detects switch toggles via `BlockStateChangeEvent`
- **BFS Propagation**: Uses breadth-first search to find all connected wires
- **State Management**: Updates wire block states from "off" → "on"
- **Power Source Tracking**: Maintains a set of active switches for efficient recalculation
- **Network Recalculation**: Recalculates entire circuit when blocks are placed/broken
- **Server Authority**: All logic runs server-side, keeping all players synchronized

**Key Methods:**
- `onBlockStateChange()` - Handles switch toggles
- `recalculateAllBluestone()` - Recalculates entire network
- `propagateSignalFromSource()` - BFS power propagation
- `setWireState()` - Updates wire block states

### The Pack (Client & Server Assets)

Located in `src/main/resources/Bluestone/`:

**Pack Structure** (follows official Hytale format):
```
Bluestone/
├── manifest.json              # Pack metadata
├── Common/                    # Client-side assets
│   ├── BlockTextures/Blocks/  # Block textures (need PNGs)
│   ├── Icons/ItemsGenerated/  # Inventory icons (need PNGs)
│   └── Models/Blocks/         # 3D models (.blockymodel)
└── Server/                    # Server-side definitions
    ├── Item/Items/            # Block JSON definitions
    │   ├── bluestone_wire.json
    │   └── bluestone_switch.json
    └── Languages/en-US/       # Translations
        └── server.lang
```

**Block Definitions:**

**Bluestone Wire** (`bluestone_wire.json`):
- Material: Solid
- DrawType: Cross (renders as + pattern like flowers)
- States: "off" (dark blue) | "on" (bright blue)
- Gathering: Breaks by hand, drops itself
- MaxStack: 64

**Bluestone Switch** (`bluestone_switch.json`):
- Material: Solid
- DrawType: Cube (standard block)
- States: "off" | "on"
- Interaction: ChangeState on Use (right-click to toggle)
- Gathering: Breaks by hand, drops itself
- MaxStack: 64

Both blocks are in the **"Blocks.Decoration"** category.

---

## Project Structure

```
bluestone/
├── src/main/
│   ├── java/com/bluestone/
│   │   ├── BluestonePlugin.java         # Main plugin logic
│   │   └── command/
│   │       └── GiveCommand.java         # Example command
│   └── resources/
│       ├── manifest.json                # Plugin manifest
│       ├── config.json                  # Plugin config
│       └── Bluestone/                   # Pack folder
│           ├── manifest.json            # Pack manifest
│           ├── README.md                # Pack documentation
│           ├── Common/                  # Client assets
│           │   ├── BlockTextures/Blocks/
│           │   │   ├── *.png.txt        # Texture specifications
│           │   │   └── *.blockymodel    # 3D models
│           │   ├── Icons/ItemsGenerated/
│           │   │   └── *.png.txt        # Icon specifications
│           │   └── Models/Blocks/
│           │       └── *.blockymodel    # Block models
│           └── Server/                  # Server definitions
│               ├── Item/Items/
│               │   ├── bluestone_wire.json      # Wire definition
│               │   └── bluestone_switch.json    # Switch definition
│               └── Languages/en-US/
│                   └── server.lang      # English translations
├── build.gradle.kts                     # Build configuration
├── gradle.properties                    # Project properties
├── settings.gradle.kts                  # Gradle settings
├── SETUP_GUIDE.md                       # Detailed setup guide
├── IMPLEMENTATION_SUMMARY.md            # Technical details
├── README.md                            # This file
└── LICENSE                              # MIT License
```

---

## Development

### Building

```bash
# Compile only
./gradlew compileJava

# Build plugin JAR
./gradlew shadowJar

# Clean and rebuild
./gradlew clean shadowJar
```

### Testing

```bash
# Run server with your plugin (if configured)
./gradlew runServer

# Clean build directory
./gradlew clean
```

### Adding Features

**Extend the plugin:**
1. Add new event handlers in `BluestonePlugin.java`
2. Create new block types in pack JSON files
3. Add textures and models
4. Update translations

**Example: Add a Bluestone Torch**
1. Create `bluestone_torch.json` in `Server/Item/Items/`
2. Add textures to `Common/BlockTextures/Blocks/`
3. Add translation to `server.lang`
4. Update plugin to handle torch as a power source

---

## Pack Compliance

This pack follows the official **"Adding a Block"** Hytale guide:

✅ Correct folder structure (`Common/`, `Server/`)  
✅ Proper `manifest.json` format  
✅ Block definitions in `Server/Item/Items/`  
✅ Translation files in `Server/Languages/en-US/`  
✅ Textures in `Common/BlockTextures/`  
✅ Icons in `Common/Icons/ItemsGenerated/`  
✅ Models in `Common/Models/Blocks/`  
✅ All required properties (TranslationProperties, BlockType, etc.)  
✅ State management (off/on states with textures)  
✅ Interactions (ChangeState for switch)  
✅ Categories (Blocks.Decoration)  

**Technical Validation:**
- ✅ JSON syntax validated
- ✅ Paths follow official conventions
- ✅ Plugin code compatible with JSON structure
- ⚠️ Texture assets pending creation

See [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) for complete compliance details.

---

## Documentation

- **[SETUP_GUIDE.md](SETUP_GUIDE.md)** - Complete setup and testing instructions
- **[IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md)** - Technical implementation details
- **[src/main/resources/Bluestone/README.md](src/main/resources/Bluestone/README.md)** - Pack structure overview

---

## Roadmap

### Current Status (v1.0.0)
- ✅ Plugin architecture complete
- ✅ Block definitions following official format
- ✅ BFS signal propagation
- ✅ State management
- ✅ Pack structure compliant
- ⚠️ Texture assets pending

### Future Features
- [ ] Bluestone Torch (always-on power source)
- [ ] Bluestone Repeater (signal delay)
- [ ] Bluestone Lamp (output device)
- [ ] Pressure Plates (player detection)
- [ ] Doors/Pistons integration
- [ ] Advanced circuitry components

---

## Troubleshooting

### Blocks don't appear in game
- ✅ Verify pack is enabled in world settings
- ✅ Check that texture PNG files exist (not just .txt placeholders)
- ✅ Validate JSON syntax (use `ConvertFrom-Json` in PowerShell)
- ✅ Check Hytale logs for pack loading errors

### Switch doesn't toggle
- ✅ Ensure `Interactions` section exists in `bluestone_switch.json`
- ✅ Verify plugin JAR is in server's `plugins/` folder
- ✅ Check server console for plugin loading errors
- ✅ Test with `/plugins` command to verify plugin is enabled

### Wires don't power up
- ✅ Ensure plugin is loaded (check server logs)
- ✅ Verify block IDs match: `bluestone:bluestone_wire` and `bluestone:bluestone_switch`
- ✅ Test with simple circuit: 1 switch + 1 wire adjacent
- ✅ Check plugin logs for propagation messages

### Textures are missing/black
- ✅ Create the 6 required PNG files (see SETUP_GUIDE.md)
- ✅ Verify file names match JSON exactly (case-sensitive)
- ✅ Check paths are correct relative to pack folder
- ✅ Ensure PNG files are in correct folders (not in root)

---

## Contributing

Contributions are welcome! Areas for improvement:

- Creating texture assets (PNG files)
- Adding new block types
- Optimizing BFS algorithm
- Adding configuration options
- Writing unit tests
- Improving documentation

**To contribute:**
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## License

This project is licensed under the **MIT License** - see [LICENSE](LICENSE) file for details.

You are free to:
- ✅ Use commercially
- ✅ Modify and distribute
- ✅ Use privately
- ✅ Sublicense

---

## Credits

**Authors:**
- **GianSmile** - [YouTube](https://www.youtube.com/@GianSmile)
- **NeoPlayzGames** - [YouTube](https://www.youtube.com/@NPG123)

**Inspired by:**
- Minecraft's Redstone system
- Hytale's official block creation guide

**Built with:**
- Java 25
- Gradle 8.11.1
- Hytale Server API

---

## Support

- **Issues:** [GitHub Issues](https://github.com/yourusername/bluestone/issues)
- **Documentation:** See `SETUP_GUIDE.md` and `IMPLEMENTATION_SUMMARY.md`
- **Community:** Join the Hytale modding community

---

**Happy Circuit Building! ⚡🔵**

