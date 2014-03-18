package pokemane.northernrail.core.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pokemane.northernrail.api.rail.RailType;

import java.util.ArrayList;

/**
 * Created by pokemane on 3/6/14.
 */
public class RailConnectionHelper {
	int numTerminals;
	ForgeDirection orientation;
	boolean isMirrored;

	public RailConnectionHelper(int numTerminals, ForgeDirection orientation, boolean isMirrored) {
		this.numTerminals = numTerminals;
		this.orientation = orientation;
		this.isMirrored = isMirrored;
	}

	public RailConnectionHelper(int numTerminals, ForgeDirection orientation) {
		this.numTerminals = numTerminals;
		this.orientation = orientation;
		this.isMirrored = false;
	}

	public boolean canNeighborsConnect(World world, BlockPosition rail, BlockPosition neighbor) {
		TileEntity railTile = world.getTileEntity(rail.x, rail.y, rail.z);
		TileEntity neighborRailTile = world.getTileEntity(neighbor.x, neighbor.y, neighbor.z);
		return false;
	}

	public ArrayList<ForgeDirection> getPossibleConnections() {
		ForgeDirection axis = ForgeDirection.UP;
		if (isMirrored) axis = axis.getOpposite();
		ArrayList<ForgeDirection> connList = new ArrayList<ForgeDirection>();
		connList.add(orientation);
		ForgeDirection newDir = orientation;
		for (int i = 0; i < numTerminals; ++i) {
			connList.add(newDir.getRotation(axis));
			newDir = newDir.getRotation(axis);
		}
		return connList;
	}

	public ForgeDirection rotateToFit() {

		return null;
	}

	public boolean canConnectHere() {

		boolean flag = false;

		return flag;
	}
}
