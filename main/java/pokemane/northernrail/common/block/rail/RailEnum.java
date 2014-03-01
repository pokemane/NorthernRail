package pokemane.northernrail.common.block.rail;

import pokemane.northernrail.api.rail.IRailIconProvider;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.client.render.RailIconProvider;

/**
 * Created by pokemane on 3/1/14.
 */
public enum RailEnum {
	DEFAULT ((short)0,"default", new RailDefault(), RailIconProvider.INSTANCE),
	GOLDEN  ((short)1, "golden", new RailDefault(), RailIconProvider.INSTANCE);

	private final short id;
	private final String tag;
	private final IRailLogic railClass;
	private final IRailIconProvider iconProvider;

	RailEnum(short id, String tag, IRailLogic railClass, IRailIconProvider iconProvider){
		this.id = id;
		this.tag = tag;
		this.railClass = railClass;
		this.iconProvider = iconProvider;
	}

}
