package pokemane.northernrail.api.rail;

import java.util.Hashtable;

/**
 * Created by pokemane on 2/20/14.
 */
public final class RailRegistry {
	public static final Hashtable<Short, RailType> railTypeRegistry = new Hashtable<Short, RailType>();

	public static void addRailType(RailType railType){
		if(!(railTypeRegistry.contains(railType)))
			railTypeRegistry.put(railType.getRailId(), railType);
	}

	public static RailType getRailType(short key){
		return railTypeRegistry.get(key);
	}

	public static short getNextAvailableRailId(){
		return (short)railTypeRegistry.size();
	}
}
