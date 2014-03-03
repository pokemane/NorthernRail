package pokemane.northernrail.client.render;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import pokemane.northernrail.api.rail.IRailIconProvider;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.NorthernRail;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pokemane on 2/22/14.
 */
public class RailIconProvider implements IRailIconProvider {
	public static final RailIconProvider INSTANCE = new RailIconProvider();
	private static final HashMap<Short, IIcon> railTypeIIconHashMap = new HashMap<Short, IIcon>();

	public void registerIcons(IIconRegister iconRegister){
		for (RailType railType : RailRegistry.railTypeRegistry.values()){
			//TODO remember to change the string back to a variable later after I figure out how the fuck to do this right
			railTypeIIconHashMap.put(railType.getRailId(), iconRegister.registerIcon(NorthernRail.TEXTURE_NAME_PREFIX + railType.getRailTag()));
		}
	}

	@Override
	public IIcon getIconFromRailId(short id) {
		return railTypeIIconHashMap.get(id);
	}

	@Override
	public IIcon getIconFromRailType(RailType railType) {
		return this.getIconFromRailId(railType.getRailId());
	}

	public ArrayList<IIcon> getRegistryListing() {
		ArrayList<IIcon> list = new ArrayList<IIcon>();
		for (short i = 0; i < railTypeIIconHashMap.size(); i++) {
			list.add(railTypeIIconHashMap.get(i));
		}
		return list;
	}
}