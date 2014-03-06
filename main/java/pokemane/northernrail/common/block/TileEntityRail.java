package pokemane.northernrail.common.block;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.NorthernRail;
import pokemane.northernrail.common.block.rail.RailBaseNR;
import pokemane.northernrail.core.util.BlockDataManager;
import pokemane.northernrail.core.util.BlockPosition;

/**
 * Created by pokemane on 2/20/14.
 */
public class TileEntityRail extends TileEntity {
	public TileEntityRail() {
		super();
	}

	public short getRailId() {
		return railId;
	}

	private short railId = 0;

	public int getX(){return this.xCoord;}
	public int getY(){return this.yCoord;}
	public int getZ(){return this.zCoord;}
	public World getWorld(){return this.worldObj;}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		readItemData(tag);
		super.readFromNBT(tag);
		System.out.println(tag);
	}

	public void readItemData(NBTTagCompound tag) {
		this.railId = tag.getShort(NorthernRail.RAIL_ID_TAG);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		writeItemData(tag);
		super.writeToNBT(tag);
		System.out.println(tag);
	}

	public void writeItemData(NBTTagCompound tag) {
		tag.setShort(NorthernRail.RAIL_ID_TAG, railId);
	}

	public RailType getRailType(){
		return RailRegistry.getRailType(railId);
	}

	@Override
	public int getBlockMetadata() {
		this.blockMetadata = this.worldObj.getBlockMetadata(this.xCoord,this.yCoord,this.zCoord);
		return this.blockMetadata;
	}

	/**
	 * Determines if this TileEntity requires update calls.
	 *
	 * @return True if you want updateEntity() to be called, false if not
	 */
	@Override
	public boolean canUpdate() {
		return false;
	}

	/**
	 * Called from Chunk.setBlockIDWithMetadata, determines if this tile entity should be re-created when the ID, or Metadata changes.
	 * Use with caution as this will leave straggler TileEntities, or create conflicts with other TileEntities if not used properly.
	 *
	 * @param oldBlock
	 * @param newBlock
	 * @param oldMeta  The old metadata of the block
	 * @param newMeta  The new metadata of the block (May be the same)
	 * @param world    Current world
	 * @param x        X Position
	 * @param y        Y Position
	 * @param z        Z Position       @return True to remove the old tile entity, false to keep it in tact {and create a new one if the new values specify to}
	 */
	@Override
	public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
		//return super.shouldRefresh(oldBlock, newBlock, oldMeta, newMeta, world, x, y, z);
		return false;
	}

	@Override
	public void updateEntity() {
		RailBaseNR.updateNRRailBlockState(this.worldObj,this.xCoord,this.yCoord,this.zCoord);
	}

	/**
	 * Overriden in a sign to provide the text.
	 */
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);

		return super.getDescriptionPacket();
	}

	public void onBlockBroken() {
		NBTTagCompound tag = new NBTTagCompound();
		writeItemData(tag);
		System.out.println("onBlockBroken "+tag);
		BlockDataManager.setForBlock(new BlockPosition(xCoord, yCoord, zCoord), tag);
	}
}
