package pokemane.northernrail.api.rail;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

/**
 * Created by pokemane on 2/18/14.
 */
public final class RailType {
	//name of the logic
	private final String railTag;
	//tooltips for eventually making tooltips
	private final String tooltip;
	private final IRailLogic railClass;
	private final IRailIconProvider iconProvider;
	private final short railId;

	public RailType(short railId, String railTag, IRailLogic railClass, IRailIconProvider iconProvider) {
		this(railId ,railTag, railClass, iconProvider, null);
	}

	public RailType(short railId, String railTag, IRailLogic railClass, IRailIconProvider iconProvider, String tooltip) {
		this.railId = railId;
		this.railClass = railClass;
		this.tooltip = tooltip;
		this.railTag = railTag;
		this.iconProvider = iconProvider;
	}

	public short getRailId() {
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

	public IRailLogic createLogicFromType(){
		return this.getRailClass().createInstance();
	}

	public IIcon getIcon(){
		return iconProvider.getIconFromRailId(railId);
	}

}
