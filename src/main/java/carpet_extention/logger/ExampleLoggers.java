package carpet_extention.logger;

import carpet.logging.HUDLogger;
import carpet.logging.Logger;
import carpet.logging.LoggerRegistry;

public class ExampleLoggers {


    public static boolean __block_break;
    public static boolean __setting_change;

    public static boolean __my_pos;




    public static void initLoggers() {

        LoggerRegistry.registerLogger("block_break", ExampleLoggers.simpleLogger("block_break", null, null, false));


        LoggerRegistry.registerLogger("setting_change", ExampleLoggers.simpleLogger("setting_change", null, null, false));

        LoggerRegistry.registerLogger("my_pos", ExampleLoggers.hudLogger("my_pos", null, null, false));






    }


    static HUDLogger hudLogger(String logName, String def, String [] options, boolean strictOptions) {
        return hudLogger(ExampleLoggers.class, logName, def, options, strictOptions);
    }

    static HUDLogger hudLogger(Class<?> clazz, String logName, String def, String [] options, boolean strictOptions) {
        try {
            return new HUDLogger(clazz.getField("__"+logName), logName, def, options, strictOptions);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Failed to create hud logger "+logName);
        }
    }

    static Logger simpleLogger(String logName, String def, String [] options, boolean strictOptions) {
        return simpleLogger(ExampleLoggers.class, logName, def, options, strictOptions);
    }

    static Logger simpleLogger(Class<?> clazz, String logName, String def, String [] options, boolean strictOptions) {
        try {
            return new Logger(clazz.getField("__"+logName), logName, def, options, strictOptions);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Failed to create logger "+logName);
        }
    }


}
