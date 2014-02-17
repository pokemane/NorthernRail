package pokemane.northernrail.common.block.rail;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
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



    public void func_150048_a(World world, int x, int y, int z, int oldMetadata, int newMetadata, Block block){
        boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);
        flag = flag || this.isConnectedRailPowered(world, x, y, z, oldMetadata, true, 0, this.maxPropagationDistance) || this.isConnectedRailPowered(world, x, y, z, oldMetadata, false, 0, this.maxPropagationDistance);
        boolean flag1 = false;
        if (flag && (oldMetadata & 8) == 0){
            world.setBlockMetadataWithNotify(x, y, z, newMetadata | 8, 3);
            flag1 = true;
        }
        else if (!flag && (oldMetadata & 8) != 0){
            world.setBlockMetadataWithNotify(x, y, z, newMetadata, 3);
            flag1 = true;
        }
        if (flag1){
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this);

            if (newMetadata == 2 || newMetadata == 3 || newMetadata == 4 || newMetadata == 5){
                world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
            }
        }
    }

    @Override
    public void onMinecartPass(World world, EntityMinecart cart, int x, int y, int z) {
        NRRailBase railBlock = (NRRailBase)world.getBlock(x, y, z);
        int meta = railBlock.getBasicRailMetadata(world,cart, x,y,z);
        int dirMeta = meta & 7;
        double cartSpeed = Math.sqrt(cart.motionX*cart.motionX+cart.motionZ*cart.motionZ);
        if (this.isBeingPowered){
            if (cartSpeed > 0.01D){
                cart.motionX += cart.motionX / cartSpeed * 0.04D;
                cart.motionZ += cart.motionZ / cartSpeed * 0.04D;
            }
            else if (dirMeta == 1){
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
        else if (cartSpeed < 0.03D){
            cart.motionX = 0.0D;
            cart.motionY = 0.0D;
            cart.motionZ = 0.0D;
        }
        else{
            cart.motionX *= 0.5D;
            cart.motionY = 0.0D;
            cart.motionZ *= 0.5D;
        }
    }
}
