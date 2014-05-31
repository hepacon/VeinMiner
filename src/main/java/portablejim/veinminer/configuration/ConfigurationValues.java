/* This file is part of VeinMiner.
 *
 *    VeinMiner is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as
 *    published by the Free Software Foundation, either version 3 of
 *     the License, or (at your option) any later version.
 *
 *    VeinMiner is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with VeinMiner.
 *    If not, see <http://www.gnu.org/licenses/>.
 */

package portablejim.veinminer.configuration;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import portablejim.veinminer.api.ToolType;

import java.io.File;
import java.util.HashMap;

/**
 * Stores the raw values of configs which are stored in the config file.
 *
 * The Forge config file writer to write files.
 */

public class ConfigurationValues {

    private Configuration configFile;

    public static final String CONFIG_AUTODETECT = "autodetect";
    public static final String CONFIG_AUTODETECT_COMMENT = "Autodetect items and blocks during game start-up.";
    public static final String CONFIG_BLOCKLIST = "blocklist";
    public static final String CONFIG_BLOCKLIST_COMMENT = "Names of blocks to auto-mine.\nNames are formatted like 'modName:block_name/metadata'. Separate names (with metadata) with ','.\nUse 'minecraft' as the mod name for vanilla blocks.";
    public static final String CONFIG_ITEMLIST = "itemlist";
    public static final String CONFIG_ITEMLIST_COMMENT = "Tools to use to auto-mine with.\nNames are formatted like 'modName:tool_name/metadata'. Separate names (with metadata) with ','.\nUse 'minecraft' as the mod name for vanilla items.";
    public static final String CONFIG_LIMITS = "limit";
    public static final String CONFIG_MISC = "misc";
    public static final String CONFIG_OVERRIDE = "overrides";
    public static final String CONFIG_CLIENT = "client";
    public static final String CONFIG_CLIENT_COMMENT = "These settings are used client side, so they have no impact on servers.";

    public boolean AUTODETECT_TOOLS_TOGGLE;
    public static final boolean AUTODETECT_TOOLS_TOGGLE_DEFAULT = true;
    public static final String AUTODETECT_TOOLS_TOGGLE_CONFIGNAME = "autodetect.tools.enable";
    public static final String AUTODETECT_TOOLS_TOGGLE_DESCRIPTION = "Autodetect tools on starting the game, adding the names to the list.";

    public HashMap<ToolType, ConfigToolValue> toolConfig = new HashMap<ToolType, ConfigToolValue>(ToolType.values().length);

    public String BLOCK_EQUIVALENCY_LIST;
    public static final String BLOCK_EQUIVALENCY_LIST_DEFAULT = "minecraft:redstone_ore=minecraft:lit_redstone_ore";
    public static final String BLOCK_EQUIVALENCY_LIST_CONFIGNAME = "equalBlocks";
    public static final String BLOCK_EQUIVALENCY_LIST_DESCRIPTION = String.format("Block IDs (with metadata) to consider equivalent.\nNames are formatted like 'modName:block_name/metadata'. Separate names (with metadata) with ','. Use 'minecraft' as the mod name for vanilla blocks.\n[default: '%s']", BLOCK_EQUIVALENCY_LIST_DEFAULT);

    public int BLOCK_LIMIT;
    public static final int BLOCK_LIMIT_DEFAULT = 800;
    public static final String BLOCK_LIMIT_CONFIGNAME = "limit.blocks";
    public static final String BLOCK_LIMIT_DESCRIPTION = String.format("Limit of blocks to be destroyed at once. Use -1 for infinite. [range: -1 to 2147483647, default: %d]", BLOCK_LIMIT_DEFAULT);

    public int RADIUS_LIMIT;
    public static final int RADIUS_LIMIT_DEFAULT = 20;
    public static final String RADIUS_LIMIT_CONFIGNAME = "limit.radius";
    public static final String RADIUS_LIMIT_DESCRIPTION = String.format("Maximum distance from the first block to search for blocks to destroy. [range: -1 to 1000, default: %d]", RADIUS_LIMIT_DEFAULT);

    public int BLOCKS_PER_TICK;
    public static final int BLOCKS_PER_TICK_DEFAULT = 10;
    public static final String BLOCKS_PER_TICK_CONFIGNAME = "limit.blocksPerTick";
    public static final String BLOCKS_PER_TICK_DESCRIPTION = String.format("Maximum number of blocks to be removed per game tick (1/20 seconds). Using a low number will keep the game from getting huge performance drops but also decreases the speed at which blocks are destroyed. [range: 1 ~ 1000, default: %d]", BLOCKS_PER_TICK_DEFAULT);

