package net.jt.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jt.tutorialmod.TutorialMod;
import net.jt.tutorialmod.item.custom.ChiselItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItem {
    public static final Item SHATTERED_WASTE = register( "shattered_waste", new Item(new Item.Settings()));

    public static final Item SHATTERED_STEEL = register( "shattered_steel", new Item(new Item.Settings()));

    public static final Item CHISEL = register("chisel", new ChiselItem(new Item.Settings().maxDamage(32)));

    public static final Item STARLIGHT_ASHES = register("starlight_ashes", new Item(new Item.Settings()));

    public static final Item CAULIFLOWER = register("cauliflower", new Item(new Item.Settings().food(ModFoodComponents.CUALIFLOWER)));
    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);
    }
    public static void registerModItems() {

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(fabricItemGroupEntries -> {
           fabricItemGroupEntries.add(SHATTERED_WASTE);
           fabricItemGroupEntries.add(SHATTERED_STEEL);

        });
    }

}

