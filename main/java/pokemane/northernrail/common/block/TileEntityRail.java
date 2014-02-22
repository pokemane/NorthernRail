package pokemane.northernrail.common.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.api.rail.RailType;

/**
 * Created by pokemane on 2/20/14.
 */
public class TileEntityRail extends TileEntity {

	public IRailLogic rail;
	private RailType railType;

	public TileEntityRail(RailType railType) {
		this.railType = railType;
		this.rail = railType.createRailFromType();
	}

	public int getX(){return this.xCoord;}
	public int getY(){return this.yCoord;}
	public int getZ(){return this.zCoord;}
	public World getWorld(){return this.worldObj;}

	public IIcon getIcon(){
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_) {
		super.readFromNBT(p_145839_1_);
	}

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_) {
		super.writeToNBT(p_145841_1_);
	}

	@Override
	public int getBlockMetadata() {
		return super.getBlockMetadata();
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
}
