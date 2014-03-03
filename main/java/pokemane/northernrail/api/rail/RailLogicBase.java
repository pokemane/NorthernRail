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
import pokemane.northernrail.common.NorthernRail;
import pokemane.northernrail.common.block.TileEntityRail;

import java.util.Random;

/**
 * Created by pokemane on 2/21/14.
 */
public abstract class RailLogicBase implements IRailLogic {

	public Block block;
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
	public void setTile(TileEntityRail tileEntityRail) {
		this.tileEntity = tileEntityRail;
	}

	@Override
	public TileEntity getTile() {
		return this.tileEntity;
	}

	@Override
	public boolean isPowered() {
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tileEntity.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		tileEntity.readFromNBT(tag);
	}

	@Override
	public void onMinecartPass(EntityMinecart entityMinecart) {

	}

	@Override
	public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(isPowered())
		{
			meta = meta & 7;
		}
		return meta;
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
	public boolean onBlockActivated(EntityPlayer entityPlayer) {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		if (!world.isRemote)
		{
			int l = world.getBlockMetadata(x, y, z);
			int i1 = l;

			if (isPowered())
			{
				i1 = l & 7;
			}

			boolean flag = false;

			if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z))
			{
				flag = true;
			}

			if (i1 == 2 && !World.doesBlockHaveSolidTopSurface(world, x + 1, y, z))
			{
				flag = true;
			}

			if (i1 == 3 && !World.doesBlockHaveSolidTopSurface(world, x - 1, y, z))
			{
				flag = true;
			}

			if (i1 == 4 && !World.doesBlockHaveSolidTopSurface(world, x, y, z - 1))
			{
				flag = true;
			}

			if (i1 == 5 && !World.doesBlockHaveSolidTopSurface(world, x, y, z + 1))
			{
				flag = true;
			}

			if (flag)
			{
				block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
			else
			{
				onRedstoneSignal(world, x, y, z, l, i1, neighborBlock);
			}
		}
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public boolean isFlexibleRail() {
		return !isPowered();
	}

	@Override
	public boolean canMakeSlopes() {
		return true;
	}

	@Override
	public float getMaxRailSpeed(EntityMinecart cart) {
		return 0;
	}

	@Override
	public void updateEntity() {

	}

	@Override
	public void onRedstoneSignal(World world, int x, int y, int z, int side, int meta, Block block) {

	}
}
