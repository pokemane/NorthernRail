package pokemane.northernrail.testing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pokemane.northernrail.client.render.RailIconProvider;
import pokemane.northernrail.common.NorthernRail;
import pokemane.northernrail.common.NorthernRailLoader;

/**
 * Created by pokemane on 2/15/14.
 */
public class TestRail extends NRRailBlockBase {

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public TestRail(){
        super(false);
	    setCreativeTab(NorthernRail.TAB);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return meta >= 6 ? this.icon : this.blockIcon;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		NRRailBlockBase block = (NRRailBlockBase)world.getBlock(x,y,z);
		int meta = block.getBasicRailMetadata(world,null,x,y,z);
		String message = "RailRegistry " + RailIconProvider.INSTANCE.getRegistryListing();
		ChatComponentText chatmessage = new ChatComponentText(message);
		if (!world.isRemote){
			player.addChatComponentMessage(chatmessage);
		}
		return false;
	}

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        super.registerBlockIcons(iconRegister);
        this.icon = iconRegister.registerIcon(this.getTextureName() + "_turned");
    }

    @Override
    public void func_150048_a(World world, int x, int y, int z, int side, int meta, Block block){
        if (block.canProvidePower() && (new NRRailLogic(world,x,y,z).countAdjacentRails()) == 3){
            this.refreshTrackShape(world,x,y,z,false);
        }
    }

    @Override
    public void onMinecartPass(World world, EntityMinecart cart, int x, int y, int z) {
        NRRailBlockBase railBlock = (NRRailBlockBase)world.getBlock(x, y, z);
        int meta = railBlock.getBasicRailMetadata(world,cart, x,y,z);
        int dirMeta = meta & 7;
        double cartSpeed = Math.sqrt(cart.motionX * cart.motionX + cart.motionZ * cart.motionZ);

        if (dirMeta == 1){
            if (world.isSideSolid(x-1,y,z, ForgeDirection.EAST)){
                cart.motionX += 0.02D;
            }
            else if (world.isSideSolid(x+1,y,z,ForgeDirection.WEST)){
                cart.motionX += -0.02D;
            }

       }
        else if (dirMeta == 0){
            if (world.isSideSolid(x,y,z-1, ForgeDirection.SOUTH)){
                cart.motionZ += 0.02D;
            }
            else if (world.isSideSolid(x,y,z+1,ForgeDirection.NORTH)){
                cart.motionZ += -0.02D;
            }

       }


    }


}
