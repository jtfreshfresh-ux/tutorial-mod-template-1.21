package net.jt.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.jt.tutorialmod.block.ModBlocks;
import net.jt.tutorialmod.component.ModDateComponentTypes;
import net.jt.tutorialmod.item.ModItem;
import net.jt.tutorialmod.item.ModItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItemGroups.registerItemGroups();

		LOGGER.info("Hello Fabric world!");
		ModItem.registerModItems();
		ModBlocks.registerModBlocks();
		ModDateComponentTypes.registerDataComponentTypes();

		FuelRegistry.INSTANCE. add(ModItem.STARLIGHT_ASHES, 600);
	}
}