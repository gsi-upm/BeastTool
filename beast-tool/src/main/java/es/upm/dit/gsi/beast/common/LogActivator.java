package es.upm.dit.gsi.beast.common;

/**
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author a.carrera
 * 
 */
public class LogActivator {

    public static void logToFile(Logger logger, String name, Level level) {
        try {
            File aux = new File(".");
            String parent = aux.getCanonicalPath();
            File f = new File(parent + File.separator + "log");
            if (!f.isDirectory()) {
                f.mkdir();
            }
            String path = parent + File.separator + "log" + File.separator
                    + name + "%u.log";
            FileHandler fh = new FileHandler(path);
            fh.setFormatter(new SimpleFormatter());
            fh.setEncoding("UTF-8");
            logger.addHandler(fh);
            logger.setLevel(level);
        } catch (IOException e) {
            logger.warning("Error configuring the log file.");
            e.printStackTrace();
        }
    }
}
