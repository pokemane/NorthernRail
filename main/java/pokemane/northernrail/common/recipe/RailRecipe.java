package pokemane.northernrail.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import pokemane.northernrail.common.block.rail.ItemBlockRail;

/**
 * Created by pokemane on 2/22/14.
 */
public class RailRecipe implements IRecipe{
	/**
	 * Used to check if a recipe matches current crafting inventory
	 *
	 * @param var1
	 * @param var2
	 */
	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		return false;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 *
	 * @param var1
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		return null;
	}

	/**
	 * Returns the size of the recipe area
	 */
	@Override
	public int getRecipeSize() {
		return 0;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}
}
