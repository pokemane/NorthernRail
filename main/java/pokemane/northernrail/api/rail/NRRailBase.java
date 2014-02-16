package pokemane.northernrail.api.rail;


import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.common.NorthernRailLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by pokemane on 2/15/14.
 */
public abstract class NRRailBase extends BlockRailBase implements IRail{

    protected final boolean powered;
    protected NRRailBase(boolean poweredState){
        super(poweredState);
        this.powered = poweredState;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setCreativeTab(NorthernRailLoader.creativeTabNR);
    }

    @Override
    public boolean isRailBlockAt(World world, int x, int y, int z) {
        return isRailBlock(world.getBlock(x,y,z));
    }

    @Override
    public boolean isRailBlock(Block block) {
        return block instanceof NRRailBase;
    }

    @Override
    public boolean isPowered() {
        return this.powered;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 par5vec3, Vec3 par6ved3) {
        this.setBlockBoundsBasedOnState(world,x,y,z);
        return super.collisionRayTrace(world,x,y,z,par5vec3,par6ved3);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z) {
        int l = iBlockAccess.getBlockMetadata(x,y,z);

        if (l >= 2 && l <= 5){
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
        }
        else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        }
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return renderType;
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return World.doesBlockHaveSolidTopSurface(world,x,y-1,z);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (!world.isRemote){
            this.func_150052_a(world,x,y,z,true);
            if (this.powered){
                this.onNeighborBlockChange(world,x,y,z,this);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        if (!world.isRemote){
            int blockMetadata = world.getBlockMetadata(x,y,z);
            int newMeta = blockMetadata;

            if (this.powered){
                newMeta = blockMetadata & 7;
            }

            boolean flag = false;

            if (!World.doesBlockHaveSolidTopSurface(world,x,y-1,z)){
                flag = true;
            }

            if (newMeta == 2 && !World.doesBlockHaveSolidTopSurface(world,x+1,y,z)){
                flag = true;
            }

            if (newMeta == 3 && !World.doesBlockHaveSolidTopSurface(world,x-1,y,z)){
                flag = true;
            }

            if (newMeta == 4 && !World.doesBlockHaveSolidTopSurface(world,x,y,z-1)){
                flag = true;
            }

            if (newMeta == 5 && !World.doesBlockHaveSolidTopSurface(world,x,y,z+1)){
                flag = true;
            }

            if (flag){
                this.dropBlockAsItem(world,x,y,z,world.getBlockMetadata(x,y,z),0);
                world.setBlockToAir(x,y,z);
            }
            else {
                this.func_150048_a(world, x, y, z, blockMetadata, newMeta, neighborBlock);
            }
        }
    }

    @Override
    public void func_150048_a(World world, int x, int y, int z, int oldMetadata, int newMetadata, Block block) {}

    @Override
    public void func_150052_a(World world, int x, int y, int z, boolean par6) {
        if (!world.isRemote){
<<<<<<< HEAD
            new NRRailLogic(world,x,y,z).func_150655_a(world.isBlockIndirectlyGettingPowered(x, y, z), par6);
=======
            new NRRailLogic(world,x,y,z).func_150655_a(world.isBlockIndirectlyGettingPowered(x,y,z),par6);
>>>>>>> bbbcbbaeac8cb623b348bf5758234cf32df449ba
        }
    }

    @Override
    public int getMobilityFlag() {
        return 0;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata) {
        int i1 = oldMetadata;

        if (this.field_150053_a)
        {
            i1 = oldMetadata & 7;
        }

        super.breakBlock(world, x, x, x, oldBlock, oldMetadata);

        if (i1 == 2 || i1 == 3 || i1 == 4 || i1 == 5)
        {
            world.notifyBlocksOfNeighborChange(x, x + 1, x, oldBlock);
        }

        if (this.field_150053_a)
        {
            world.notifyBlocksOfNeighborChange(x, x, x, oldBlock);
            world.notifyBlocksOfNeighborChange(x, x - 1, x, oldBlock);
        }
    }

    @Override
    public boolean isFlexibleRail(IBlockAccess iBlockAccess, int x, int y, int z) {
        return isPowered();
    }

    @Override
    public boolean canMakeSlopes(IBlockAccess iBlockAccess, int x, int y, int z) {
        return true;
    }

    @Override
    public int getBasicRailMetadata(IBlockAccess iBlockAccess, EntityMinecart cart, int x, int y, int z) {
        int meta = iBlockAccess.getBlockMetadata(x, y, z);
        if(isPowered())
        {
            meta = meta & 7;
        }
        return meta;
    }

    @Override
    public float getMaxRailSpeed(World world, EntityMinecart cart, int x, int y, int z) {
        return 0.4f;
    }

    @Override
    public void onMinecartPass(World world, EntityMinecart cart, int x, int y, int z) {

    }


    private int renderType = 9;
    @Override
    public void setRenderType(int value) {
        renderType = value;
    }

    /*============================================================================*/
    public class NRRailLogic{
        private final NRRailBase theBlock;
        private final int blockMetadata;
        private World railLogicWorld;
        private int railX;
        private int railY;
        private int railZ;
        private final boolean isStraightRail;
        private List railChunkPosition;
        private final boolean canMakeSlopes;

        public NRRailLogic(World world, int x, int y, int z){
            this.railLogicWorld = world;
            this.railX = x;
            this.railY = y;
            this.railZ = z;
            this.theBlock = (NRRailBase)world.getBlock(x,y,z);
            this.isStraightRail = theBlock.isFlexibleRail(world,x,y,z);
            this.canMakeSlopes = theBlock.canMakeSlopes(world,x,y,z);
            this.blockMetadata = theBlock.getBasicRailMetadata(world,null,x,y,z);
            this.setBasicRail(blockMetadata);
        }

        private void setBasicRail(int metadata){
            this.railChunkPosition.clear();

            if (metadata == 0)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
            }
            else if (metadata == 1)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
            }
            else if (metadata == 2)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY + 1, this.railZ));
            }
            else if (metadata == 3)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY + 1, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
            }
            else if (metadata == 4)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ - 1));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
            }
            else if (metadata == 5)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ + 1));
            }
            else if (metadata == 6)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
            }
            else if (metadata == 7)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
            }
            else if (metadata == 8)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
            }
            else if (metadata == 9)
            {
                this.railChunkPosition.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
                this.railChunkPosition.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
            }
        }

        private void refreshConnectedTracks(){
            for (int i = 0; i < this.railChunkPosition.size(); ++i){
                NRRailLogic railLogic = this.getRailLogic((ChunkPosition)this.railChunkPosition);
                if (railLogic != null && railLogic.isRailChunkPositionCorrect(this)){
                    this.railChunkPosition.set(i, new ChunkPosition(railLogic.railX,railLogic.railY,railLogic.railZ));
                }
                else {
                    this.railChunkPosition.remove(i--);
                }
            }
        }

        private boolean isMinecartTrack(int x, int y, int z){
            return isRailBlockAt(this.railLogicWorld, x, y, z) || (isRailBlockAt(this.railLogicWorld, x, y + 1, z) || isRailBlockAt(this.railLogicWorld, x, y - 1, z));
        }

        private NRRailLogic getRailLogic(ChunkPosition chunkPosition){
            return isRailBlockAt(this.railLogicWorld, chunkPosition.chunkPosX, chunkPosition.chunkPosY, chunkPosition.chunkPosZ) ? NRRailBase.this.new NRRailLogic(this.railLogicWorld, chunkPosition.chunkPosX, chunkPosition.chunkPosY, chunkPosition.chunkPosZ) : (isRailBlockAt(this.railLogicWorld, chunkPosition.chunkPosX, chunkPosition.chunkPosY + 1, chunkPosition.chunkPosZ) ? NRRailBase.this.new NRRailLogic(this.railLogicWorld, chunkPosition.chunkPosX, chunkPosition.chunkPosY + 1, chunkPosition.chunkPosZ) : (isRailBlockAt(this.railLogicWorld, chunkPosition.chunkPosX, chunkPosition.chunkPosY - 1, chunkPosition.chunkPosZ) ? NRRailBase.this.new NRRailLogic(this.railLogicWorld, chunkPosition.chunkPosX, chunkPosition.chunkPosY - 1, chunkPosition.chunkPosZ) : null));
        }

        private boolean isRailChunkPositionCorrect(NRRailLogic nrRailLogic){
            for (int i = 0; i < this.railChunkPosition.size(); ++i){
                ChunkPosition chunkPosition = (ChunkPosition)this.railChunkPosition.get(i);
                if (chunkPosition.chunkPosX == nrRailLogic.railX && chunkPosition.chunkPosZ == nrRailLogic.railZ){
                    return true;
                }
            }
            return false;
        }

        private boolean isPartOfTrack(int x, int y, int z){
            for (Object aRailChunkPosition : this.railChunkPosition) {
                ChunkPosition chunkPosition = (ChunkPosition) aRailChunkPosition;
                if (chunkPosition.chunkPosX == x && chunkPosition.chunkPosZ == z) {
                    return true;
                }
            }
            return false;
        }

        public int getNumberOfAdjacentTracks(){
            int i = 0;
            if (this.isMinecartTrack(this.railX, this.railY, this.railZ - 1))
            {
                ++i;
            }

            if (this.isMinecartTrack(this.railX, this.railY, this.railZ + 1))
            {
                ++i;
            }

            if (this.isMinecartTrack(this.railX - 1, this.railY, this.railZ))
            {
                ++i;
            }

            if (this.isMinecartTrack(this.railX + 1, this.railY, this.railZ))
            {
                ++i;
            }

            return i;
        }

        private boolean canConnectTo(NRRailLogic nrRailLogic){
            return this.isRailChunkPositionCorrect(nrRailLogic) || (this.railChunkPosition.size() != 2);
        }

        private void connectToNeighbor(NRRailLogic nrRailLogic){
            this.railChunkPosition.add(new ChunkPosition(nrRailLogic.railX, nrRailLogic.railY, nrRailLogic.railZ));
            boolean flag = this.isPartOfTrack(this.railX,this.railY,this.railZ-1);
            boolean flag1 = this.isPartOfTrack(this.railX,this.railY,this.railZ+1);
            boolean flag2 = this.isPartOfTrack(this.railX - 1, this.railY, this.railZ);
            boolean flag3 = this.isPartOfTrack(this.railX + 1, this.railY, this.railZ);
            byte b0 = -1;

            if (flag || flag1){
                b0 = 0;
            }

            if (flag2 || flag3){
                b0 = 1;
            }

            if (!this.isStraightRail){
                if (flag1 && flag3 && !flag && !flag2){
                    b0 = 6;
                }

                if (flag1 && flag2 && !flag && !flag3){
                    b0 = 7;
                }

                if (flag && flag2 && !flag1 && !flag3){
                    b0 = 8;
                }

                if (flag && flag3 && !flag1 && !flag2){
                    b0 = 9;
                }
            }

            if (b0 == 0 && canMakeSlopes){
                if (isRailBlockAt(this.railLogicWorld, this.railX, this.railY + 1, this.railZ - 1)){
                    b0 = 4;
                }

                if (isRailBlockAt(this.railLogicWorld, this.railX, this.railY + 1, this.railZ + 1)){
                    b0 = 5;
                }
            }

            if (b0 == 1 && canMakeSlopes){
                if (isRailBlockAt(this.railLogicWorld, this.railX + 1, this.railY + 1, this.railZ)){
                    b0 = 2;
                }

                if (isRailBlockAt(this.railLogicWorld, this.railX - 1, this.railY + 1, this.railZ)){
                    b0 = 3;
                }
            }

            if (b0 < 0){
                b0 = 0;
            }

            int i = b0;

            if (this.isStraightRail){
                i = this.railLogicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | b0;
            }

            this.railLogicWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, i, 3);
        }

        private boolean canConnectFrom(int x, int y, int z){
            NRRailLogic nrRailLogic = this.getRailLogic(new ChunkPosition(x,y,z));
            if (nrRailLogic == null){
                return false;
            }
            else {
                nrRailLogic.refreshConnectedTracks();
                return nrRailLogic.canConnectTo(this);
            }
        }


        /**
         * I think this is something to do with determining where we can connect to, after the rail gets an RS signal.
         * @param par1 seems to be "is receiving power"
         * @param par2
         */
        public void func_150655_a(boolean par1, boolean par2){
            boolean flag2 = this.canConnectFrom(this.railX, this.railY, this.railZ - 1);
            boolean flag3 = this.canConnectFrom(this.railX, this.railY, this.railZ + 1);
            boolean flag4 = this.canConnectFrom(this.railX - 1, this.railY, this.railZ);
            boolean flag5 = this.canConnectFrom(this.railX + 1, this.railY, this.railZ);
            byte b0 = -1;

            if ((flag2 || flag3) && !flag4 && !flag5){
                b0 = 0;
            }

            if ((flag4 || flag5) && !flag2 && !flag3){
                b0 = 1;
            }

            if (!this.isStraightRail){
                if (flag3 && flag5 && !flag2 && !flag4){
                    b0 = 6;
                }

                if (flag3 && flag4 && !flag2 && !flag5){
                    b0 = 7;
                }

                if (flag2 && flag4 && !flag3 && !flag5){
                    b0 = 8;
                }

                if (flag2 && flag5 && !flag3 && !flag4){
                    b0 = 9;
                }
            }

            if (b0 == -1){
                if (flag2 || flag3){
                    b0 = 0;
                }

                if (flag4 || flag5){
                    b0 = 1;
                }

                if (!this.isStraightRail){
                    if (par1){
                        if (flag3 && flag5){
                            b0 = 6;
                        }

                        if (flag4 && flag3){
                            b0 = 7;
                        }

                        if (flag5 && flag2){
                            b0 = 9;
                        }

                        if (flag2 && flag4){
                            b0 = 8;
                        }
                    }
                    else {
                        if (flag2 && flag4){
                            b0 = 8;
                        }

                        if (flag5 && flag2){
                            b0 = 9;
                        }

                        if (flag4 && flag3){
                            b0 = 7;
                        }

                        if (flag3 && flag5){
                            b0 = 6;
                        }
                    }
                }
            }

            if (b0 == 0 && canMakeSlopes){
                if (isRailBlockAt(this.railLogicWorld, this.railX, this.railY + 1, this.railZ - 1)){
                    b0 = 4;
                }

                if (isRailBlockAt(this.railLogicWorld, this.railX, this.railY + 1, this.railZ + 1)){
                    b0 = 5;
                }
            }

            if (b0 == 1 && canMakeSlopes){
                if (isRailBlockAt(this.railLogicWorld, this.railX + 1, this.railY + 1, this.railZ)){
                    b0 = 2;
                }

                if (isRailBlockAt(this.railLogicWorld, this.railX - 1, this.railY + 1, this.railZ)){
                    b0 = 3;
                }
            }

            if (b0 < 0){
                b0 = 0;
            }

            this.setBasicRail(b0);
            int i = b0;

            if (this.isStraightRail){
                i = this.railLogicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | b0;
            }

            if (par2 || this.railLogicWorld.getBlockMetadata(this.railX, this.railY, this.railZ) != i){
                this.railLogicWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, i, 3);

                for (Object aRailChunkPosition : this.railChunkPosition) {
                    NRRailLogic nrRailLogic = this.getRailLogic((ChunkPosition) aRailChunkPosition);

                    if (nrRailLogic != null) {
                        nrRailLogic.refreshConnectedTracks();

                        if (nrRailLogic.canConnectTo(this)) {
                            nrRailLogic.connectToNeighbor(this);
                        }
                    }
                }
            }
        }
    }
}
