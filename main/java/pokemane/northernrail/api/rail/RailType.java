package pokemane.northernrail.api.rail;


import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

/**
 * Created by pokemane on 2/18/14.
 */
public final class RailType {
	//Block is for when we instantiate this to create new rail blocks.
	// used to grab the itemstacks.
	public static Block railBlock;
	//name of the rail
	private final String trackTag;
	//tooltips for eventually making tooltips
	private final String tooltip;
	private final IRailBase railClass;

	public RailType(String trackTag, IRailBase railClass) {
		this(trackTag, railClass, null);
	}

	public RailType(String trackTag, IRailBase railClass, String tooltip){
		this.railClass = railClass;
		this.tooltip = tooltip;
		this.trackTag = trackTag;
	}

	public String getTrackTag() {
		return trackTag;
	}

	public String getTooltip() {
		return tooltip;
	}

	public IRailBase getRailClass() {
		return railClass;
	}

	public ItemStack getItem(int quantity){
		if (railBlock != null){
			return new ItemStack(railBlock,quantity);
		}
		return null;
	}


}
