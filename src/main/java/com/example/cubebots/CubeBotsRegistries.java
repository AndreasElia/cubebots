package com.example.cubebots;

import com.example.cubebots.init.CubeBotsBlocks;
import com.example.cubebots.init.CubeBotsEntities;
import com.example.cubebots.init.CubeBotsItems;
import com.example.cubebots.item.CubeCallItem;
import com.example.cubebots.item.InstantCubeCallItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubeBotsRegistries {
    public static final Logger LOGGER = CubeBots.LOGGER;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                CubeBotsItems.dried_fruit = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "dried_fruit"),
                CubeBotsItems.cube_bot = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_bot"),
                CubeBotsItems.cube_call = new CubeCallItem(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_call"),
                CubeBotsItems.cube_core = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_core"),
                CubeBotsItems.cube_infinity_core = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_infinity_core"),
                CubeBotsItems.cube_life_core = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_life_core"),
                CubeBotsItems.cube_piece = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_piece"),
                CubeBotsItems.cube_nether_piece = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "cube_nether_piece"),
                CubeBotsItems.instant_cube_call = new InstantCubeCallItem(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "instant_cube_call"),
                CubeBotsItems.marker = new Item(new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBots.MOD_ID, "marker"),
                CubeBotsItems.fruit_block = new BlockItem(CubeBotsBlocks.fruit_block, new Item.Properties().group(CubeBots.cubeBotsGroup)).setRegistryName(CubeBotsBlocks.fruit_block.getRegistryName()),
                CubeBotsItems.cube_bot_spawn_egg = CubeBotsEntities.registerEntitySpawnEgg(CubeBotsEntities.cube_bot_entity, 0xffffff, 0x000000, "cube_bot_spawn_egg", CubeBots.cubeBotsGroup)
        );

        CubeBots.LOGGER.info("Items registered.");
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                CubeBotsBlocks.fruit_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(5).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(CubeBots.MOD_ID, "fruit_block")
        );

        CubeBots.LOGGER.info("Blocks registered.");
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(
                CubeBotsEntities.cube_bot_entity
        );

        CubeBotsEntities.registerEntityWorldSpawn(CubeBotsEntities.cube_bot_entity, Biomes.PLAINS, Biomes.BEACH, Biomes.JUNGLE);

        CubeBots.LOGGER.info("Entities registered.");
    }
}
