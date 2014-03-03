package pokemane.northernrail.common.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.NorthernRail;
import pokemane.northernrail.core.util.BlockDataManager;
import pokemane.northernrail.core.util.BlockPosition;

/**
 * Created by pokemane on 2/20/14.
 */
public class TileEntityRail extends TileEntity {
	public TileEntityRail() {
		super();
	}

	public short getRailId() {
		return railId;
	}

	private short railId = 0;

	public int getX(){return this.xCoord;}
	public int getY(){return this.yCoord;}
	public int getZ(){return this.zCoord;}
	public World getWorld(){return this.worldObj;}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		System.out.println(tag);
		readItemData(tag);
	}

	public void readItemData(NBTTagCompound tag) {
		this.railId = tag.getShort(NorthernRail.RAIL_ID_TAG);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		System.out.println(tag);
		writeItemData(tag);
	}

	public void writeItemData(NBTTagCompound tag) {
		tag.setShort(NorthernRail.RAIL_ID_TAG, railId);
	}

	public RailType getRailType(){
		return RailRegistry.getRailType(railId);
	}

	@Override
	public int getBlockMetadata() {
		this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord,this.yCoord,this.zCoord);
		return this.blockMetadata;
	}

	/**
	 * Determines if this TileEntity requires update calls.
	 *
	 * @return True if you want updateEntity() to be called, false if not
	 */
	@Override
	public boolean canUpdate() {
		return false;
	}

	public void onBlockBroken() {
		NBTTagCompound tag = new NBTTagCompound();
		writeItemData(tag);
		System.out.println("onBlockBroken "+tag);
		BlockDataManager.setForBlock(new BlockPosition(xCoord, yCoord, zCoord), tag);
	}
}
