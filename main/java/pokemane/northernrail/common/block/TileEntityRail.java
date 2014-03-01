package pokemane.northernrail.common.block;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.NorthernRail;
import pokemane.northernrail.common.block.rail.RailBaseNR;
import pokemane.northernrail.core.util.BlockDataManager;
import pokemane.northernrail.core.util.BlockPosition;
import pokemane.northernrail.core.util.RailFactory;

/**
 * Created by pokemane on 2/20/14.
 */
public class TileEntityRail extends TileEntity {
	public TileEntityRail() {
	}

	public short getRailId() {
		return railId;
	}

	private short railId = 0;

	public int getX(){return this.xCoord;}
	public int getY(){return this.yCoord;}
	public int getZ(){return this.zCoord;}
	public World getWorld(){return this.worldObj;}

	public IIcon getIcon(){
		RailType railType = RailRegistry.getRailType(railId);
		return railType.getIcon();
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.railId = tag.getShort(NorthernRail.RAIL_ID_TAG);
	}

	public RailType getRailType(){
		return RailRegistry.getRailType(railId);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort(NorthernRail.RAIL_ID_TAG, railId);
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
		tag.setShort(NorthernRail.RAIL_ID_TAG, railId);
		BlockDataManager.setForBlock(new BlockPosition(xCoord, yCoord, zCoord), RailFactory.createRailItemStack(railId).writeToNBT(new NBTTagCompound()));
	}
}
