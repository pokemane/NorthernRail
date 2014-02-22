package pokemane.northernrail.common.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import pokemane.northernrail.common.block.rail.RailBaseNR;

/**
 * Created by pokemane on 2/22/14.
 */
public class ItemBlockRail extends ItemBlock {
	public ItemBlockRail() {
		super(new RailBaseNR());
	}
}
