package pokemane.northernrail.api.rail;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by pokemane on 2/15/14.
 */
public interface IRail {
    /**
     * Returns true if the block at the coordinates of world passed is a valid rail block (current is rail, isPoweredRail or
     * detector).
     */
    public abstract boolean isRailBlockAt(World world, int x, int y, int z);

    public abstract int getX();
    public abstract int getY();
    public abstract int getZ();
    public abstract World getWorld();

    /**
     * Return true if the parameter is a blockID for a valid rail block (current is rail, isPoweredRail or detector).
     */
    public abstract boolean isRailBlock(Block block);

    /**
     * Returns true if the block is power related rail.
     */
    public abstract boolean isPowered();

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public abstract AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z);

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     * Is normally false for rails.
     */
    public abstract boolean isOpaqueCube();

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public abstract MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 par5vec3, Vec3 par6ved3);

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public abstract void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z);

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public abstract boolean renderAsNormalBlock();

    /**
     * The type of render function that is called for this block
     */
    public abstract int getRenderType();

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public abstract int quantityDropped(Random random);

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public abstract boolean canPlaceBlockAt(World world, int x, int y, int z);

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public abstract void onBlockAdded(World world, int x, int y, int z);

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor Block
     */
    public abstract void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock);

    /**
     * onRedstoneSignal.  Called when the block receives an RS signal.
     * @param world
     * @param x
     * @param y
     * @param z
     * @param oldMetadata
     * @param newMetadata
     * @param block
     */
    public abstract void func_150048_a(World world, int x, int y, int z, int oldMetadata, int newMetadata, Block block);

    /**
     * refreshTrackShape forces the track shape to refresh.  Calls the rail logic method func_150655_a, which sets new
     * rail metadata based on its surroundings (if it can connect to anything around it).
     * @param world
     * @param x
     * @param y
     * @param z
     * @param par6
     */
    public abstract void func_150052_a(World world, int x, int y, int z, boolean par6);

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public abstract int getMobilityFlag();

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block, old
     * metadata
     */
    public abstract void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata);

    /**
     * Return true if the rail can make corners.
     * Used by placement logic.
     * @param iBlockAccess The world.
     * @param x The rail X coordinate.
     * @param y The rail Y coordinate.
     * @param z The rail Z coordinate.
     * @return True if the rail can make corners.
     */
    public abstract boolean isFlexibleRail(IBlockAccess iBlockAccess, int x, int y, int z);

    /**
     * Returns true if the rail can make up and down slopes.
     * Used by placement logic.
     * @param iBlockAccess The world.
     * @param x The rail X coordinate.
     * @param y The rail Y coordinate.
     * @param z The rail Z coordinate.
     * @return True if the rail can make slopes.
     */
    public abstract boolean canMakeSlopes(IBlockAccess iBlockAccess, int x, int y, int z);

    /**
     * Return the rail's metadata (without the power bit if the rail uses one).
     * Can be used to make the cart think the rail something other than it is,
     * for example when making diamond junctions or switches.
     * The cart parameter will often be null unless it it called from EntityMinecart.
     *
     * Valid rail metadata is defined as follows:
     * 0x0: flat track going North-South
     * 0x1: flat track going West-East
     * 0x2: track ascending to the East
     * 0x3: track ascending to the West
     * 0x4: track ascending to the North
     * 0x5: track ascending to the South
     * 0x6: WestNorth corner (connecting East and South)
     * 0x7: EastNorth corner (connecting West and South)
     * 0x8: EastSouth corner (connecting West and North)
     * 0x9: WestSouth corner (connecting East and North)
     *
     * @param iBlockAccess The world.
     * @param cart The cart asking for the metadata, null if it is not called by EntityMinecart.
     * @param y The rail X coordinate.
     * @param x The rail Y coordinate.
     * @param z The rail Z coordinate.
     * @return The metadata.
     */
    public abstract int getBasicRailMetadata(IBlockAccess iBlockAccess, EntityMinecart cart, int x, int y, int z);

    /**
     * Returns the max speed of the rail at the specified position.
     * @param world The world.
     * @param cart The cart on the rail, may be null.
     * @param x The rail X coordinate.
     * @param y The rail Y coordinate.
     * @param z The rail Z coordinate.
     * @return The max speed of the current rail.
     */
    public abstract float getMaxRailSpeed(World world, EntityMinecart cart, int x, int y, int z);

    /**
     * This function is called by any minecart that passes over this rail.
     * It is called once per update tick that the minecart is on the rail.
     * @param world The world.
     * @param cart The cart on the rail.
     * @param y The rail X coordinate.
     * @param x The rail Y coordinate.
     * @param z The rail Z coordinate.
     */
    public abstract void onMinecartPass(World world, EntityMinecart cart, int x, int y, int z);

    /**
     * Forge: Moved render type to a field and a setter.
     * This allows for a mod to change the render type
     * for vanilla rails, and any mod rails that extend
     * this class.
     *
     * Normally this value is 9.
     */
    public abstract void setRenderType(int value);

}
