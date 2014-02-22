package pokemane.northernrail.client.render;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import pokemane.northernrail.api.rail.IRailIconProvider;
import pokemane.northernrail.api.rail.RailType;

/**
 * Created by pokemane on 2/22/14.
 */
public class RailIconProvider implements IRailIconProvider {
	@Override
	public IIcon getIconFromRailType(IIconRegister iconRegister, RailType railType) {
		return iconRegister.registerIcon(railType.getTrackTag());
	}
}
