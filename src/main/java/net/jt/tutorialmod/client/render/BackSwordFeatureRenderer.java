package net.jt.tutorialmod.client.render;

import net.jt.tutorialmod.item.ModItem;
import net.jt.tutorialmod.item.ModItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class BackSwordFeatureRenderer
        extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    public BackSwordFeatureRenderer(
            FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context
    ) {
        super(context);
    }

    @Override
    public void render(
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            AbstractClientPlayerEntity player,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch
    ) {
        // ---- Step 2.1: check hotbar ----
        boolean hasSwordInHotbar = false;
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(ModItem.BAN_SWORD)) {
                hasSwordInHotbar = true;
                break;
            }
        }

        // ---- Step 2.2: check hands ----
        boolean holdingSword =
                player.getMainHandStack().isOf(ModItem.BAN_SWORD) ||
                        player.getOffHandStack().isOf(ModItem.BAN_SWORD);

        if (!hasSwordInHotbar || holdingSword) return;

        matrices.push();

// Attach to torso
        this.getContextModel().body.rotate(matrices);

// Move to upper back
        matrices.translate(0.0F, 0.30F, 0.21F);

// Flip model upright
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180F));

// Rotate so blade lies flat on back
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180F));

// Diagonal NW orientation
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180F));

// Slight inward tilt so it hugs the back
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(0F));

// Optional scale
        matrices.scale(1.5F, 1.5F, 1.5F);

        MinecraftClient.getInstance().getItemRenderer().renderItem(
                player,
                new ItemStack(ModItem.BAN_SWORD),
                ModelTransformationMode.FIXED,
                false,
                matrices,
                vertexConsumers,
                player.getWorld(),
                light,
                0,
                0
        );

        matrices.pop();
    }
}
