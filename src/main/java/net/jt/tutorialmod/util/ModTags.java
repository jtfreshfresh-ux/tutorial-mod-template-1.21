package net.jt.tutorialmod.util;

import net.jt.tutorialmod.TutorialMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.security.cert.CRLException;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_BAN_TOOL = createTag("needs_ban_tool");
        public static final TagKey<Block> INCORRECT_FOR_BAN_TOOL = createTag("incorrect_for_ban_tool");
        public static final TagKey<Block> NEEDS_SHATTERED_STEEL_TOOL = createTag("needs_shattered_steel_tool");
        public static final TagKey<Block> INCORRECT_FOR_SHATTERED_STEEL_TOOL = createTag("incorrect_for_shattered_steel_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name));
        }
    }
}