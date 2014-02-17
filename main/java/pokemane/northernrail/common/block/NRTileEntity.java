package pokemane.northernrail.common.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by pokemane on 2/16/14.
 */
public class NRTileEntity extends TileEntity {

    public final int getX(){
        return this.xCoord;
    }

    public final int getY(){
        return this.yCoord;
    }

    public final int getZ(){
        return this.zCoord;
    }

    public final World getWorld(){
        return this.worldObj;
    }

}
