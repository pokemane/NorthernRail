package pokemane.northernrail.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

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


}
