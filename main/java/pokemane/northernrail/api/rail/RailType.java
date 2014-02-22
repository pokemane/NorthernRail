package pokemane.northernrail.api.rail;


import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import pokemane.northernrail.client.render.IRailIconProvider;

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
	private final IRailLogic railClass;
	private final IRailIconProvider iconProvider;

	public RailType(String trackTag, IRailLogic railClass, IRailIconProvider iconProvider) {
		this(trackTag, railClass, iconProvider, null);
	}

	public RailType(String trackTag, IRailLogic railClass, IRailIconProvider iconProvider, String tooltip) {
		this.railClass = railClass;
		this.tooltip = tooltip;
		this.trackTag = trackTag;
		this.iconProvider = iconProvider;
	}

	public String getTrackTag() {
		return trackTag;
	}

	public String getTooltip() {
		return tooltip;
	}

	public IRailLogic getRailClass() {
		return railClass;
	}

	public ItemStack getItemStack(int quantity){
		if (railBlock != null){
			return new ItemStack(railBlock,quantity);
		}
		return null;
	}

	public IRailLogic createRailFromType(){
		return this.railClass.createInstance();
	}

	public IIcon getIcon(){
		return iconProvider.getIconFromRailType(this);
	}

}
