package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.client.render.RailIconProvider;

import java.util.List;

/**
 * Created by pokemane on 2/20/14.
 */
public class ItemBlockRail extends ItemBlock {
	RailBaseNR railBaseNR;
	public ItemBlockRail(Block block) {
		super(block);
		this.railBaseNR = (RailBaseNR) block;
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
		if (damage < 0){
			return RailIconProvider.INSTANCE.getIconFromRailId((short)0);
		}
		return RailIconProvider.INSTANCE.getIconFromRailId((short)damage);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		RailIconProvider.INSTANCE.registerIcons(iconRegister);
	}

	@Override
	public boolean isDamageable() {
		return true;
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 *
	 * @param item
	 * @param tab
	 * @param list
	 */
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < RailRegistry.railTypeRegistry.size(); i++){
			list.add(new ItemStack(item,1,i));
		}
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 *
	 * @param itemStack
	 */
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		int damage = itemStack.getItemDamage();
		return RailRegistry.getRailType((short)damage).getRailTag();
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

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 *
	 * @param stack
	 * @param world
	 * @param player
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		RailType railType = RailRegistry.getRailType((short)stack.getItemDamage());
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
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, 0);
	}
}
