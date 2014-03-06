package pokemane.northernrail.api.rail;

import net.minecraft.block.BlockRailBase;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pokemane on 3/5/14.
 */
public class RailLogicHelper
{
	private World railWorld;
	private int railX;
	private int railY;
	private int railZ;
	private final boolean isFlexible;
	private List chunkArray = new ArrayList();
	private final boolean canMakeSlopes;

	public RailLogicHelper(World world, int x, int y, int z)
	{
		this.railWorld = world;
		this.railX = x;
		this.railY = y;
		this.railZ = z;
		BlockRailBase block = (BlockRailBase)world.getBlock(x, y, z);
		int l = block.getBasicRailMetadata(world, null, x, y, z);
		this.isFlexible = block.isFlexibleRail(world, x, y, z);
		canMakeSlopes = block.canMakeSlopes(world, x, y, z);
		this.listPotentialConnections(l);
	}

	private void listPotentialConnections(int metadata)
	{
		this.chunkArray.clear();

		if (metadata == 0)
		{
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (metadata == 1)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
		}
		else if (metadata == 2)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY + 1, this.railZ));
		}
		else if (metadata == 3)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY + 1, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
		}
		else if (metadata == 4)
		{
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ - 1));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (metadata == 5)
		{
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ + 1));
		}
		else if (metadata == 6)
		{
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (metadata == 7)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (metadata == 8)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
		}
		else if (metadata == 9)
		{
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
		}
	}

	private void func_150651_b()
	{
		for (int i = 0; i < this.chunkArray.size(); ++i)
		{
			RailLogicHelper rail = this.createRailHelperAtAboveOrBelow((ChunkPosition) this.chunkArray.get(i));

			if (rail != null && rail.isRailAdjacent(this))
			{
				this.chunkArray.set(i, new ChunkPosition(rail.railX, rail.railY, rail.railZ));
			}
			else
			{
				this.chunkArray.remove(i--);
			}
		}
	}

	private boolean isRailBlockAtAboveOrBelow(int x, int y, int z)
	{
		return BlockRailBase.func_150049_b_(this.railWorld, x, y, z) || (BlockRailBase.func_150049_b_(this.railWorld, x, y + 1, z) || BlockRailBase.func_150049_b_(this.railWorld, x, y - 1, z));
	}

	private RailLogicHelper createRailHelperAtAboveOrBelow(ChunkPosition chunkPos)
	{
		return BlockRailBase.func_150049_b_(this.railWorld, chunkPos.chunkPosX, chunkPos.chunkPosY, chunkPos.chunkPosZ) ? new RailLogicHelper(this.railWorld, chunkPos.chunkPosX, chunkPos.chunkPosY, chunkPos.chunkPosZ) : (BlockRailBase.func_150049_b_(this.railWorld, chunkPos.chunkPosX, chunkPos.chunkPosY + 1, chunkPos.chunkPosZ) ? new RailLogicHelper(this.railWorld, chunkPos.chunkPosX, chunkPos.chunkPosY + 1, chunkPos.chunkPosZ) : (BlockRailBase.func_150049_b_(this.railWorld, chunkPos.chunkPosX, chunkPos.chunkPosY - 1, chunkPos.chunkPosZ) ? new RailLogicHelper(this.railWorld, chunkPos.chunkPosX, chunkPos.chunkPosY - 1, chunkPos.chunkPosZ) : null));
	}

	private boolean isRailAdjacent(RailLogicHelper rail)
	{
		for (Object aChunkPos : this.chunkArray) {
			ChunkPosition chunkposition = (ChunkPosition) aChunkPos;

			if (chunkposition.chunkPosX == rail.railX && chunkposition.chunkPosZ == rail.railZ) {
				return true;
			}
		}

		return false;
	}

	private boolean isRailAdjacent(int x, int y, int z)
	{
		for (Object aChunkArray : this.chunkArray) {
			ChunkPosition chunkposition = (ChunkPosition) aChunkArray;

			if (chunkposition.chunkPosX == x && chunkposition.chunkPosZ == z) {
				return true;
			}
		}

		return false;
	}

	protected int countAdjacentRails()
	{
		int i = 0;

		if (this.isRailBlockAtAboveOrBelow(this.railX, this.railY, this.railZ - 1))
		{
			++i;
		}

		if (this.isRailBlockAtAboveOrBelow(this.railX, this.railY, this.railZ + 1))
		{
			++i;
		}

		if (this.isRailBlockAtAboveOrBelow(this.railX - 1, this.railY, this.railZ))
		{
			++i;
		}

		if (this.isRailBlockAtAboveOrBelow(this.railX + 1, this.railY, this.railZ))
		{
			++i;
		}

		return i;
	}

	private boolean func_150649_b(RailLogicHelper rail)
	{
		return this.isRailAdjacent(rail) || (this.chunkArray.size() != 2);
	}

	private void func_150645_c(RailLogicHelper rail)
	{
		this.chunkArray.add(new ChunkPosition(rail.railX, rail.railY, rail.railZ));
		boolean north = this.isRailAdjacent(this.railX, this.railY, this.railZ - 1);
		boolean south = this.isRailAdjacent(this.railX, this.railY, this.railZ + 1);
		boolean west = this.isRailAdjacent(this.railX - 1, this.railY, this.railZ);
		boolean east = this.isRailAdjacent(this.railX + 1, this.railY, this.railZ);
		byte b0 = -1;

		if (north || south)
		{
			b0 = 0;
		}

		if (west || east)
		{
			b0 = 1;
		}

		if (!this.isFlexible)
		{
			if (south && east && !north && !west)
			{
				b0 = 6;
			}

			if (south && west && !north && !east)
			{
				b0 = 7;
			}

			if (north && west && !south && !east)
			{
				b0 = 8;
			}

			if (north && east && !south && !west)
			{
				b0 = 9;
			}
		}

		if (b0 == 0 && canMakeSlopes)
		{
			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX, this.railY + 1, this.railZ - 1))
			{
				b0 = 4;
			}

			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX, this.railY + 1, this.railZ + 1))
			{
				b0 = 5;
			}
		}

		if (b0 == 1 && canMakeSlopes)
		{
			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX + 1, this.railY + 1, this.railZ))
			{
				b0 = 2;
			}

			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX - 1, this.railY + 1, this.railZ))
			{
				b0 = 3;
			}
		}

		if (b0 < 0)
		{
			b0 = 0;
		}

		int i = b0;

		if (this.isFlexible)
		{
			i = this.railWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | b0;
		}

		this.railWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, i, 3);
	}

	private boolean func_150647_c(int p_150647_1_, int p_150647_2_, int p_150647_3_)
	{
		RailLogicHelper rail = this.createRailHelperAtAboveOrBelow(new ChunkPosition(p_150647_1_, p_150647_2_, p_150647_3_));

		if (rail == null)
		{
			return false;
		}
		else
		{
			rail.func_150651_b();
			return rail.func_150649_b(this);
		}
	}

	public void func_150655_a(boolean p_150655_1_, boolean p_150655_2_)
	{
		boolean flag2 = this.func_150647_c(this.railX, this.railY, this.railZ - 1);
		boolean flag3 = this.func_150647_c(this.railX, this.railY, this.railZ + 1);
		boolean flag4 = this.func_150647_c(this.railX - 1, this.railY, this.railZ);
		boolean flag5 = this.func_150647_c(this.railX + 1, this.railY, this.railZ);
		byte b0 = -1;

		if ((flag2 || flag3) && !flag4 && !flag5)
		{
			b0 = 0;
		}

		if ((flag4 || flag5) && !flag2 && !flag3)
		{
			b0 = 1;
		}

		if (!this.isFlexible)
		{
			if (flag3 && flag5 && !flag2 && !flag4)
			{
				b0 = 6;
			}

			if (flag3 && flag4 && !flag2 && !flag5)
			{
				b0 = 7;
			}

			if (flag2 && flag4 && !flag3 && !flag5)
			{
				b0 = 8;
			}

			if (flag2 && flag5 && !flag3 && !flag4)
			{
				b0 = 9;
			}
		}

		if (b0 == -1)
		{
			if (flag2 || flag3)
			{
				b0 = 0;
			}

			if (flag4 || flag5)
			{
				b0 = 1;
			}

			if (!this.isFlexible)
			{
				if (p_150655_1_)
				{
					if (flag3 && flag5)
					{
						b0 = 6;
					}

					if (flag4 && flag3)
					{
						b0 = 7;
					}

					if (flag5 && flag2)
					{
						b0 = 9;
					}

					if (flag2 && flag4)
					{
						b0 = 8;
					}
				}
				else
				{
					if (flag2 && flag4)
					{
						b0 = 8;
					}

					if (flag5 && flag2)
					{
						b0 = 9;
					}

					if (flag4 && flag3)
					{
						b0 = 7;
					}

					if (flag3 && flag5)
					{
						b0 = 6;
					}
				}
			}
		}

		if (b0 == 0 && canMakeSlopes)
		{
			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX, this.railY + 1, this.railZ - 1))
			{
				b0 = 4;
			}

			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX, this.railY + 1, this.railZ + 1))
			{
				b0 = 5;
			}
		}

		if (b0 == 1 && canMakeSlopes)
		{
			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX + 1, this.railY + 1, this.railZ))
			{
				b0 = 2;
			}

			if (BlockRailBase.func_150049_b_(this.railWorld, this.railX - 1, this.railY + 1, this.railZ))
			{
				b0 = 3;
			}
		}

		if (b0 < 0)
		{
			b0 = 0;
		}

		this.listPotentialConnections(b0);
		int i = b0;

		if (this.isFlexible)
		{
			i = this.railWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | b0;
		}

		if (p_150655_2_ || this.railWorld.getBlockMetadata(this.railX, this.railY, this.railZ) != i)
		{
			this.railWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, i, 3);

			for (Object aChunkPos : this.chunkArray) {
				RailLogicHelper rail = this.createRailHelperAtAboveOrBelow((ChunkPosition) aChunkPos);

				if (rail != null) {
					rail.func_150651_b();

					if (rail.func_150649_b(this)) {
						rail.func_150645_c(this);
					}
				}
			}
		}
	}
}
