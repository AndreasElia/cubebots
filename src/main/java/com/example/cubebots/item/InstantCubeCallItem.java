package com.example.cubebots.item;

import com.example.cubebots.entity.CubeBotEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class InstantCubeCallItem extends Item {
    public InstantCubeCallItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            List<CreatureEntity> list = worldIn.getEntitiesWithinAABB(CubeBotEntity.class, playerIn.getBoundingBox().grow(48.0D, 44.0D, 48.0D));

            if (list.isEmpty()) {
                playerIn.sendMessage(new TranslationTextComponent("No entities nearby to call!"));
            } else {
                Iterator listIterator = list.iterator();

                while (listIterator.hasNext()) {
                    CreatureEntity entity = (CreatureEntity) listIterator.next();

                    entity.setPositionAndUpdate(playerIn.posX, playerIn.posY, playerIn.posZ);
                    entity.getNavigator().clearPath();
                }

                playerIn.sendMessage(new TranslationTextComponent("Entities incoming!"));
            }
        }

        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
