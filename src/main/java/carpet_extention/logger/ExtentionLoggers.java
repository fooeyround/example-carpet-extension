package carpet_extention.logger;

import carpet.logging.Logger;
import carpet.logging.LoggerRegistry;
import carpet_extention.ExtensionServer;

public class ExtentionLoggers {


    public static boolean __block_logger;


    public static void initLoggers() {

        LoggerRegistry.registerLogger("block_logger", ExtentionLoggers.simpleLogger("block_logger", null, null, false));

    }




    static Logger simpleLogger(String logName, String def, String [] options, boolean strictOptions) {
        try
        {
            return new Logger(ExtentionLoggers.class.getField("__"+logName), logName, def, options, strictOptions);
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException("Failed to create logger "+logName);
        }
    }


}
