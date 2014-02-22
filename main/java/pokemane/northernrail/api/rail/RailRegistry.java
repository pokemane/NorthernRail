package pokemane.northernrail.api.rail;

import pokemane.northernrail.client.render.RailIconProvider;
import pokemane.northernrail.common.block.rail.RailDefault;


import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Created by pokemane on 2/20/14.
 */
public final class RailRegistry {
	public static Hashtable<Integer, RailType> railTypeRegistry = new Hashtable<Integer, RailType>();

	public static void addRailType(RailType railType){
		if(!(railTypeRegistry.contains(railType)))
			railTypeRegistry.put(railType.getRailId(), railType);
	}

	public RailType getRailType(int key){
		return railTypeRegistry.get(key);
	}


}
