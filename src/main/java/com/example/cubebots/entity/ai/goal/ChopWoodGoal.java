package com.example.cubebots.entity.ai.goal;

import com.example.cubebots.entity.CubeBotEntity;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ChopWoodGoal extends Goal {
    protected final CubeBotEntity bot;
    protected final double range;
    protected final float speed;
    private final float areaSize;
    private int timeToRecalculatePath;
    private List<BlockPos> woodList = new ArrayList();

    private BlockPos currentTarget;

    public ChopWoodGoal(CubeBotEntity bot, double range, float speed) {
        this.bot = bot;
        this.range = range;
        this.speed = speed;
        this.areaSize = 30;
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean shouldExecute() {
        if (this.woodList.isEmpty()) {
            AxisAlignedBB axisComparison = this.bot.getBoundingBox().grow((double) this.areaSize);

            int var3 = MathHelper.floor(axisComparison.minX);
            int var4 = MathHelper.floor(axisComparison.maxX + 1.0D);
            int var5 = MathHelper.floor(axisComparison.minY);
            int var6 = MathHelper.floor(axisComparison.maxY + 1.0D);
            int var7 = MathHelper.floor(axisComparison.minZ);
            int var8 = MathHelper.floor(axisComparison.maxZ + 1.0D);

            for (int var9 = var3; var9 < var4; ++var9) {
                for (int var10 = var5; var10 < var6; ++var10) {
                    for (int var11 = var7; var11 < var8; ++var11) {
                        Block var12 = this.bot.getEntityWorld().getBlockState(new BlockPos(var9, var10, var11)).getBlock();

                        if (var12 instanceof LogBlock) {
                            this.woodList.add(new BlockPos(var9, var10, var11));
                        }
                    }
                }
            }
        }

        return !this.woodList.isEmpty();
    }

    public boolean shouldContinueExecuting() {
        return !this.woodList.isEmpty() && !this.bot.getNavigator().noPath();
    }

    public void startExecuting() {
        this.timeToRecalculatePath = 0;
    }

    public void resetTask() {
        this.currentTarget = null;
        this.bot.getNavigator().clearPath();
    }

    public void tick() {
        if (this.currentTarget == null) {
            BlockPos b = null;
            double dist = -1;

            for (int a = 0; a < this.woodList.size(); a++) {
                BlockPos b1 = this.woodList.get(a);

                if (this.bot.getEntityWorld().getBlockState(b1).getBlock() instanceof LogBlock) {
                    double dsqr = this.bot.getDistanceSq(b1.getX(), b1.getY(), b1.getZ());

                    if ((dist == -1) || (dsqr <= (dist * dist))) {
                        b = b1;
                        dist = Math.sqrt(dsqr);
                    }
                } else {
                    this.woodList.remove(b1);
                }
            }

            if (b != null) {
                this.currentTarget = b;
            }
        }

        if (this.currentTarget != null) {
            Block block = this.bot.getEntityWorld().getBlockState(new BlockPos(this.currentTarget)).getBlock();

            if ((block instanceof LogBlock) == false) {
                this.woodList.remove(this.currentTarget);
                this.currentTarget = null;
                return;
            }

            this.bot.getNavigator().tryMoveToXYZ(this.currentTarget.getX(), this.bot.posY, this.currentTarget.getZ(), 1.2);

            if (this.bot.getDistanceSq(this.currentTarget.getX(), this.bot.posY, this.currentTarget.getZ()) <= 2.5) {
                this.bot.getNavigator().clearPath();
            }

            if (this.bot.getDistanceSq(this.currentTarget.getX(), this.bot.posY, this.currentTarget.getZ()) <= 5) {
                this.bot.getLookController().setLookPosition(this.currentTarget.getX(), this.currentTarget.getY(), this.currentTarget.getZ(), 30, 30);

                this.bot.getEntityWorld().destroyBlock(new BlockPos(this.currentTarget.getX(), this.currentTarget.getY(), this.currentTarget.getZ()), true);

                this.woodList.remove(this.currentTarget);
                this.currentTarget = null;
            }
        }
    }
}
