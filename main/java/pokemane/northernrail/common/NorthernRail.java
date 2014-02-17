package pokemane.northernrail.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import org.omg.PortableInterceptor.ServerRequestInfo;

/**
 * Created by pokemane on 2/15/14.
 */
public class NorthernRail {
    /*=========================================================*/
    public static final String MODNAME = "Northern Rail";
    public static final String CHANNEL = "NorthernRail";
    public static final String RESOURCE_PATH = "assets/northernrail/";

    public static final CreativeTabs TAB = new CreativeTabs(CHANNEL) {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.activator_rail);
        }
    };

    public static final String TEXTURE_NAME_PREFIX = "northernrail:";

    public static final String TEXTURE_DIRECTORY = RESOURCE_PATH + "textures/";
    public static final String GUI_DIRECTORY = TEXTURE_NAME_PREFIX + "textures/gui/";
    public static final String BLOCK_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "blocks/";
    public static final String ITEM_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "items/";
    public static final String MODEL_TEXTURE_DIRECTORY = TEXTURE_DIRECTORY + "models/";

    public static final String LANGUAGE_PATH = RESOURCE_PATH + "languages/";
    private static final String[] LANGUAGES_SUPPORTED = new String[] { "en_US" };

    public static int renderIdRail = 9;
}
