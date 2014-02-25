package pokemane.northernrail.common.block.rail;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.RailRegistry;
import pokemane.northernrail.api.rail.RailType;
import pokemane.northernrail.client.render.RailIconProvider;
import pokemane.northernrail.common.block.TileEntityRail;

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
		RailType railType = RailRegistry.getRailType(damage);
		if (railType == null){
			return RailIconProvider.INSTANCE.getIconFromRailType(0);
		}
		return railType.getIcon();
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
			list.add(new ItemStack(item,1,RailRegistry.getRailType(i).getRailId()));
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
		return RailRegistry.getRailType(damage).getRailTag();
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
			int metadata1 = this.field_150939_a.onBlockPlaced(world, x, y, z, side, par8, par9, par10, 0);

			if (placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, metadata1))
			{
				RailType railType = RailRegistry.getRailType(stack.getItemDamage());
				TileEntityRail tile = new TileEntityRail(railType);
				world.setTileEntity(x, y, z, tile);
				TileEntityRail tileEntityRail = (TileEntityRail)world.getTileEntity(x,y,z);
				int id = tileEntityRail.getRailType().getRailId();
				if (!world.isRemote){
					player.addChatComponentMessage(new ChatComponentText("Item Damage " + String.valueOf(stack.getItemDamage())));
					player.addChatComponentMessage(new ChatComponentText("Rail ID "+String.valueOf(railType.getRailId())));
					player.addChatComponentMessage(new ChatComponentText("Tile Entity ID "+String.valueOf(id)));
				}
				world.markBlockForUpdate(x,y,z);
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
		return super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
	}
}
