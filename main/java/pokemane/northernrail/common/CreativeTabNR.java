package pokemane.northernrail.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by pokemane on 2/15/14.
 */
public class CreativeTabNR extends CreativeTabs {

    // Must supply CreativeTabs.getNextID() for the int param.
    public CreativeTabNR(int par1, String par2){
        super(par1,par2);
    }

    @Override
    public Item getTabIconItem() {
        //TODO add an item to display here
        return null;
    }
}
