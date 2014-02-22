package pokemane.northernrail.api.rail;

import net.minecraft.util.IIcon;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.api.rail.RailType;

/**
 * Created by pokemane on 2/21/14.
 */
public interface IRailIconProvider {
	public abstract IIcon getIconFromRailType(RailType railType);
}
