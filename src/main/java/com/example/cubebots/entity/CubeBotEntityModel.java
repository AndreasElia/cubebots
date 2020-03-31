package com.example.cubebots.entity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CubeBotEntityModel extends EntityModel<CubeBotEntity> {
    private final RendererModel body;
    private final RendererModel leftLeg;
    private final RendererModel rightLeg;

    public CubeBotEntityModel() {
        textureWidth = 64;
        textureHeight = 32;

        body = new RendererModel(this, 0, 0);
        body.addBox(-6F, 11F, -6F, 12, 12, 12);
        body.setRotationPoint(0F, 0F, 0F);
        body.setTextureSize(64, 32);
        body.mirror = true;

        leftLeg = new RendererModel(this, 0, 29);
        leftLeg.addBox(2F, 0F, -1F, 2, 1, 2);
        leftLeg.setRotationPoint(0F, 23F, 0F);
        leftLeg.setTextureSize(64, 32);
        leftLeg.mirror = true;

        rightLeg = new RendererModel(this, 8, 29);
        rightLeg.addBox(-4F, 0F, -1F, 2, 1, 2);
        rightLeg.setRotationPoint(0F, 23F, 0F);
        rightLeg.setTextureSize(64, 32);
        rightLeg.mirror = true;
    }

    @Override
    public void render(CubeBotEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        body.render(f5);
        leftLeg.render(f5);
        rightLeg.render(f5);
    }

    private void setRotationAngles(RendererModel modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
