package net.jt.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.jt.tutorialmod.block.ModBlocks;
import net.jt.tutorialmod.item.ModItem;
import net.jt.tutorialmod.block.ModBlocks;
import net.jt.tutorialmod.item.ModItem;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHATTERED_STEEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SHATTERED_WASTE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MAGIC_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItem.SHATTERED_STEEL, Models.GENERATED);
        itemModelGenerator.register(ModItem.SHATTERED_WASTE, Models.GENERATED);

        itemModelGenerator.register(ModItem.CAULIFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItem.CHISEL, Models.GENERATED);
        itemModelGenerator.register(ModItem.STARLIGHT_ASHES, Models.GENERATED);

        itemModelGenerator.register(ModItem.BAN_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItem.SHATTERED_STEEL_SWORD, Models.HANDHELD);


    }
}