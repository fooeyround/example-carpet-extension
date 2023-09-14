package carpet_extention.logger;

import carpet.logging.Logger;
import carpet.logging.LoggerRegistry;

public class ExampleLoggers {


    public static boolean __block_break;
    public static boolean __setting_change;



    public static void initLoggers() {

        LoggerRegistry.registerLogger("block_break", ExampleLoggers.simpleLogger("block_break", null, null, false));


        LoggerRegistry.registerLogger("setting_change", ExampleLoggers.simpleLogger("setting_change", null, null, false));


    }


    static Logger simpleLogger(String logName, String def, String [] options, boolean strictOptions) {
        return simpleLogger(ExampleLoggers.class, logName, def, options, strictOptions);
    }

    static Logger simpleLogger(Class<?> clazz, String logName, String def, String [] options, boolean strictOptions) {
        try
        {
            return new Logger(clazz.getField("__"+logName), logName, def, options, strictOptions);
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException("Failed to create logger "+logName);
        }
    }


}
