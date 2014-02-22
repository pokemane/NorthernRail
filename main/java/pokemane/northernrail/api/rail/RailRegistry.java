package pokemane.northernrail.api.rail;

import pokemane.northernrail.client.render.RailIconProvider;
import pokemane.northernrail.common.block.rail.RailDefault;

import java.util.ArrayList;

/**
 * Created by pokemane on 2/20/14.
 */
public final class RailRegistry {
	public static ArrayList<RailType> railTypeArrayList;

	public void addRailType(RailType railType){
		boolean flag = true;
		for (RailType type : railTypeArrayList){
			if (railType.getRailId() == type.getRailId()){
				flag = false;
			}
		}
		if (flag){
			railTypeArrayList.add(railType);
		}
	}

	private RailType DEFAULT = new RailType(-1,"default", new RailDefault(), new RailIconProvider());
	
}
