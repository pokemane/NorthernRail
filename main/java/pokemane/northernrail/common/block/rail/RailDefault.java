package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.api.rail.RailLogicBase;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.block.TileEntityRail;

/**
 * Created by pokemane on 2/20/14.
 */
public class RailDefault extends RailLogicBase {
	@Override
	public int getX() {
		return super.getX();
	}

	@Override
	public int getY() {
		return super.getY();
	}

	@Override
	public int getZ() {
		return super.getZ();
	}

	@Override
	public World getWorld() {
		return super.getWorld();
	}

	@Override
	public void setTile(TileEntityRail tileEntityRail) {
		super.setTile(tileEntityRail);
	}

	@Override
	public TileEntity getTile() {
		return super.getTile();
	}

	@Override
	public boolean isPowered() {
		return super.isPowered();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
	}

	@Override
	public void onMinecartPass(EntityMinecart entityMinecart) {
		super.onMinecartPass(entityMinecart);
	}

	@Override
	public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z) {
		return super.getBasicRailMetadata(world,cart,x,y,z);
	}

	@Override
	public void onBlockPlaced() {
		super.onBlockPlaced();
	}

	@Override
	public void onBlockRemoved() {
		super.onBlockRemoved();
	}

	@Override
	public void onBlockPlacedBy() {
		super.onBlockPlacedBy();
	}

	@Override
	public boolean onBlockActivated(EntityPlayer entityPlayer) {
		return super.onBlockActivated(entityPlayer);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		super.onNeighborBlockChange(world, x, y, z, neighborBlock);
	}

	@Override
	public boolean canUpdate() {
		return super.canUpdate();
	}

	@Override
	public boolean isFlexibleRail() {
		return super.isFlexibleRail();
	}

	@Override
	public boolean canMakeSlopes() {
		return super.canMakeSlopes();
	}

	@Override
	public float getMaxRailSpeed(EntityMinecart cart) {
		return super.getMaxRailSpeed(cart);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
	}

	@Override
	public void onRedstoneSignal(World world, int x, int y, int z, int side, int meta, Block block) {
		super.onRedstoneSignal(world, x, y, z, side, meta, block);
	}
}
