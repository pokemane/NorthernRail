package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.common.NorthernRailLoader;
import pokemane.northernrail.common.block.TileEntityRail;

import java.util.Random;

/**
 * Created by pokemane on 2/20/14.
 */
public class RailBaseNR extends BlockRailBase {

	public IRailLogic rail;

	public RailBaseNR() {
		super(false);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setResistance(3.5F);
		setHardness(1.05F);
		setStepSound(soundTypeMetal);
		setCreativeTab(NorthernRailLoader.creativeTabNR);
		//todo TE and rendering things maybe
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 *
	 * @param p_149691_1_
	 * @param p_149691_2_
	 */
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return this.rail.getIcon();
	}

	public TileEntity createTileEntity(World world, int metadata){
		return new TileEntityRail();
	}

	protected RailBaseNR(boolean p_i45389_1_) {
		super(p_i45389_1_);
	}

	/**
	 * Returns true if the block is power related rail.
	 */
	@Override
	public boolean isPowered() {
		return super.isPowered();
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 *
	 * @param p_149668_1_
	 * @param p_149668_2_
	 * @param p_149668_3_
	 * @param p_149668_4_
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return super.isOpaqueCube();
	}

	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 *
	 * @param p_149731_1_
	 * @param p_149731_2_
	 * @param p_149731_3_
	 * @param p_149731_4_
	 * @param p_149731_5_
	 * @param p_149731_6_
	 */
	@Override
	public MovingObjectPosition collisionRayTrace(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_) {
		return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 *
	 * @param p_149719_1_
	 * @param p_149719_2_
	 * @param p_149719_3_
	 * @param p_149719_4_
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock() {
		return super.renderAsNormalBlock();
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType() {
		return super.getRenderType();
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 *
	 * @param p_149745_1_
	 */
	@Override
	public int quantityDropped(Random p_149745_1_) {
		return super.quantityDropped(p_149745_1_);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 *
	 * @param p_149742_1_
	 * @param p_149742_2_
	 * @param p_149742_3_
	 * @param p_149742_4_
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
		return super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 *
	 * @param p_149726_1_
	 * @param p_149726_2_
	 * @param p_149726_3_
	 * @param p_149726_4_
	 */
	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 *
	 * @param p_149695_1_
	 * @param p_149695_2_
	 * @param p_149695_3_
	 * @param p_149695_4_
	 * @param p_149695_5_
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_) {
		super.onNeighborBlockChange(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
	}

	@Override
	protected void func_150048_a(World p_150048_1_, int p_150048_2_, int p_150048_3_, int p_150048_4_, int p_150048_5_, int p_150048_6_, Block p_150048_7_) {
		super.func_150048_a(p_150048_1_, p_150048_2_, p_150048_3_, p_150048_4_, p_150048_5_, p_150048_6_, p_150048_7_);
	}

	@Override
	protected void func_150052_a(World p_150052_1_, int p_150052_2_, int p_150052_3_, int p_150052_4_, boolean p_150052_5_) {
		super.func_150052_a(p_150052_1_, p_150052_2_, p_150052_3_, p_150052_4_, p_150052_5_);
	}

	/**
	 * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
	 * and stop pistons
	 */
	@Override
	public int getMobilityFlag() {
		return super.getMobilityFlag();
	}

	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	/**
	 * Return true if the rail can make corners.
	 * Used by placement logic.
	 *
	 * @param world The world.
	 * @param y     The rail Y coordinate.
	 * @param x     The rail X coordinate.
	 * @param z     The rail Z coordinate.
	 * @return True if the rail can make corners.
	 */
	@Override
	public boolean isFlexibleRail(IBlockAccess world, int y, int x, int z) {
		return super.isFlexibleRail(world, y, x, z);
	}

	/**
	 * Returns true if the rail can make up and down slopes.
	 * Used by placement logic.
	 *
	 * @param world The world.
	 * @param x     The rail X coordinate.
	 * @param y     The rail Y coordinate.
	 * @param z     The rail Z coordinate.
	 * @return True if the rail can make slopes.
	 */
	@Override
	public boolean canMakeSlopes(IBlockAccess world, int x, int y, int z) {
		return super.canMakeSlopes(world, x, y, z);
	}

	/**
	 * Return the rail's metadata (without the power bit if the rail uses one).
	 * Can be used to make the cart think the rail something other than it is,
	 * for example when making diamond junctions or switches.
	 * The cart parameter will often be null unless it it called from EntityMinecart.
	 * <p/>
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
	 * @param world The world.
	 * @param cart  The cart asking for the metadata, null if it is not called by EntityMinecart.
	 * @param x     The rail Y coordinate.
	 * @param y     The rail X coordinate.
	 * @param z     The rail Z coordinate.
	 * @return The metadata.
	 */
	@Override
	public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z) {
		return super.getBasicRailMetadata(world, cart, x, y, z);
	}

	/**
	 * Returns the max speed of the rail at the specified position.
	 *
	 * @param world The world.
	 * @param cart  The cart on the rail, may be null.
	 * @param y     The rail Y coordinate.
	 * @param x     The rail X coordinate.
	 * @param z     The rail Z coordinate.
	 * @return The max speed of the current rail.
	 */
	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, int y, int x, int z) {
		return super.getRailMaxSpeed(world, cart, y, x, z);
	}

	/**
	 * This function is called by any minecart that passes over this rail.
	 * It is called once per update tick that the minecart is on the rail.
	 *
	 * @param world The world.
	 * @param cart  The cart on the rail.
	 * @param y     The rail X coordinate.
	 * @param x     The rail Y coordinate.
	 * @param z     The rail Z coordinate.
	 */
	@Override
	public void onMinecartPass(World world, EntityMinecart cart, int y, int x, int z) {
		super.onMinecartPass(world, cart, y, x, z);
	}

	@Override
	public void setRenderType(int value) {
		super.setRenderType(value);
	}
}
