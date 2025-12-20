package net.jt.tutorialmod.component;

import net.jt.tutorialmod.TutorialMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDateComponentTypes {
    public static final ComponentType<BlockPos> CORDINATES = register("cordinates", builder -> builder.codec(BlockPos.CODEC));


    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderUnaryOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TutorialMod.MOD_ID, name),
                builderUnaryOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        TutorialMod.LOGGER.info("Registering Data Component Types for " + TutorialMod.MOD_ID);
    }
}
