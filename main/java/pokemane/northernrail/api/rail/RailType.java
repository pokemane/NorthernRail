package pokemane.northernrail.api.rail;

/**
 * Created by pokemane on 2/18/14.
 */
public final class RailType {
	//name of the logic
	private final String railTag;
	//tooltips for eventually making tooltips
	private final String tooltip;
	private final IRailLogic railClass;
	private final short railId;

	public RailType(short railId, String railTag, IRailLogic railClass) {
		this(railId ,railTag, railClass, null);
	}

	public RailType(short railId, String railTag, IRailLogic railClass, String tooltip) {
		this.railId = railId;
		this.railClass = railClass;
		this.tooltip = tooltip;
		this.railTag = railTag;
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

}
