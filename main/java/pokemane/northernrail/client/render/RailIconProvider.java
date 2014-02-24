package pokemane.northernrail.client.render;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import pokemane.northernrail.api.rail.IRailIconProvider;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.NorthernRail;

import java.util.HashMap;

/**
 * Created by pokemane on 2/22/14.
 */
public class RailIconProvider implements IRailIconProvider {
	public static final RailIconProvider INSTANCE = new RailIconProvider();
	public final HashMap<Integer, IIcon> railTypeIIconHashMap = new HashMap();

	public void registerIcons(IIconRegister iconRegister){
		if (railTypeIIconHashMap.isEmpty()){
			for (RailType railType : RailRegistry.railTypeRegistry.values()){
				this.railTypeIIconHashMap.put(railType.getRailId(), iconRegister.registerIcon("northernrail:" + railType.getRailTag()));
			}
		}
	}

	@Override
	public IIcon getIconFromRailType(RailType railType) {
			return this.getIconFromRailType(railType.getRailId());
	}

	public IIcon getIconFromRailType(int id) {
		return this.railTypeIIconHashMap.get(id);
	}
}