    public boolean ENABLE_ALL_BLOCKS;
    public static final boolean ENABLE_ALL_BLOCKS_DEFAULT = false;
    public static final String ENABLE_ALL_BLOCKS_CONFIGNAME = "override.allBlocks";
    public static final String ENABLE_ALL_BLOCKS_DESCRIPTION = "Mine all blocks with all registered tools that can harvest blocks.";

    public boolean ENABLE_ALL_TOOLS;
    public static final boolean ENABLE_ALL_TOOLS_DEFAULT = false;
    public static final String ENABLE_ALL_TOOLS_CONFIGNAME = "override.allTools";
    public static final String ENABLE_ALL_TOOLS_DESCRIPTION = "Allow all tools, including the open hand, to be used to mine blocks.";

    public String CLIENT_PREFERRED_MODE;
    public static final String CLIENT_PREFERRED_MODE_DEFAULT = "pressed";
    public static final String CLIENT_PREFERRED_MODE_CONFIGNAME = "client.preferredMode";
    public static final String CLIENT_PREFERRED_MODE_DESCRIPTION = "What mode should the client use when joining a game.\nValid modes: [default: pressed]\n  'disabled' = don't enable, even when keybind pressed\n  'pressed' = enables when keybind is pressed\n  'released' = enables when keybind is released\n  'sneak' = enables when sneaking (ignores keybind)\n  'nosneak' = enables when not sneaking (ignores keybind)";

    public ConfigurationValues(File file) {
        configFile = new Configuration(file);

        toolConfig.put(ToolType.AXE, new ConfigToolValue("axe", true, "log,treeLeaves", "minecraft:log,minecraft:log2,minecraft:leaves,minecraft:leaves2,minecraft:fence", "minecraft:wooden_axe,minecraft:stone_axe,minecraft:golden_axe,minecraft:iron_axe,minecraft:diamond_axe"));
        toolConfig.put(ToolType.HOE, new ConfigToolValue("hoe", false, "", "minecraft:wheat,minecraft:pumpkin,minecraft:melon_block,minecraft:carrots,minecraft:potatoes", "minecraft:wooden_hoe,minecraft:stone_hoe,minecraft:golden_hoe,minecraft:iron_hoe,minecraft:diamond_hoe"));
        toolConfig.put(ToolType.PICKAXE, new ConfigToolValue("pickaxe", true, "ore", "minecraft:coal_ore,minecraft:gold_ore,minecraft:iron_ore,minecraft:diamond_ore,minecraft:lapis_ore,minecraft:emerald_ore,minecraft:quartz_ore,minecraft:redstone_ore,minecraft:lit_redstone_ore,minecraft:mossy_cobblestone,minecraft:glowstone,minecraft:obsidian,minecraft:nether_brick_fence,minecraft:cobblestone_wall", "minecraft:wooden_pickaxe,minecraft:stone_pickaxe,minecraft:golden_pickaxe,minecraft:iron_pickaxe,minecraft:diamond_pickaxe"));
        toolConfig.put(ToolType.SHEARS, new ConfigToolValue("shears", true, "treeLeaves", "minecraft:leaves,minecraft:leaves2,minecraft:web,minecraft:tallgrass,minecraft:deadbush,minecraft:wool,minecraft:vine", "minecraft:shears"));
        toolConfig.put(ToolType.SHOVEL, new ConfigToolValue("shovel", false, "", "minecraft:clay,minecraft:gravel", "minecraft:wooden_shovel,minecraft:stone_shovel,minecraft:golden_shovel,minecraft:iron_shovel,minecraft:diamond_shovel"));

        loadConfigFile();
    }

