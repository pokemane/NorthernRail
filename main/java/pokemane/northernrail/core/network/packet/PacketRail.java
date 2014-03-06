package pokemane.northernrail.core.network.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import pokemane.northernrail.common.block.TileEntityRail;

/**
 * Created by pokemane on 3/5/14.
 */
public class PacketRail extends AbstractPacket {

	private int x, y, z;
	private short railId;

	public PacketRail() {
	}

	public PacketRail(int x, int y, int z, short railId) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.railId = railId;
	}

	/**
	 * Encode the packet data into the ByteBuf stream. Complex data sets may need specific data handlers (See @link{cpw.mods.fml.common.network.ByteBuffUtils})
	 *
	 * @param ctx    channel context
	 * @param buffer the buffer to encode into
	 */
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeShort(railId);
	}

	/**
	 * Decode the packet data from the ByteBuf stream. Complex data sets may need specific data handlers (See @link{cpw.mods.fml.common.network.ByteBuffUtils})
	 *
	 * @param ctx    channel context
	 * @param buffer the buffer to decode from
	 */
	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		railId = buffer.readShort();
	}

	/**
	 * Handle a packet on the client side. Note this occurs after decoding has completed.
	 *
	 * @param player the player reference
	 */
	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	/**
	 * Handle a packet on the server side. Note this occurs after decoding has completed.
	 *
	 * @param player the player reference
	 */
	@Override
	public void handleServerSide(EntityPlayer player) {
		World world = player.worldObj;
		TileEntity tile = world.getTileEntity(x,y,z);
		if (tile instanceof TileEntityRail) {
			((TileEntityRail) tile).setRailId(railId);
		}
	}
}
