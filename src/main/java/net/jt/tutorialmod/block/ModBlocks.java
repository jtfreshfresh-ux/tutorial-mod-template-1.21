package net.jt.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jt.tutorialmod.TutorialMod;
import net.jt.tutorialmod.block.custom.MagicBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.DropperBlock;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {


    public static final Block SHATTERED_STEEL_BLOCK = register("shattered_steel_block",
            new Block(AbstractBlock.Settings.create().strength(3.0f, 1200.0f)
                    .requiresTool().sounds(BlockSoundGroup.ANVIL)));

    public static final Block SHATTERED_WASTE_BLOCK = register("shattered_waste_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 1),
                    AbstractBlock.Settings.create().strength(2.5f, 1200.0f).requiresTool()));
    public static final Block MAGIC_BLOCK = register("magic_block",
            new MagicBlock(AbstractBlock.Settings.create().strength(1f).requiresTool()));

        private static  Block register(String name, Block block) {
            registerBlockItem(name, block);
            return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);

        }

        private static void registerBlockItem(String name, Block block) {
            Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                    new BlockItem(block, new Item.Settings()));
        }

        public  static void registerModBlocks() {
            TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

            ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(fabricItemGroupEntries -> {
                fabricItemGroupEntries.add(ModBlocks.SHATTERED_WASTE_BLOCK);
                fabricItemGroupEntries.add(ModBlocks.SHATTERED_STEEL_BLOCK);
            });
        }

}
