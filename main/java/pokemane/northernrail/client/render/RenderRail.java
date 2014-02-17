package pokemane.northernrail.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import pokemane.northernrail.api.rail.IRail;
import pokemane.northernrail.api.rail.NRRailBase;
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
        renderRailWorld(renderer,world,x,y,z,(NRRailBase)block,modelId);
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

    private void renderRailWorld(RenderBlocks renderBlocks, IBlockAccess iBlockAccess, int blockX, int blockY, int blockZ, NRRailBase block, int modelId){
        Tessellator tessellator = Tessellator.instance;
        int railMeta = block.getBasicRailMetadata(iBlockAccess, null, blockX, blockY, blockZ);
        IIcon railTexture = renderBlocks.overrideBlockTexture != null ? renderBlocks.overrideBlockTexture : block.getBlockTextureFromSide(0);

        if ((block != null)){
            railTexture = renderBlocks.getIconSafe(block.getIcon(0,railMeta));
        }
        assert block != null;
        if (block.isPowered())
        {
            railMeta &= 7;
        }

        tessellator.setBrightness(block.getMixedBrightnessForBlock(iBlockAccess, blockX, blockY, blockZ));
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        double d0 = (double)railTexture.getMinU();
        double d1 = (double)railTexture.getMinV();
        double d2 = (double)railTexture.getMaxU();
        double d3 = (double)railTexture.getMaxV();
        double d4 = 0.0625D;
        double d5 = (double)(blockX + 1);
        double d6 = (double)(blockX + 1);
        double d7 = (double)(blockX + 0);
        double d8 = (double)(blockX + 0);
        double d9 = (double)(blockZ + 0);
        double d10 = (double)(blockZ + 1);
        double d11 = (double)(blockZ + 1);
        double d12 = (double)(blockZ + 0);
        double d13 = (double)blockY + d4;
        double d14 = (double)blockY + d4;
        double d15 = (double)blockY + d4;
        double d16 = (double)blockY + d4;

        if (railMeta != 1 && railMeta != 2 && railMeta != 3 && railMeta != 7)
        {
            if (railMeta == 8)
            {
                d5 = d6 = (double)(blockX + 0);
                d7 = d8 = (double)(blockX + 1);
                d9 = d12 = (double)(blockZ + 1);
                d10 = d11 = (double)(blockZ + 0);
            }
            else if (railMeta == 9)
            {
                d5 = d8 = (double)(blockX + 0);
                d6 = d7 = (double)(blockX + 1);
                d9 = d10 = (double)(blockZ + 0);
                d11 = d12 = (double)(blockZ + 1);
            }
        }
        else
        {
            d5 = d8 = (double)(blockX + 1);
            d6 = d7 = (double)(blockX + 0);
            d9 = d10 = (double)(blockZ + 1);
            d11 = d12 = (double)(blockZ + 0);
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
