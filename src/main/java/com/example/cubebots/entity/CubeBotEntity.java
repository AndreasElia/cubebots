package com.example.cubebots.entity;

import com.example.cubebots.entity.ai.goal.ChopWoodGoal;
import com.example.cubebots.entity.ai.goal.CollectItemsGoal;
import com.example.cubebots.init.CubeBotsEntities;
import com.example.cubebots.init.CubeBotsItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CubeBotEntity extends CreatureEntity {
    public boolean tamable;
    public boolean tamed;
    public String tamer;

    public Inventory inventory = new Inventory(8);

    public List<BlockPos> markedChests = new ArrayList();

    public CubeBotEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super((EntityType<? extends CreatureEntity>) CubeBotsEntities.cube_bot_entity, worldIn);
        this.setCustomNameVisible(false);
    }

    @Override
    public EntitySize getSize(Pose pose) {
        return new EntitySize(0.52F, 0.6F, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new ChopWoodGoal(this, 12F, 1.15F));
        // this.goalSelector.addGoal(2, new CollectItemsGoal(this, 12F, 1.15F));
        this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (new Random().nextInt(7) >= 5) {
            ItemEntity itemDrop = new ItemEntity(this.world, this.posX, this.posY, this.posZ, new ItemStack(CubeBotsItems.cube_piece, new Random().nextInt(2) + 1));
            this.world.addEntity(itemDrop);
        }

        if (new Random().nextInt(150) >= 22) {
            ItemEntity itemDrop = new ItemEntity(this.world, this.posX, this.posY, this.posZ, new ItemStack(CubeBotsItems.cube_core, new Random().nextInt(2) + 1));
            this.world.addEntity(itemDrop);
        }
    }
}
