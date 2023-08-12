package carpet_extention;

import carpet.api.settings.Rule;

import carpet.api.settings.RuleCategory.*;


/**
 * Here is your example Settings class that will be a part of  /{@link ExtensionServer} settings command,
 *
 * Descriptions, Extra and any other form of translation are handled by {@link ExtensionServer#canHasTranslations(String)} and stored in assets/modid/lang/
 */
public class ExampleOwnSettings {


    public static final String MISC = "misc";
    public enum Option {
        OPTION_A,
        OPTION_B,
        OPTION_C
    }

    @Rule(categories = MISC)
    public static int intSetting = 10;

    @Rule(categories = MISC, options = {"foo", "bar", "baz"}, strict = false)
    public static String stringSetting = "foo";

    @Rule(categories = MISC)
    public static Option optionSetting = Option.OPTION_A;

    @Rule(categories = MISC)
    public static boolean boolSetting = false;
}