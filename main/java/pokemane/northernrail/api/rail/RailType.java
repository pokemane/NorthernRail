package pokemane.northernrail.api.rail;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import javax.imageio.metadata.IIOMetadataController;
import java.util.HashMap;

/**
 * Created by pokemane on 2/18/14.
 */
public final class RailType {
	//name of the logic
	private final String railTag;
	//tooltips for eventually making tooltips
	private final String tooltip;
	private final IRailLogic railClass;
	private final int railId;

	private HashMap<Integer, IIcon[]> iconHashMap = new HashMap<Integer, IIcon[]>();

	public RailType(short railId, String railTag, IRailLogic railClass) {
		this(railId ,railTag, railClass, null);
	}

	public RailType(short railId, String railTag, IRailLogic railClass, String tooltip) {
		this.railId = railId;
		this.railClass = railClass;
		this.tooltip = tooltip;
		this.railTag = railTag;
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

	public IIcon getIcon(int state, int damage, boolean powered) {
		return null;
	}

	public void registerIcons(IIconRegister iconRegister) {

	}

}
