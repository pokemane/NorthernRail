package pokemane.northernrail.common.block.rail;

import net.minecraft.util.IIcon;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.api.rail.RailLogicBase;

/**
 * Created by pokemane on 2/20/14.
 */
public class RailDefault extends RailLogicBase {


	@Override
	public IRailLogic createInstance() {
		return new RailDefault();
	}
}
