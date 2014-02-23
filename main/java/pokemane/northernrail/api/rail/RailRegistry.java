package pokemane.northernrail.api.rail;

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

	public static RailType getRailType(int key){
		return railTypeRegistry.get(key);
	}
}
