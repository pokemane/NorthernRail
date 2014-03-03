package pokemane.northernrail.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import pokemane.northernrail.common.block.rail.RailBaseNR;
import pokemane.northernrail.testing.NRRailBlockBase;
import pokemane.northernrail.common.NorthernRail;

/**
 * Created by pokemane on 2/16/14.
 */
public class RenderRail implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderRailWorld(renderer,world,x,y,z,(RailBaseNR)block,modelId);
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return NorthernRail.renderIdRail;
    }

    private void renderRailWorld(RenderBlocks renderBlocks, IBlockAccess world, int x, int y, int z, RailBaseNR block, int modelId){
        Tessellator tessellator = Tessellator.instance;
        int railMeta = world.getBlockMetadata(x,y,z);
        IIcon railTexture = block.getIcon(world,x,y,z,0);

        if (renderBlocks.hasOverrideBlockTexture()){
            railTexture = renderBlocks.overrideBlockTexture;
        }
        assert block != null;
        if (block.isPowered())
        {
            railMeta &= 7;
        }

        tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double d0 = (double)railTexture.getMinU();
        double d1 = (double)railTexture.getMinV();
        double d2 = (double)railTexture.getMaxU();
        double d3 = (double)railTexture.getMaxV();
        double d4 = 0.0625D;
        double d5 = (double)(x + 1);
        double d6 = (double)(x + 1);
        double d7 = (double)(x + 0);
        double d8 = (double)(x + 0);
        double d9 = (double)(z + 0);
        double d10 = (double)(z + 1);
        double d11 = (double)(z + 1);
        double d12 = (double)(z + 0);
        double d13 = (double)y + d4;
        double d14 = (double)y + d4;
        double d15 = (double)y + d4;
        double d16 = (double)y + d4;

        if (railMeta != 1 && railMeta != 2 && railMeta != 3 && railMeta != 7)
        {
            if (railMeta == 8)
            {
                d5 = d6 = (double)(x + 0);
                d7 = d8 = (double)(x + 1);
                d9 = d12 = (double)(z + 1);
                d10 = d11 = (double)(z + 0);
            }
            else if (railMeta == 9)
            {
                d5 = d8 = (double)(x + 0);
                d6 = d7 = (double)(x + 1);
                d9 = d10 = (double)(z + 0);
                d11 = d12 = (double)(z + 1);
            }
        }
        else
        {
            d5 = d8 = (double)(x + 1);
            d6 = d7 = (double)(x + 0);
            d9 = d10 = (double)(z + 1);
            d11 = d12 = (double)(z + 0);
        }

        if (railMeta != 2 && railMeta != 4)
        {
            if (railMeta == 3 || railMeta == 5)
            {
                ++d14;
                ++d15;
            }
        }
        else
        {
            ++d13;
            ++d16;
        }

        tessellator.addVertexWithUV(d5, d13, d9, d2, d1);
        tessellator.addVertexWithUV(d6, d14, d10, d2, d3);
        tessellator.addVertexWithUV(d7, d15, d11, d0, d3);
        tessellator.addVertexWithUV(d8, d16, d12, d0, d1);
        tessellator.addVertexWithUV(d8, d16, d12, d0, d1);
        tessellator.addVertexWithUV(d7, d15, d11, d0, d3);
        tessellator.addVertexWithUV(d6, d14, d10, d2, d3);
        tessellator.addVertexWithUV(d5, d13, d9, d2, d1);
    }
}
