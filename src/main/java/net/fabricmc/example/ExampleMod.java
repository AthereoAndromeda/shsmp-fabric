package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.event.PlayerDeathCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "shsmp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Item item = new NecronomiconItem(new Item.Settings().group(ItemGroup.MISC));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "necronomicon"), item);
		
		PlayerDeathCallback.EVENT.register((player, source) -> {
			// LOGGER.info("Event registrerar");
			LOGGER.info(player.getEntityName());
			LOGGER.info(source.getName());

			// TextColor.
			player.sendMessage(Text.literal("§4§lSigh. Better luck next time."));
			player.changeGameMode(GameMode.SPECTATOR);
		});
	}
}
