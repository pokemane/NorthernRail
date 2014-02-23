package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.common.block.TileEntityRail;

/**
 * Created by pokemane on 2/20/14.
 */
public class ItemBlockRail extends ItemBlock {
	RailBaseNR railBaseNR;
	public ItemBlockRail(Block block) {
		super(block);
		this.railBaseNR = (RailBaseNR)block;
		setMaxDamage(1000);
		setHasSubtypes(true);
	}

	/**
	 * Gets an icon index based on an item's damage value
	 *
	 * @param damage
	 */
	@Override
	public IIcon getIconFromDamage(int damage) {
		RailType railType = RailRegistry.getRailType(damage);
		if (railType == null){
			return null;
		}
		return railType.getIcon(railType);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 *
	 * @param stack
	 * @param world
	 * @param player
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		RailType railType = RailRegistry.getRailType(stack.getItemDamage());
		String message = String.valueOf(railType) + " " + String.valueOf(stack.getItemDamage());
		ChatComponentText chatmessage = new ChatComponentText(message);
		if(world.isRemote){
			player.addChatComponentMessage(chatmessage);
		}
		message = String.valueOf(this.getIconFromDamage(stack.getItemDamage()));
		if(world.isRemote){
			player.addChatComponentMessage(chatmessage);
		}
		return stack;
	}

	/**
	 * Called to actually place the block, after the location is determined
	 * and all permission checks have been made.
	 *
	 * @param stack    The item stack that was used to place the block. This can be changed inside the method.
	 * @param player   The player who is placing the block. Can be null if the block is not being placed by a player.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param side     The side the player (or machine) right-clicked on.
	 * @param hitX
	 * @param hitY
	 * @param hitZ
	 * @param metadata
	 */
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		if (!world.setBlock(x, y, z, railBaseNR, metadata, 3))
		{
			return false;
		}

		if (world.getBlock(x, y, z) == railBaseNR)
		{
			railBaseNR.onBlockPlacedBy(world, x, y, z, player, stack);
			railBaseNR.onPostBlockPlaced(world, x, y, z, metadata);
		}

		return true;
	}
}
