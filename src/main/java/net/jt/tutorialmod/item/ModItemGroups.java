package net.jt.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jt.tutorialmod.TutorialMod;
import net.jt.tutorialmod.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SHATTERED_STEEL_ITEMS_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "shattered_steel_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItem.SHATTERED_STEEL))
                    .displayName(Text.translatable("itemGroup.tutorialmod.shattered_steel_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItem.SHATTERED_STEEL);
                        entries.add(ModItem.SHATTERED_WASTE);

                        entries.add(ModItem.CHISEL);

                    }).build()
    );

    public static final ItemGroup SHATTERED_STEEL_BLOCKS_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "shattered_steel_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.SHATTERED_STEEL_BLOCK))
                    .displayName(Text.translatable("itemGroup.tutorialmod.shattered_steel_blocks"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.SHATTERED_WASTE_BLOCK);
                        entries.add(ModBlocks.SHATTERED_STEEL_BLOCK);

                        entries.add(ModBlocks.MAGIC_BLOCK);




                    }).build()
    );
    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for " + TutorialMod.MOD_ID);
    }
}

