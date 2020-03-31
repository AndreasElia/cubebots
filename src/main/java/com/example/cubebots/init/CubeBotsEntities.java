package com.example.cubebots.init;

import com.example.cubebots.CubeBots;
import com.example.cubebots.CubeBotsRegistries;
import com.example.cubebots.entity.CubeBotEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.ObjectHolder;

public class CubeBotsEntities {
    public static EntityType<?> cube_bot_entity = EntityType.Builder.create(CubeBotEntity::new, EntityClassification.MISC).build(CubeBots.MOD_ID + ":cube_bot_entity").setRegistryName(new ResourceLocation(CubeBots.MOD_ID, "cube_bot_entity"));

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name, ItemGroup itemGroup) {
        return new SpawnEggItem(type, color1, color2, new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(new ResourceLocation(CubeBots.MOD_ID, "cube_bot_spawn_egg"));
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, Biome... biomes) {
        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, 10, 1, 10));
            }
        }
    }
}
