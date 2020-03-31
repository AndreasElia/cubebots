package com.example.cubebots;

import static com.example.cubebots.CubeBots.MOD_ID;

import com.example.cubebots.entity.CubeBotEntityRenderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MOD_ID)
public class CubeBots {
    public static final String MOD_ID = "cubebots";

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    // Create the item group.
    public static final ItemGroup cubeBotsGroup = new CubeBotsGroup();

    public CubeBots() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        CubeBotEntityRenderRegistry.registryEntityRenders();

        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }
}
