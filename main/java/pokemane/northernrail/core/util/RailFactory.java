package pokemane.northernrail.core.util;

import net.minecraft.item.ItemStack;
import pokemane.northernrail.common.block.rail.RailBaseNR;

/**
 * Created by pokemane on 2/28/14.
 */
public class RailFactory {
	public static ItemStack createRailItemStack(short railId, int qty) {
		return new ItemStack(new RailBaseNR(), qty, railId);
	}

	public static ItemStack createRailItemStack(short railId) {
		return createRailItemStack(railId, 1);
	}
}
