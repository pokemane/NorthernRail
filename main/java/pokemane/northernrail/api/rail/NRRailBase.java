package pokemane.northernrail.api.rail;


import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import pokemane.northernrail.common.NorthernRail;
import pokemane.northernrail.common.NorthernRailLoader;

import java.util.Random;

/**
 * Created by pokemane on 2/15/14.
 */
public abstract class NRRailBase extends BlockRailBase implements IRail{

    protected final boolean isPoweredRail;
    protected NRRailBase(boolean powered){
        super(powered);
        this.isPoweredRail = powered;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
        this.setCreativeTab(NorthernRailLoader.creativeTabNR);
    }

    @Override
    public boolean isRailBlockAt(World world, int x, int y, int z) {
        return isRailBlock(world.getBlock(x,y,z));
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public boolean isRailBlock(Block block) {
        return block instanceof BlockRailBase;
    }

    @Override
    public boolean isPowered() {
        return this.isPoweredRail;
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
            if (this.isPoweredRail){
                this.onNeighborBlockChange(world,x,y,z,this);
            }
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
        if (!world.isRemote){
            int blockMetadata = world.getBlockMetadata(x,y,z);
            int newMeta = blockMetadata;

            if (this.isPoweredRail){
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

    public void onRedstoneSignal(World world, int x, int y, int z, int oldMetadata, int newMetadata, Block block){
        func_150048_a(world,x,y,z,oldMetadata,newMetadata,block);
    }

    @Override
    public void func_150048_a(World world, int x, int y, int z, int oldMetadata, int newMetadata, Block block) {}

    public void refreshTrackShape(World world, int x, int y, int z, boolean par6){
        func_150052_a(world,x,y,z,par6);
    }

    @Override
    public void func_150052_a(World world, int x, int y, int z, boolean par6){
        if (!world.isRemote){
                new Rail(world,x,y,z).func_150655_a(world.isBlockIndirectlyGettingPowered(x, y, z), par6);
        }
    }

    @Override
    public int getMobilityFlag() {
        return 0;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata) {
        int i1 = oldMetadata;

        if (this.isPoweredRail)
        {
            i1 = oldMetadata & 7;
        }

        super.breakBlock(world, x, x, x, oldBlock, oldMetadata);

        if (i1 == 2 || i1 == 3 || i1 == 4 || i1 == 5)
        {
            world.notifyBlocksOfNeighborChange(x, x + 1, x, oldBlock);
        }

        if (this.isPoweredRail)
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


    private int renderType = NorthernRail.renderIdRail;
    @Override
    public void setRenderType(int value) {
        renderType = value;
    }

    public int countAdjacentRails(World world, int x, int y, int z){
        return new NRRailLogic(world,x,y,z).countAdjacentRails();
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

    protected boolean testPowered(World world, int x, int y, int z, boolean dir, int distance, int maxDistance, int orientation){
        Block block = world.getBlock(x, y, z);

        if (block == this){
            int meta = world.getBlockMetadata(x, y, z);
            int meta1 = meta & 7;

            if (orientation == 1 && (meta1 == 0 || meta1 == 4 || meta1 == 5)){
                return false;
            }

            if (orientation == 0 && (meta1 == 1 || meta1 == 2 || meta1 == 3)){
                return false;
            }

            if ((meta & 8) != 0){
                return world.isBlockIndirectlyGettingPowered(x, y, z) || this.isConnectedRailPowered(world, x, y, z, meta, dir, distance + 1, maxDistance);

            }
        }

        return false;
    }

    /*============================================================================*/
    protected class NRRailLogic extends Rail{

        public NRRailLogic(World world, int x, int y, int z){
            super(world, x, y, z);
        }

        /**
         * Wrapper because the way vanilla is structured is dumb as hell
         * @return how many rails are touching our rail
         */
        public int countAdjacentRails(){
            return func_150650_a();
        }
    }
}
