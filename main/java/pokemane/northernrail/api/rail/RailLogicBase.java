package pokemane.northernrail.api.rail;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.common.block.TileEntityRail;

/**
 * Created by pokemane on 2/21/14.
 */
public abstract class RailLogicBase implements IRailLogic {

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
	 * @param world The world.
	 * @param cart The cart asking for the metadata, null if it is not called by EntityMinecart.
	 * @param y The rail X coordinate.
	 * @param x The rail Y coordinate.
	 * @param z The rail Z coordinate.
	 * @return The metadata.
	 */
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

	public boolean isRailPositionValid(World world, int x, int y, int z, int meta) {
		boolean valid = false;
		if (!World.doesBlockHaveSolidTopSurface(world, x, y-1, z)) {
			valid = true;
		}
		if ((meta == 2) && (!World.doesBlockHaveSolidTopSurface(world, x + 1, y, z))) {
			valid = true;
		} else if ((meta == 3) && (!World.doesBlockHaveSolidTopSurface(world, x - 1, y, z))) {
			valid = true;
		} else if ((meta == 4) && (!World.doesBlockHaveSolidTopSurface(world, x, y, z - 1))) {
			valid = true;
		} else if ((meta == 5) && (!World.doesBlockHaveSolidTopSurface(world, x, y, z + 1))) {
			valid = true;
		}
		return valid;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		if (!world.isRemote)
		{
			int metadata = world.getBlockMetadata(x, y, z);
			int newMetadata = metadata;

			if (isPowered())
			{
				newMetadata = metadata & 7;
			}

			boolean flag = isRailPositionValid(world, x, y, z, newMetadata);

			if (flag)
			{
				Block thisBlock = world.getBlock(x,y,z);
				thisBlock.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
			else
			{
				updateRail(world, x,y,z, 0, newMetadata, neighborBlock);
			}
		}
	}

	@Override
	public boolean canUpdate() {
		return false;
	}

	@Override
	public boolean isStraightRail() {
		return isPowered();
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
	public void updateRail(World world, int x, int y, int z, int side, int metadata, Block neighborBlock) {

	}

	@Override
	public void changeRail(World world, int x, int y, int z, boolean bool) {

	}
}
