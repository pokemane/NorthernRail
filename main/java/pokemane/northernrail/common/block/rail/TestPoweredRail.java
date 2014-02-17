package pokemane.northernrail.common.block.rail;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import pokemane.northernrail.api.rail.NRRailBase;

/**
 * Created by pokemane on 2/16/14.
 */
public class TestPoweredRail extends NRRailBase {
    private IIcon theIcon;
    private int maxPropagationDistance = 12;
    public TestPoweredRail(){
        super(true);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata){
        return (metadata & 8) == 0 ? this.blockIcon : this.theIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        super.registerBlockIcons(iconRegister);
        this.theIcon = iconRegister.registerIcon(this.getTextureName() + "_powered");
    }

    public void func_150048_a(World p_150048_1_, int p_150048_2_, int p_150048_3_, int p_150048_4_, int p_150048_5_, int p_150048_6_, Block p_150048_7_)
    {
        boolean flag = p_150048_1_.isBlockIndirectlyGettingPowered(p_150048_2_, p_150048_3_, p_150048_4_);
        flag = flag || this.isConnectedRailPowered(p_150048_1_, p_150048_2_, p_150048_3_, p_150048_4_, p_150048_5_, true, 0, this.maxPropagationDistance) || this.isConnectedRailPowered(p_150048_1_, p_150048_2_, p_150048_3_, p_150048_4_, p_150048_5_, false, 0, this.maxPropagationDistance);
        boolean flag1 = false;

        if (flag && (p_150048_5_ & 8) == 0)
        {
            p_150048_1_.setBlockMetadataWithNotify(p_150048_2_, p_150048_3_, p_150048_4_, p_150048_6_ | 8, 3);
            flag1 = true;
        }
        else if (!flag && (p_150048_5_ & 8) != 0)
        {
            p_150048_1_.setBlockMetadataWithNotify(p_150048_2_, p_150048_3_, p_150048_4_, p_150048_6_, 3);
            flag1 = true;
        }

        if (flag1)
        {
            p_150048_1_.notifyBlocksOfNeighborChange(p_150048_2_, p_150048_3_ - 1, p_150048_4_, this);

            if (p_150048_6_ == 2 || p_150048_6_ == 3 || p_150048_6_ == 4 || p_150048_6_ == 5)
            {
                p_150048_1_.notifyBlocksOfNeighborChange(p_150048_2_, p_150048_3_ + 1, p_150048_4_, this);
            }
        }
    }
}
