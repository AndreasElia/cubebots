package com.example.cubebots.entity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class CubeBotEntityRenderRegistry {
    public static void registryEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(CubeBotEntity.class, new CubeBotEntityRender.RenderFactory());
    }
}
