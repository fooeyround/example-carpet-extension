package carpet_extention;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.CarpetRule;
import carpet.api.settings.SettingsManager;
import carpet.logging.LoggerRegistry;
import carpet.utils.Messenger;
import carpet.utils.Translations;
import carpet_extention.commands.ExampleCommand;
import carpet_extention.logger.ExtentionLoggers;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;

import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Check CarpetExtension interface for more events.
 */
public class ExtensionServer implements ModInitializer, CarpetExtension {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.


	//!Replace this with your mods modid!
	public static final String MODID = "modid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	private static final SettingsManager mySettingManager = new SettingsManager("1.0.0", MODID,"Example Mod");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//This is also where we will initialize out carpet extension and Settings Manager!

		//we can't access carpet's settings manager here (yet)!

		CarpetServer.manageExtension(new ExtensionServer());

		// Let's have our own settings class independent of carpet.conf
		mySettingManager.parseSettingsClass(ExampleOwnSettings.class);





		LOGGER.info("Example Carpet Extension Loaded! CHANGEME");
	}


	/**
	 * Currently anything needing {@link CarpetServer#settingsManager} needs to be here because this is right after it is initialized.
	 */
	@Override
	public void onGameStarted() {

		// let's /carpet handle our few simple settings
		CarpetServer.settingsManager.parseSettingsClass(ExampleSimpleSettings.class);

		// set up a ruleObserver to observe when rules change in carpet.
		//only observes rules from the settings managers it has been registered to.
		// use registerGlobalObserver to observe rules from all settings managers (that allow for this feature)
		CarpetServer.settingsManager.registerRuleObserver(ExtensionServer::exampleRuleObserver);
	}

	@Override
	public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher, final CommandRegistryAccess commandBuildContext) {
		ExampleCommand.register(dispatcher);
	}



	@Override
	public void registerLoggers() {
		ExtentionLoggers.initLoggers();
	}

	@Override
	public SettingsManager extensionSettingsManager() {
		// this will ensure that our settings are loaded properly when world loads
		return mySettingManager;
	}


	@Override
	public Map<String, String> canHasTranslations(String lang) {
		return Translations.getTranslationFromResourcePath("assets/modid/lang/%s.json".formatted(lang));
	}

	public static void exampleRuleObserver(ServerCommandSource serverCommandSource, CarpetRule<?> currentRuleState, String originalUserTest) {
		if (currentRuleState.categories().contains("examplemod")) {
			Messenger.m(
					serverCommandSource,
					"gi Psssst... make sure not to touch original carpet rules"
			);
			// obviously you can change original carpet rules
		} else if (serverCommandSource.getPlayer() != null) {
			Messenger.print_server_message(
					serverCommandSource.getServer(),
					"Ehlo everybody, "+serverCommandSource.getPlayer().getName().getString()+" is cheating..."
			);
		}
	}
}