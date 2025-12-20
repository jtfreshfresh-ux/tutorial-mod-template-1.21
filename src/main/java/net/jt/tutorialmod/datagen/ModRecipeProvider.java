package net.jt.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.jt.tutorialmod.block.ModBlocks;
import net.jt.tutorialmod.item.ModItem;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        List<ItemConvertible> SHATTERED_STEEL_SMELTABLES = List.of(ModItem.SHATTERED_WASTE);

        offerSmelting(exporter, SHATTERED_STEEL_SMELTABLES, RecipeCategory.MISC, (ItemConvertible) ModItem.SHATTERED_STEEL, 0.25f, (int) 200f, "shattered_steel");
        offerBlasting(exporter, SHATTERED_STEEL_SMELTABLES, RecipeCategory.MISC, (ItemConvertible) ModItem.SHATTERED_STEEL, 0.25f, (int) 100f, "shattered_steel");

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.BAN_SWORD)
                .pattern(" S ")
                .pattern("TET")
                .pattern(" N ")
                .input('N', Items.NETHERITE_SWORD)
                .input('S', Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .input('T', ModItem.SHATTERED_STEEL)
                .input('E', Items.DRAGON_EGG)
                .criterion(hasItem(Items.NETHERITE_SWORD), conditionsFromItem(Items.NETHERITE_SWORD))
                .criterion(hasItem(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE), conditionsFromItem(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE))
                .criterion(hasItem(ModItem.SHATTERED_STEEL), conditionsFromItem(ModItem.SHATTERED_STEEL))
                .criterion(hasItem(Items.DRAGON_EGG), conditionsFromItem(Items.DRAGON_EGG))
                .offerTo(exporter);


        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SHATTERED_STEEL_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItem.SHATTERED_STEEL)
                .criterion(hasItem(ModItem.SHATTERED_STEEL), conditionsFromItem(ModItem.SHATTERED_STEEL))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItem.SHATTERED_STEEL, 9)
                .input(ModBlocks.SHATTERED_STEEL_BLOCK)
                .criterion(hasItem(ModBlocks.SHATTERED_STEEL_BLOCK), conditionsFromItem(ModBlocks.SHATTERED_STEEL_BLOCK))
                .offerTo(exporter);


    }
}
