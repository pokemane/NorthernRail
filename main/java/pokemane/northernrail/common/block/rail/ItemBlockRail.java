package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.client.render.RailIconProvider;

/**
 * Created by pokemane on 2/20/14.
 */
public class ItemBlockRail extends ItemBlock {
	RailBaseNR railBaseNR;
	public ItemBlockRail(Block block) {
		super(block);
		this.railBaseNR = (RailBaseNR)block;
		setMaxDamage(0);
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
		return railType.getIcon();
	}

	@Override
	public boolean isDamageable() {
		return true;
	}

	/**
	 * Returns the metadata of the block which this Item (ItemBlock) can place
	 *
	 * @param metadata
	 */
	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		RailIconProvider.INSTANCE.registerIcons(iconRegister);
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
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 *
	 * @param stack
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param side
	 * @param par8
	 * @param par9
	 * @param par10
	 */
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
		Block block = world.getBlock(x, y, z);

		if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
		{
			side = 1;
		}
		else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z))
		{
			if (side == 0)
			{
				--y;
			}

			if (side == 1)
			{
				++y;
			}

			if (side == 2)
			{
				--z;
			}

			if (side == 3)
			{
				++z;
			}

			if (side == 4)
			{
				--x;
			}

			if (side == 5)
			{
				++x;
			}
		}

		if (stack.stackSize == 0)
		{
			return false;
		}
		else if (!player.canPlayerEdit(x, y, z, side, stack))
		{
			return false;
		}
		else if (y == 255 && this.field_150939_a.getMaterial().isSolid())
		{
			return false;
		}
		else if (world.canPlaceEntityOnSide(this.field_150939_a, x, y, z, false, side, player, stack))
		{
			int metadata = this.getMetadata(stack.getItemDamage());
			int metadata1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, par8, par9, par10, metadata);

			if (placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, metadata1))
			{
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), this.field_150939_a.stepSound.func_150496_b(), (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F, this.field_150939_a.stepSound.getPitch() * 0.8F);
				--stack.stackSize;
			}

			return true;
		}
		else
		{
			return false;
		}
	}

}
