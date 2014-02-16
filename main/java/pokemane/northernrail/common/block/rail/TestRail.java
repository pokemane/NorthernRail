package pokemane.northernrail.common.block.rail;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.NRRailBase;

/**
 * Created by pokemane on 2/15/14.
 */
public class TestRail extends NRRailBase {

    @SideOnly(Side.CLIENT)
    private IIcon icon;

    public TestRail(){
        super(false);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
        return meta >= 6 ? this.icon : this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        super.registerBlockIcons(iconRegister);
        this.icon = iconRegister.registerIcon(this.getTextureName() + "_turned");
    }

    @Override
    public void func_150048_a(World world, int x, int y, int z, int side, int meta, Block block){
        if (block.canProvidePower() && (new NRRailLogic(world,x,y,z).getNumberOfAdjacentTracks()) == 3){
            this.func_150052_a(world,x,y,z,false);
        }
    }
}
