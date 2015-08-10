package sillelien.scheduler;

import com.sillelien.dollar.api.var;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FilenameFilter;

import static com.sillelien.dollar.api.DollarStatic.$;
import static com.sillelien.dollar.api.DollarStatic.$yaml;

/**
 *
 *
 * @author hello@neilellis.me
 */
public class YamlParser {
    public static void parse() {
        File dir= new File("/config/sillelien.scheduler/schedules.d");
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".yaml") || name.endsWith(".yml"));
        for (File file : files) {
            System.out.println("Read config file "+file);
            var config = $yaml(file);
            System.out.println("Config was "+config.toJsonString());
        }
    }
}
