package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.IRailLogic;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.client.render.RailIconProvider;
import pokemane.northernrail.common.NorthernRailLoader;
import pokemane.northernrail.common.block.TileEntityRail;

import java.util.List;
import java.util.Random;

/**
 * Created by pokemane on 2/20/14.
 */
public class RailBaseNR extends BlockRailBase {

	/**
	 * Called throughout the code as a replacement for block instanceof BlockContainer
	 * Moving this to the Block base class allows for mods that wish to extend vanilla
	 * blocks, and also want to have a tile entity on that block, may.
	 * <p/>
	 * Return true from this function to specify this block has a tile entity.
	 *
	 * @param metadata Metadata of the current block
	 * @return True if block has a tile entity, false otherwise
	 */
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public RailBaseNR() {
		super(false);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setResistance(3.5F);
		setHardness(1.05F);
		setStepSound(soundTypeMetal);
		setCreativeTab(NorthernRailLoader.creativeTabNR);
	}

	/**
	 * Called when the block is placed in the world.
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param player
	 * @param stack
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		RailType railType = RailRegistry.getRailType(stack.getItemDamage());
		TileEntityRail tile = new TileEntityRail(railType);
		world.setTileEntity(x,y,z,tile);
		this.blockIcon = tile.getIcon();
	}

	/**
	 * Called after a block is placed
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param metadata
	 */
	@Override
	public void onPostBlockPlaced(World world, int x, int y, int z, int metadata) {
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		TileEntity tile = world.getTileEntity(x,y,z);
		if (tile instanceof TileEntityRail){
			return ((TileEntityRail) tile).getIcon();
		}
		return null;
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		RailIconProvider.INSTANCE.registerIcons(iconRegister);
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
	 * Called upon block activation (right click on the block.)
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param player
	 * @param side
	 * @param p_149727_7_
	 * @param p_149727_8_
	 * @param p_149727_9_
	 */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		TileEntityRail tile = (TileEntityRail)world.getTileEntity(x,y,z);
		String message = String.valueOf(tile.getBlockMetadata());
		ChatComponentText chatmessage = new ChatComponentText(message);
		if(!world.isRemote){
			player.addChatComponentMessage(chatmessage);
		}
		return true;
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 *
	 * @param world
	 * @param p_149668_2_
	 * @param p_149668_3_
	 * @param p_149668_4_
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return super.getCollisionBoundingBoxFromPool(world, p_149668_2_, p_149668_3_, p_149668_4_);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent textures.blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube() {
		return super.isOpaqueCube();
	}

	/**
	 * Ray traces through the textures.blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 *
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param param5vec3
	 * @param param6vec3
	 */
	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 param5vec3, Vec3 param6vec3) {
		return super.collisionRayTrace(world, x, y, z, param5vec3, param6vec3);
	}

	/**
	 * Updates the textures.blocks bounds based on its current state. Args: world, x, y, z
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
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 */
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
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
