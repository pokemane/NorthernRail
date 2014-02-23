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
	public final HashMap<RailType, IIcon[]> textures = new HashMap();
	public final HashMap<RailType, IIcon> railTypeIIconHashMap = new HashMap();

	public void registerIcons(IIconRegister iconRegister){
		for (RailType railType : RailRegistry.railTypeRegistry.values()){
			this.railTypeIIconHashMap.put(railType, iconRegister.registerIcon(NorthernRail.RAIL_TEXTURE_DIRECTORY + railType.getRailTag()));
		}
	}

	@Override
	public IIcon getIconFromRailType(RailType railType) {
			return this.railTypeIIconHashMap.get(railType);
	}
}
