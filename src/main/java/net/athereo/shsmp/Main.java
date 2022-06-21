package net.athereo.shsmp;

import net.athereo.shsmp.event.PlayerDeathCallback;
import net.athereo.shsmp.event.PlayerJoinCallback;
import net.athereo.shsmp.item.NecronomiconItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final String MOD_ID = "shsmp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Item item = new NecronomiconItem(new Item.Settings().group(ItemGroup.MISC));

	public static final Scoreboard scoreboard = new Scoreboard();
	public static Team aliveTeam = scoreboard.getTeam("alive");
	public static Team deadTeam = scoreboard.getTeam("dead");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing SHSMP-Fabric...");
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "necronomicon"), item);

		if (aliveTeam == null) {
			aliveTeam = scoreboard.addTeam("alive");
			LOGGER.info("Alive team created");
		}

		if (deadTeam == null) {
			deadTeam = scoreboard.addTeam("dead");
			LOGGER.info("Dead team created");
		}

		PlayerDeathCallback.EVENT.register((player, source) -> {
			LOGGER.info(player.getEntityName());
			LOGGER.info(source.getName());

			player.sendMessage(Text.literal("§4§lSigh. Better luck next time."));
			player.changeGameMode(GameMode.SPECTATOR);
			scoreboard.addPlayerToTeam(player.getEntityName(), deadTeam);

			for (String str : deadTeam.getPlayerList()) {
				LOGGER.info(str);
			}
		});

		PlayerJoinCallback.EVENT.register((player, server) -> {
			LOGGER.info(player.getEntityName() + " Joined le server");
		});

		LOGGER.info("SHSMP Finished Initializing");
	}
}
