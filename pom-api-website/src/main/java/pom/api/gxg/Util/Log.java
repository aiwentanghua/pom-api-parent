package pom.api.gxg.Util;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Log {

    private static Logger logger=Logger.getLogger(Log.class);

    public static void info(String data){

        logger.info(data);
        return;
    }

    public static void debug(String data){

        logger.debug(data);
        return;
    }

    public static void error(String data){

        logger.error(data);
        return;
    }
    public static String getCrashMessage(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }
}
