package com.example.cubebots;

import com.example.cubebots.init.CubeBotsItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CubeBotsGroup extends ItemGroup {
    public CubeBotsGroup() {
        super(CubeBots.MOD_ID);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(CubeBotsItems.dried_fruit);
    }
}