    public void loadConfigFile() {
        configFile.load();

        configFile.addCustomCategoryComment(CONFIG_AUTODETECT, CONFIG_AUTODETECT_COMMENT);
        AUTODETECT_TOOLS_TOGGLE = configFile.get(CONFIG_AUTODETECT, AUTODETECT_TOOLS_TOGGLE_CONFIGNAME, AUTODETECT_TOOLS_TOGGLE_DEFAULT, AUTODETECT_TOOLS_TOGGLE_DESCRIPTION).getBoolean(AUTODETECT_TOOLS_TOGGLE_DEFAULT);

        configFile.addCustomCategoryComment(CONFIG_BLOCKLIST, CONFIG_BLOCKLIST_COMMENT);
        configFile.addCustomCategoryComment(CONFIG_ITEMLIST, CONFIG_ITEMLIST_COMMENT);

        for(ToolType toolType : ToolType.values()) {
            ConfigOptionBoolean autoToggle = toolConfig.get(toolType).autodetectToggle;
            autoToggle.value = configFile.get(CONFIG_AUTODETECT, autoToggle.configName, autoToggle.valueDefault, autoToggle.description).getBoolean(autoToggle.valueDefault);
            ConfigOptionString autoList = toolConfig.get(toolType).autodetectList;
            autoList.value = configFile.get(CONFIG_AUTODETECT, autoList.configName, autoList.valueDefault, autoList.description).getString();
            ConfigOptionString blockList = toolConfig.get(toolType).blockIdList;
            blockList.value = configFile.get(CONFIG_BLOCKLIST, blockList.configName, blockList.valueDefault, blockList.description).getString();
            ConfigOptionString toolList = toolConfig.get(toolType).toolIdList;
            toolList.value = configFile.get(CONFIG_ITEMLIST, toolList.configName, toolList.valueDefault, toolList.description).getString();
        }

        BLOCK_LIMIT = configFile.get(CONFIG_LIMITS, BLOCK_LIMIT_CONFIGNAME, BLOCK_LIMIT_DEFAULT, BLOCK_LIMIT_DESCRIPTION).getInt(BLOCK_LIMIT_DEFAULT);
        RADIUS_LIMIT = configFile.get(CONFIG_LIMITS, RADIUS_LIMIT_CONFIGNAME, RADIUS_LIMIT_DEFAULT, RADIUS_LIMIT_DESCRIPTION).getInt(RADIUS_LIMIT_DEFAULT);
        BLOCKS_PER_TICK = configFile.get(CONFIG_LIMITS, BLOCKS_PER_TICK_CONFIGNAME, BLOCKS_PER_TICK_DEFAULT, BLOCKS_PER_TICK_DESCRIPTION).getInt(BLOCKS_PER_TICK_DEFAULT);

        BLOCK_EQUIVALENCY_LIST = configFile.get(CONFIG_MISC, BLOCK_EQUIVALENCY_LIST_CONFIGNAME, BLOCK_EQUIVALENCY_LIST_DEFAULT, BLOCK_EQUIVALENCY_LIST_DESCRIPTION).getString();

        ENABLE_ALL_BLOCKS = configFile.get(CONFIG_OVERRIDE, ENABLE_ALL_BLOCKS_CONFIGNAME, ENABLE_ALL_BLOCKS_DEFAULT, ENABLE_ALL_BLOCKS_DESCRIPTION).getBoolean(ENABLE_ALL_BLOCKS_DEFAULT);
        ENABLE_ALL_TOOLS = configFile.get(CONFIG_OVERRIDE, ENABLE_ALL_TOOLS_CONFIGNAME, ENABLE_ALL_TOOLS_DEFAULT, ENABLE_ALL_TOOLS_DESCRIPTION).getBoolean(ENABLE_ALL_TOOLS_DEFAULT);

        configFile.addCustomCategoryComment(CONFIG_CLIENT, CONFIG_CLIENT_COMMENT);
        CLIENT_PREFERRED_MODE = configFile.get(CONFIG_CLIENT, CLIENT_PREFERRED_MODE_CONFIGNAME, CLIENT_PREFERRED_MODE_DEFAULT, CLIENT_PREFERRED_MODE_DESCRIPTION).getString();

        configFile.save();
    }

    public void saveConfigFile() {
        //configFile.load();

        for(ToolType toolType : ToolType.values()) {
            ConfigToolValue thisToolValue = toolConfig.get(toolType);
            Property blockListProp = configFile.getCategory(CONFIG_BLOCKLIST).get(thisToolValue.blockIdList.configName);
            if(blockListProp != null) {
                blockListProp.set(thisToolValue.blockIdList.value);
            }
            Property itemListProp = configFile.getCategory(CONFIG_ITEMLIST).get(thisToolValue.toolIdList.configName);
            if(itemListProp != null) {
                itemListProp.set(thisToolValue.toolIdList.value);
            }
        }

        configFile.getCategory(CONFIG_LIMITS).get(BLOCK_LIMIT_CONFIGNAME).set(BLOCK_LIMIT);
        configFile.getCategory(CONFIG_LIMITS).get(RADIUS_LIMIT_CONFIGNAME).set(RADIUS_LIMIT);
        configFile.getCategory(CONFIG_LIMITS).get(BLOCKS_PER_TICK_CONFIGNAME).set(BLOCKS_PER_TICK);

        configFile.getCategory(CONFIG_MISC).get(BLOCK_EQUIVALENCY_LIST_CONFIGNAME).set(BLOCK_EQUIVALENCY_LIST);

        configFile.getCategory(CONFIG_OVERRIDE).get(ENABLE_ALL_BLOCKS_CONFIGNAME).set(ENABLE_ALL_BLOCKS);
        configFile.getCategory(CONFIG_OVERRIDE).get(ENABLE_ALL_TOOLS_CONFIGNAME).set(ENABLE_ALL_TOOLS);

        configFile.getCategory(CONFIG_CLIENT).get(CLIENT_PREFERRED_MODE_CONFIGNAME).set(CLIENT_PREFERRED_MODE);

        if(configFile.hasChanged()) {
            configFile.save();
        }
    }
}
