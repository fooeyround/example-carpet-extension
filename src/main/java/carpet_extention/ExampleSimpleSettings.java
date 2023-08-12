package carpet_extention;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Rule;

import carpet.api.settings.Validator;
import carpet.api.settings.Validators;
import carpet.utils.Messenger;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.api.settings.RuleCategory.*;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class ExampleSimpleSettings
{
    /**
     *  Custom validator class for your setting. If validate returns null - settings is not changed.
     */
    private static class CheckValue extends Validator<Integer>
    {
        @Override
        public Integer validate(ServerCommandSource source, CarpetRule<Integer> currentRule, Integer newValue, String typedString)
        {
            Messenger.m(source, "rb Congrats, you just changed a setting to "+newValue);
            return newValue < 20000000 ? newValue : null;
        }
    }

    /**
     *  Simple numeric setting, no use otherwise
     */
    @Rule(
            options = {"32768", "250000", "1000000"},
            validators = {Validators.NonNegativeNumber.class, CheckValue.class},
            categories = {CREATIVE, "examplemod"}
    )
    public static int uselessNumericalSetting = 32768;


    /**
     * You can define your own categories. It makes sense to create new category for all settings in your mod. (it makes sorting rules from mods easier)
     */
    @Rule(categories = {"examplemod", "fun"})
    public static boolean flyingPigs = false;

}