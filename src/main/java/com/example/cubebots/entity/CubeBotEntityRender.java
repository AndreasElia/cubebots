package com.example.cubebots.entity;

import com.example.cubebots.CubeBots;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

import javax.annotation.Nullable;

public class CubeBotEntityRender extends LivingRenderer<CubeBotEntity, CubeBotEntityModel> {
    public CubeBotEntityRender(EntityRendererManager manager) {
        super(manager, new CubeBotEntityModel(), 0.3F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(CubeBotEntity entity) {
        return new ResourceLocation(CubeBots.MOD_ID, "textures/entity/cube_bot_entity.png");
    }

    public static class RenderFactory implements IRenderFactory<CubeBotEntity> {
        @Override
        public EntityRenderer<? super CubeBotEntity> createRenderFor(EntityRendererManager manager) {
            return new CubeBotEntityRender(manager);
        }

    }
}
