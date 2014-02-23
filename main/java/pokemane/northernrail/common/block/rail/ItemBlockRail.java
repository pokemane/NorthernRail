package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
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
		return railType.getIcon();
	}

	/**
	 * Returns 0 for /terrain.png, 1 for /gui/items.png
	 */
	@Override
	public int getSpriteNumber() {
		return 0;
	}

	@Override
	public void registerIcons(IIconRegister par1IconRegister) {	}

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
	 * @param par1ItemStack
	 * @param par2EntityPlayer
	 * @param par3World
	 * @param par4
	 * @param par5
	 * @param par6
	 * @param par7
	 * @param par8
	 * @param par9
	 * @param par10
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
	}

}
