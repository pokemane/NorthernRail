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
	private static final String __OBFID = "CL_00000196";
	private final boolean canMakeSlopes;

	public RailLogicHelper(World p_i45388_2_, int p_i45388_3_, int p_i45388_4_, int p_i45388_5_)
	{
		this.railWorld = p_i45388_2_;
		this.railX = p_i45388_3_;
		this.railY = p_i45388_4_;
		this.railZ = p_i45388_5_;
		BlockRailBase block = (BlockRailBase)p_i45388_2_.getBlock(p_i45388_3_, p_i45388_4_, p_i45388_5_);
		int l = block.getBasicRailMetadata(p_i45388_2_, null, p_i45388_3_, p_i45388_4_, p_i45388_5_);
		this.isFlexible = block.isFlexibleRail(p_i45388_2_, p_i45388_3_, p_i45388_4_, p_i45388_5_);
		canMakeSlopes = block.canMakeSlopes(p_i45388_2_, p_i45388_3_, p_i45388_4_, p_i45388_5_);
		this.func_150648_a(l);
	}

	private void func_150648_a(int p_150648_1_)
	{
		this.chunkArray.clear();

		if (p_150648_1_ == 0)
		{
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (p_150648_1_ == 1)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
		}
		else if (p_150648_1_ == 2)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY + 1, this.railZ));
		}
		else if (p_150648_1_ == 3)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY + 1, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
		}
		else if (p_150648_1_ == 4)
		{
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ - 1));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (p_150648_1_ == 5)
		{
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY + 1, this.railZ + 1));
		}
		else if (p_150648_1_ == 6)
		{
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (p_150648_1_ == 7)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ + 1));
		}
		else if (p_150648_1_ == 8)
		{
			this.chunkArray.add(new ChunkPosition(this.railX - 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
		}
		else if (p_150648_1_ == 9)
		{
			this.chunkArray.add(new ChunkPosition(this.railX + 1, this.railY, this.railZ));
			this.chunkArray.add(new ChunkPosition(this.railX, this.railY, this.railZ - 1));
		}
	}

	private void func_150651_b()
	{
		for (int i = 0; i < this.chunkArray.size(); ++i)
		{
			RailLogicHelper rail = this.func_150654_a((ChunkPosition)this.chunkArray.get(i));

			if (rail != null && rail.func_150653_a(this))
			{
				this.chunkArray.set(i, new ChunkPosition(rail.railX, rail.railY, rail.railZ));
			}
			else
			{
				this.chunkArray.remove(i--);
			}
		}
	}

	private boolean isRailBlockAt(int p_150646_1_, int p_150646_2_, int p_150646_3_)
	{
		return BlockRailBase.func_150049_b_(this.railWorld, p_150646_1_, p_150646_2_, p_150646_3_) || (BlockRailBase.func_150049_b_(this.railWorld, p_150646_1_, p_150646_2_ + 1, p_150646_3_) || BlockRailBase.func_150049_b_(this.railWorld, p_150646_1_, p_150646_2_ - 1, p_150646_3_));
	}

	private RailLogicHelper func_150654_a(ChunkPosition p_150654_1_)
	{
		return BlockRailBase.func_150049_b_(this.railWorld, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY, p_150654_1_.chunkPosZ) ? new RailLogicHelper(this.railWorld, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY, p_150654_1_.chunkPosZ) : (BlockRailBase.func_150049_b_(this.railWorld, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY + 1, p_150654_1_.chunkPosZ) ? new RailLogicHelper(this.railWorld, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY + 1, p_150654_1_.chunkPosZ) : (BlockRailBase.func_150049_b_(this.railWorld, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY - 1, p_150654_1_.chunkPosZ) ? new RailLogicHelper(this.railWorld, p_150654_1_.chunkPosX, p_150654_1_.chunkPosY - 1, p_150654_1_.chunkPosZ) : null));
	}

	private boolean func_150653_a(RailLogicHelper p_150653_1_)
	{
		for (Object aChunkPos : this.chunkArray) {
			ChunkPosition chunkposition = (ChunkPosition) aChunkPos;

			if (chunkposition.chunkPosX == p_150653_1_.railX && chunkposition.chunkPosZ == p_150653_1_.railZ) {
				return true;
			}
		}

		return false;
	}

	private boolean func_150652_b(int p_150652_1_, int p_150652_2_, int p_150652_3_)
	{
		for (Object aChunkArray : this.chunkArray) {
			ChunkPosition chunkposition = (ChunkPosition) aChunkArray;

			if (chunkposition.chunkPosX == p_150652_1_ && chunkposition.chunkPosZ == p_150652_3_) {
				return true;
			}
		}

		return false;
	}

	protected int countAdjacentRails()
	{
		int i = 0;

		if (this.isRailBlockAt(this.railX, this.railY, this.railZ - 1))
		{
			++i;
		}

		if (this.isRailBlockAt(this.railX, this.railY, this.railZ + 1))
		{
			++i;
		}

		if (this.isRailBlockAt(this.railX - 1, this.railY, this.railZ))
		{
			++i;
		}

		if (this.isRailBlockAt(this.railX + 1, this.railY, this.railZ))
		{
			++i;
		}

		return i;
	}

	private boolean func_150649_b(RailLogicHelper p_150649_1_)
	{
		return this.func_150653_a(p_150649_1_) || (this.chunkArray.size() != 2);
	}

	private void func_150645_c(RailLogicHelper p_150645_1_)
	{
		this.chunkArray.add(new ChunkPosition(p_150645_1_.railX, p_150645_1_.railY, p_150645_1_.railZ));
		boolean flag = this.func_150652_b(this.railX, this.railY, this.railZ - 1);
		boolean flag1 = this.func_150652_b(this.railX, this.railY, this.railZ + 1);
		boolean flag2 = this.func_150652_b(this.railX - 1, this.railY, this.railZ);
		boolean flag3 = this.func_150652_b(this.railX + 1, this.railY, this.railZ);
		byte b0 = -1;

		if (flag || flag1)
		{
			b0 = 0;
		}

		if (flag2 || flag3)
		{
			b0 = 1;
		}

		if (!this.isFlexible)
		{
			if (flag1 && flag3 && !flag && !flag2)
			{
				b0 = 6;
			}

			if (flag1 && flag2 && !flag && !flag3)
			{
				b0 = 7;
			}

			if (flag && flag2 && !flag1 && !flag3)
			{
				b0 = 8;
			}

			if (flag && flag3 && !flag1 && !flag2)
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
		RailLogicHelper rail = this.func_150654_a(new ChunkPosition(p_150647_1_, p_150647_2_, p_150647_3_));

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

		this.func_150648_a(b0);
		int i = b0;

		if (this.isFlexible)
		{
			i = this.railWorld.getBlockMetadata(this.railX, this.railY, this.railZ) & 8 | b0;
		}

		if (p_150655_2_ || this.railWorld.getBlockMetadata(this.railX, this.railY, this.railZ) != i)
		{
			this.railWorld.setBlockMetadataWithNotify(this.railX, this.railY, this.railZ, i, 3);

			for (int j = 0; j < this.chunkArray.size(); ++j)
			{
				RailLogicHelper rail = this.func_150654_a((ChunkPosition)this.chunkArray.get(j));

				if (rail != null)
				{
					rail.func_150651_b();

					if (rail.func_150649_b(this))
					{
						rail.func_150645_c(this);
					}
				}
			}
		}
	}
}
