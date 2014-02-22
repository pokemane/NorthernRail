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

/**
 * Created by pokemane on 2/16/14.
 */
public class TestPoweredRail extends NRRailBlockBase {
    private IIcon poweredIcon;
    private int maxPropagationDistance = 12;
    public TestPoweredRail(){
        super(true);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata){
        return (metadata & 8) == 0 ? this.blockIcon : this.poweredIcon;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		int meta = world.getBlockMetadata(x,y,z);
		final String message = "meta: " + String.valueOf(meta);
		ChatComponentText chatmessage = new ChatComponentText(message);
		if (world.isRemote){
			player.addChatComponentMessage(chatmessage);
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        super.registerBlockIcons(iconRegister);
        this.poweredIcon = iconRegister.registerIcon(this.getTextureName() + "_powered");
    }


	@Override
    public void func_150048_a(World world, int x, int y, int z, int oldMetadata, int dirMeta, Block block){
        boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);
        flag = flag || this.isConnectedRailPowered(world, x, y, z, oldMetadata, true, 0, this.maxPropagationDistance) || this.isConnectedRailPowered(world, x, y, z, oldMetadata, false, 0, this.maxPropagationDistance);
        boolean flag1 = false;
		//setting block metadata based on current state
		//if we are getting powered indirectly and are not currently in the powered state
        if (flag && (oldMetadata & 8) == 0){
	        //set our "power" bit true
            world.setBlockMetadataWithNotify(x, y, z, dirMeta | 8, 3);
            flag1 = true;
        }
        //if we are not getting powered indirectly and are currently in the powered state
        else if (!flag && (oldMetadata & 8) != 0){
	        //leave the current metadata as it is
            world.setBlockMetadataWithNotify(x, y, z, dirMeta, 3);
            flag1 = true;
        }
		//if we're being indirectly powered and we've set the metadata
        if (flag1){
	        //notify blocks below our level we've changed
	        //this causes other rails to connect to us if they're ascending to us
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
			//if we're ascending now
	        //notifies rails one level up so they can decide if we can connect
            if (dirMeta == 2 || dirMeta == 3 || dirMeta == 4 || dirMeta == 5){
                world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
            }
        }
    }

	protected boolean isConnectedRailPowered(World world, int x, int y, int z, int metadata, boolean dir, int distance, int maxDistance){
		// if we're trying to reach past the max propagation distance
		if (distance >= maxDistance){
			return false;
		}
		else{
			int orientation = metadata & 7;
			boolean powered = true;

			switch (orientation) {
				case 0: //north-south
					if (dir){
						++z;
					}
					else{
						--z;
					}
					break;
				case 1: //east-west
					if (dir){
						--x;
					}
					else{
						++x;
					}

					break;
				case 2: //ascending to east
					if (dir){
						--x;
					}
					else{
						++x;
						++y;
						powered = false;
					}

					orientation = 1;
					break;
				case 3: // ascending to west
					if (dir){
						--x;
						++y;
						powered = false;
					}
					else{
						++x;
					}

					orientation = 1;
					break;
				case 4: // ascending to north
					if (dir){
						++z;
					}
					else{
						--z;
						++y;
						powered = false;
					}

					orientation = 0;
					break;
				case 5: // ascending to south
					if (dir){
						++z;
						++y;
						powered = false;
					}
					else{
						--z;
					}

					orientation = 0;
			}

			return this.testPowered(world, x, y, z, dir, distance, maxDistance, orientation) || powered && this.testPowered(world, x, y - 1, z, dir, distance, maxDistance, orientation);
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
			// if we're powered
			if ((meta & 8) != 0){
				return world.isBlockIndirectlyGettingPowered(x, y, z) || this.isConnectedRailPowered(world, x, y, z, meta, dir, distance + 1, maxDistance);
			}
		}

		return false;
	}

	@Override
    public void onMinecartPass(World world, EntityMinecart cart, int x, int y, int z) {
        int meta = world.getBlockMetadata(x,y,z);
        int dirMeta = meta & 7;
		int pwrMeta = meta & 8;
        double cartSpeed = Math.sqrt(cart.motionX*cart.motionX+cart.motionZ*cart.motionZ);
        if (pwrMeta == 8){
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
