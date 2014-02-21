package pokemane.northernrail.api.rail;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.common.NorthernRailLoader;
import pokemane.northernrail.common.block.NRTileEntity;

import java.util.Random;

/**
 * Created by pokemane on 2/20/14.
 */
public class RailBaseNR extends BlockRailBase {

	public IRailBaseInstance rail;

	public RailBaseNR() {
		super(false);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		setResistance(3.5F);
		setHardness(1.05F);
		setStepSound(soundTypeMetal);
		setCreativeTab(NorthernRailLoader.creativeTabNR);
		//todo TE and rendering things maybe
	}

	@Override
	public boolean isPowered() {
		return super.isPowered();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
	}

	@Override
	public boolean isOpaqueCube() {
		return super.isOpaqueCube();
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_) {
		return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
		super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return super.renderAsNormalBlock();
	}

	@Override
	public int getRenderType() {
		return super.getRenderType();
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return super.quantityDropped(p_149745_1_);
	}

	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_) {
		return super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_) {
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	}

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

	@Override
	public int getMobilityFlag() {
		return super.getMobilityFlag();
	}

	@Override
	public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
		super.breakBlock(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
	}

	@Override
	public boolean isFlexibleRail(IBlockAccess world, int y, int x, int z) {
		return super.isFlexibleRail(world, y, x, z);
	}

	@Override
	public boolean canMakeSlopes(IBlockAccess world, int x, int y, int z) {
		return super.canMakeSlopes(world, x, y, z);
	}

	@Override
	public int getBasicRailMetadata(IBlockAccess world, EntityMinecart cart, int x, int y, int z) {
		return super.getBasicRailMetadata(world, cart, x, y, z);
	}

	@Override
	public float getRailMaxSpeed(World world, EntityMinecart cart, int y, int x, int z) {
		return super.getRailMaxSpeed(world, cart, y, x, z);
	}

	@Override
	public void onMinecartPass(World world, EntityMinecart cart, int y, int x, int z) {
		super.onMinecartPass(world, cart, y, x, z);
	}

	@Override
	public void setRenderType(int value) {
		super.setRenderType(value);
	}
}
