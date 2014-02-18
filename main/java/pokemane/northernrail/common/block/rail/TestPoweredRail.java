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
	private boolean isBeingPowered;
    public TestPoweredRail(){
        super(true);
	    this.isBeingPowered = false;
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

	protected boolean isConnectedRailPowered(World world, int x, int y, int z, int meta, boolean dir, int distance, int maxDistance){
		if (distance >= maxDistance){
			return false;
		}
		else{
			meta = meta & 7;
			boolean powered = true;

			switch (meta) {
				case 0:
					if (dir){
						++z;
					}
					else{
						--z;
					}
					break;
				case 1:
					if (dir){
						--x;
					}
					else{
						++x;
					}

					break;
				case 2:
					if (dir){
						--x;
					}
					else{
						++x;
						++y;
						powered = false;
					}

					meta = 1;
					break;
				case 3:
					if (dir){
						--x;
						++y;
						powered = false;
					}
					else{
						++x;
					}

					meta = 1;
					break;
				case 4:
					if (dir){
						++z;
					}
					else{
						--z;
						++y;
						powered = false;
					}

					meta = 0;
					break;
				case 5:
					if (dir){
						++z;
						++y;
						powered = false;
					}
					else{
						--z;
					}

					meta = 0;
			}

			return this.testPowered(world, x, y, z, dir, distance, maxDistance, meta) || powered && this.testPowered(world, x, y - 1, z, dir, distance, maxDistance, meta);
		}
	}

	protected boolean testPowerPropagation(World world, int x, int y, int z, int distance, int maxDistance, int orientation){
		return (isConnectedRailPowered(world, x,y,z, orientation,true,distance,maxDistance) || isConnectedRailPowered(world,x,y,z,orientation,false,distance,maxDistance));
	}

	protected boolean testPowered(World world, int x, int y, int z, boolean dir, int distance, int maxDistance, int orientation){
		Block block = world.getBlock(x, y, z);

		if (block instanceof TestPoweredRail){
			int meta = world.getBlockMetadata(x, y, z);
			int meta1 = meta & 7;

			if (orientation == 1 && (meta1 == 0 || meta1 == 4 || meta1 == 5)){
				return false;
			}

			if (orientation == 0 && (meta1 == 1 || meta1 == 2 || meta1 == 3)){
				return false;
			}

			if ((meta & 8) != 0){
				if (world.isBlockIndirectlyGettingPowered(x, y, z) || this.isConnectedRailPowered(world, x, y, z, meta, dir, distance + 1, maxDistance)){
					//slightly changed logic to show that the rail is indeed being powered since I can't figure how to
					//get the minecart to check it properly at the moment.
					this.isBeingPowered = true;
					return true;
				}
				else {
					this.isBeingPowered = false;
				}

			}
		}

		return false;
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
