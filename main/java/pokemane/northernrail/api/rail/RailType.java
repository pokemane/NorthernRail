package pokemane.northernrail.api.rail;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by pokemane on 2/18/14.
 */
public final class RailType {
	//Block is for when we instantiate this to create new rail textures.blocks.
	// used to grab the itemstacks.
	public static Block railBlock;
	//name of the rail
	private final String railTag;
	//tooltips for eventually making tooltips
	private final String tooltip;
	private final IRailLogic railClass;
	private final IRailIconProvider iconProvider;
	private final int railId;

	public RailType(int railId, String railTag, IRailLogic railClass, IRailIconProvider iconProvider) {
		this(railId ,railTag, railClass, iconProvider, null);
	}

	public RailType(int railId, String railTag, IRailLogic railClass, IRailIconProvider iconProvider, String tooltip) {
		this.railId = railId;
		this.railClass = railClass;
		this.tooltip = tooltip;
		this.railTag = railTag;
		this.iconProvider = iconProvider;
	}

	public int getRailId() {
		return railId;
	}

	public String getRailTag() {
		return railTag;
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
		return this.getRailClass();
	}

	public IIcon getIcon(RailType railType){
		return iconProvider.getIconFromRailType(railType);
	}

}
