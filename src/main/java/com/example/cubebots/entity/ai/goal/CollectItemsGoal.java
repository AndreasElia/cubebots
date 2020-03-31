package com.example.cubebots.entity.ai.goal;

import com.example.cubebots.entity.CubeBotEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;

import java.util.EnumSet;
import java.util.List;

public class CollectItemsGoal extends Goal {
    protected final CubeBotEntity bot;
    protected final double range;
    protected final float speed;
    private final float areaSize;
    private int timeToRecalculatePath;

    private ItemEntity currentTarget;

    public CollectItemsGoal(CubeBotEntity bot, double range, float speed) {
        this.bot = bot;
        this.range = range;
        this.speed = speed;
        this.areaSize = 30;
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean shouldExecute() {
        List<ItemEntity> lvt_1_1_ = this.bot.world.getEntitiesWithinAABB(ItemEntity.class, this.bot.getBoundingBox().grow((double) this.areaSize), null);

        for(ItemEntity ent:lvt_1_1_) {
            if (!ent.isInvisible()) {
                this.currentTarget = ent;
                return true;
            }
        }

        return false;
    }

    public boolean shouldContinueExecuting() {
        return this.currentTarget != null && !this.bot.getNavigator().noPath();
    }

    public void startExecuting() {
        this.timeToRecalculatePath = 0;
    }

    public void resetTask() {
        this.currentTarget = null;
        this.bot.getNavigator().clearPath();
    }

    public void tick() {
        if (this.currentTarget != null) {
            this.bot.getLookController().setLookPositionWithEntity(this.currentTarget, 10.0F, (float) this.bot.getVerticalFaceSpeed());

            if (--this.timeToRecalculatePath <= 0) {
                this.timeToRecalculatePath = 10;
                this.bot.getNavigator().tryMoveToEntityLiving(this.currentTarget, 1.2);
            }

            if (this.bot.getDistance(this.currentTarget) < 1.6) {
                this.currentTarget.remove();
                this.currentTarget = null;
            }
        }
    }
}
