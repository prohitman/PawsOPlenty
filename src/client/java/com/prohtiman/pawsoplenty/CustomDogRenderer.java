package com.prohtiman.pawsoplenty;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WolfCollarLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;

public class CustomDogRenderer extends MobRenderer<CustomDog, CustomDogModel<CustomDog>> {
    private static final ResourceLocation WOLF_LOCATION = new ResourceLocation("textures/entity/wolf/wolf.png");
    private static final ResourceLocation WOLF_TAME_LOCATION = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
    private static final ResourceLocation WOLF_ANGRY_LOCATION = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

    public CustomDogRenderer(EntityRendererProvider.Context context) {
        super(context, new CustomDogModel<>(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
        //this.addLayer(new WolfCollarLayer(this));
    }

    protected float getBob(CustomDog wolf, float f) {
        return wolf.getTailAngle();
    }

    public void render(CustomDog wolf, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        if (wolf.isWet()) {
            float h = wolf.getWetShade(g);
            ((CustomDogModel)this.model).setColor(h, h, h);
        }

        super.render(wolf, f, g, poseStack, multiBufferSource, i);
        if (wolf.isWet()) {
            ((CustomDogModel)this.model).setColor(1.0F, 1.0F, 1.0F);
        }

    }

    public ResourceLocation getTextureLocation(CustomDog wolf) {
        if (wolf.isTame()) {
            return WOLF_TAME_LOCATION;
        } else {
            return wolf.isAngry() ? WOLF_ANGRY_LOCATION : WOLF_LOCATION;
        }
    }
}
