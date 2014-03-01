package pokemane.northernrail.core.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import pokemane.northernrail.api.rail.RailType;

import java.util.HashMap;

/**
 * Created by pokemane on 2/24/14.
 */
public class BlockDataManager {
	private static HashMap<BlockPosition, NBTTagCompound> blockPositionRailIdHashMap = new HashMap<BlockPosition, NBTTagCompound>();

	public static void setForBlock(BlockPosition bp, NBTTagCompound tag) {
		BlockPosition newbp = bp.copy();
		newbp.orientation = ForgeDirection.UNKNOWN;
		blockPositionRailIdHashMap.put(newbp, tag);
	}

	public static void setForBlock(TileEntity tile) {

		NBTTagCompound tag = new NBTTagCompound();
		tile.writeToNBT(tag);
		setForBlock(new BlockPosition(tile), tag);
	}

	public static NBTTagCompound getForBlock(int x, int y, int z) {
		BlockPosition bp = new BlockPosition(x,y,z);
		NBTTagCompound tag = blockPositionRailIdHashMap.get(bp);
		blockPositionRailIdHashMap.remove(bp);
		return tag;
	}

}
