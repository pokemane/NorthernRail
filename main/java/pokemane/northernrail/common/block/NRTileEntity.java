package pokemane.northernrail.common.block;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
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


	@Override
	public void readFromNBT(NBTTagCompound p_145839_1_) {
		super.readFromNBT(p_145839_1_);
	}

	@Override
	public void writeToNBT(NBTTagCompound p_145841_1_) {
		super.writeToNBT(p_145841_1_);
	}

	@Override
	public int getBlockMetadata() {
		return super.getBlockMetadata();
	}

	@Override
	public Block getBlockType() {
		return super.getBlockType();
	}

	@Override
    public boolean canUpdate() {
        return false;
    }
}
