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

	public abstract IIcon getIcon();

	public abstract void setTile(TileEntityRail tileEntityRail);

	public abstract TileEntity getTile();

	public abstract boolean isPowered();

	public abstract void writeToNBT(NBTTagCompound nbtTagCompound);

	public abstract void readFromNBT(NBTTagCompound nbtTagCompound);

	public abstract void onMinecartPass(EntityMinecart entityMinecart);

	public abstract int getBasicRailMetadata(EntityMinecart entityMinecart);

	public abstract void onBlockPlaced();

	public abstract void onBlockRemoved();

	public abstract void onBlockPlacedBy();

	public abstract void onBlockActivated(EntityPlayer entityPlayer);

	public abstract void onNeighborBlockChange();

	public abstract boolean canUpdate();

	public abstract boolean isFlexibleRail();

	public abstract boolean canMakeSlopes();

	public abstract float getMaxRailSpeed();

	public abstract void updateEntity();

	public abstract void setRenderType();

	public abstract int getRenderType();

	public abstract RailType getRailType();

	public abstract IRailLogic createInstance();

}
