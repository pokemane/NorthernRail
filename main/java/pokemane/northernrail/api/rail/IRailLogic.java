package pokemane.northernrail.api.rail;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.util.IIcon;
import pokemane.northernrail.common.block.TileEntityRail;

import java.util.Random;

/**
 * Created by pokemane on 2/15/14.
 * The base for the Rail logic instances.
 */

public interface IRailLogic {

    public abstract int getX();
    public abstract int getY();
    public abstract int getZ();
    public abstract World getWorld();

	public abstract void setTile(TileEntityRail tileEntityRail);

	public abstract TileEntity getTile();

	public abstract boolean isPowered();

	public abstract void writeToNBT(NBTTagCompound nbtTagCompound);

	public abstract void readFromNBT(NBTTagCompound nbtTagCompound);

	public abstract void onMinecartPass(EntityMinecart entityMinecart);

	public abstract int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z);

	public abstract void onBlockPlaced();

	public abstract void onBlockRemoved();

	public abstract void onBlockPlacedBy();

	public abstract boolean onBlockActivated(EntityPlayer entityPlayer);

	public abstract void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock);

	public abstract boolean canUpdate();

	public abstract boolean isStraightRail();

	public abstract boolean canMakeSlopes();

	public abstract float getMaxRailSpeed(EntityMinecart cart);

	public abstract void updateEntity();

	public abstract void updateRail(World world, int x, int y, int z, int side, int metadata, Block neighborBlock);

	public abstract void changeRail(World world, int x, int y, int z, boolean bool);

}
