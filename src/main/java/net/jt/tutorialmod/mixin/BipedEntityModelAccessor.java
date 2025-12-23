package net.jt.tutorialmod.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BipedEntityModel.class)
public interface BipedEntityModelAccessor {

    @Accessor("rightArm")
    ModelPart tutorialmod$getRightArm();

    @Accessor("leftArm")
    ModelPart tutorialmod$getLeftArm();
}
