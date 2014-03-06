package pokemane.northernrail.common.block.rail;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.IBlockAccess;
import pokemane.northernrail.api.rail.RailLogicBase;

/**
 * Created by pokemane on 3/4/14.
 */
public class RailJunction extends RailLogicBase {
	@Override
	public void onMinecartPass(EntityMinecart entityMinecart) {
		super.onMinecartPass(entityMinecart);
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
		if (cart != null){
			float yaw = cart.prevRotationYaw;
			yaw %= 180f;
			while (yaw <=0 ) yaw += 180f;
			if (45f <= yaw && yaw <= 135f) {
				return 0;
			}
			else {
				return 1;
			}
		}
		else return world.getBlockMetadata(x,y,z);
		//return super.getBasicRailMetadata(world, cart, x, y, z);
	}

	@Override
	public boolean canMakeSlopes() {
		return false;
	}
}
