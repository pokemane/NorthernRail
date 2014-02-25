package pokemane.northernrail.core.util;

import net.minecraftforge.common.util.ForgeDirection;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.block.TileEntityRail;

import java.util.HashMap;

/**
 * Created by pokemane on 2/24/14.
 */
public class RailBlockDataManager {
	private static HashMap<BlockPosition, RailType> blockPositionRailTypeHashMap = new HashMap<BlockPosition, RailType>();

	public static void setForBlock(BlockPosition bp, RailType type) {
		BlockPosition newbp = bp.copy();
		newbp.orientation = ForgeDirection.UNKNOWN;
		blockPositionRailTypeHashMap.put(newbp,type);
	}

	public static void setForBlock(TileEntityRail tile) {
		setForBlock(new BlockPosition(tile), tile.getRailType());
	}

	public static RailType getForBlock(int x, int y, int z) {
		BlockPosition bp = new BlockPosition(x,y,z);
		RailType type = blockPositionRailTypeHashMap.get(bp);
		blockPositionRailTypeHashMap.remove(bp);
		return type;
	}

}
