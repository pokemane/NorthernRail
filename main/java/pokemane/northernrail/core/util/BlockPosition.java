package pokemane.northernrail.core.util;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by pokemane on 2/24/14.
 */
public class BlockPosition {
	public int x,y,z;
	public ForgeDirection orientation;

	public BlockPosition(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.orientation = ForgeDirection.UNKNOWN;
	}

	public BlockPosition(int x, int y, int z, ForgeDirection orientation) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.orientation = orientation;
	}

	public BlockPosition(BlockPosition pos) {
		this.x = pos.x;
		this.y = pos.y;
		this.z = pos.z;
		this.orientation = pos.orientation;
	}

	public BlockPosition(TileEntity tile) {
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
		this.orientation = ForgeDirection.UNKNOWN;
	}

	public BlockPosition(NBTTagCompound tagCompound) {
		this.x = tagCompound.getInteger("i");
		this.y = tagCompound.getInteger("j");
		this.z = tagCompound.getInteger("k");
		this.orientation = ForgeDirection.UNKNOWN;
	}

	public BlockPosition copy() {
		return new BlockPosition(x,y,z,orientation);
	}

	public void moveRight(int step){
		switch(orientation){
			case SOUTH:
				x = x - step;
				break;
			case NORTH:
				x = x + step;
				break;
			case EAST:
				z = z + step;
				break;
			case WEST:
				z = z - step;
				break;
			default:
				break;
		}
	}

	public void moveLeft(int step) {
		moveRight(-step);
	}

	public void moveForward(int step) {
		switch (orientation){
			case UP:
				y = y + step;
				break;
			case DOWN:
				y = y - step;
				break;
			case SOUTH:
				z = z + step;
				break;
			case NORTH:
				z = z - step;
				break;
			case EAST:
				x = x + step;
				break;
			case WEST:
				x = x - step;
				break;
			default:
				break;
		}
	}

	public void moveBack(int step) {
		moveForward(-step);
	}

	public void moveUp(int step) {
		switch (orientation) {
			case EAST:
			case WEST:
			case NORTH:
			case SOUTH:
				y = y + step;
				break;
			default:
				break;
		}
	}

	public void moveDown(int step) {
		moveUp(-step);
	}

	public TileEntity getTileEntity(World world) {
		return world.getTileEntity(x,y,z);
	}

	public ForgeDirection getRelativeDirection(BlockPosition bp){
		BlockPosition newbp = bp.copy();
		ForgeDirection direction = ForgeDirection.UNKNOWN;
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			if (x + dir.offsetX == newbp.x && y + dir.offsetY == newbp.y && z + dir.offsetZ == newbp.z) {
				direction = dir;
			}
		}
		return direction;
	}

	public TileEntity getTileEntityAtPos(World world) {
		return world.getTileEntity(this.x, this.y, this.z);
	}

	public Block getBlockAtPos(World world) {
		return world.getBlock(this.x,this.y,this.z);
	}

	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof BlockPosition))
		{
			return false;
		}
		BlockPosition bp = (BlockPosition)obj;
		return bp.x == x && bp.y == y && bp.z == z && bp.orientation == orientation;
	}

	@Override
	public int hashCode()
	{
		return (x & 0xFFF) | (y & 0xFF << 8) | (z & 0xFFF << 12);
	}
}
