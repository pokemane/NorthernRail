package pokemane.northernrail.api.rail;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.common.block.TileEntityRail;

import java.util.Random;

/**
 * Created by pokemane on 2/21/14.
 */
public abstract class RailLogicBase implements IRailLogic {

	private Block block;
	private TileEntity tileEntity;


	@Override
	public int getX() {
		return this.tileEntity.xCoord;
	}

	@Override
	public int getY() {
		return this.tileEntity.yCoord;
	}

	@Override
	public int getZ() {
		return this.tileEntity.zCoord;
	}

	@Override
	public World getWorld() {
		return this.tileEntity.getWorldObj();
	}

	@Override
	public IIcon getIcon() {
		return null;
	}

	@Override
	public void setTile(TileEntityRail tileEntityRail) {
		this.tileEntity = tileEntityRail;
	}

	@Override
	public TileEntity getTile() {
		return null;
	}

	@Override
	public boolean isPowered() {
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

	}

	@Override
	public void onMinecartPass(EntityMinecart entityMinecart) {

	}

	@Override
	public int getBasicRailMetadata(EntityMinecart entityMinecart) {
		return 0;
	}

	@Override
	public void onBlockPlaced() {

	}

	@Override
	public void onBlockRemoved() {

	}

	@Override
	public void onBlockPlacedBy() {

	}

	@Override
	public void onBlockActivated(EntityPlayer entityPlayer) {

	}

	@Override
	public void onNeighborBlockChange() {

	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public boolean isFlexibleRail() {
		return false;
	}

	@Override
	public boolean canMakeSlopes() {
		return false;
	}

	@Override
	public float getMaxRailSpeed() {
		return 0;
	}

	@Override
	public void updateEntity() {

	}

	@Override
	public void setRenderType() {

	}

	@Override
	public int getRenderType() {
		return 0;
	}

	@Override
	public RailType getRailType() {
		return null;
	}

	@Override
	public IRailLogic createInstance() {
		return this;
	}
}
