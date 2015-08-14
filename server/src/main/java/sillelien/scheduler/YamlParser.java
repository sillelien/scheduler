package sillelien.scheduler;

import com.sillelien.dollar.api.collections.ImmutableList;
import com.sillelien.dollar.api.var;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.sillelien.dollar.api.DollarStatic.$;
import static com.sillelien.dollar.api.DollarStatic.$yaml;

/**
 * @author hello@neilellis.me
 */
public class YamlParser {

    static {
        Path dir = Paths.get(Constants.CONFIG_DIR);

            ExecutorService watcherThread = Executors.newSingleThreadExecutor();
            watcherThread.submit(() -> {
                try {
                    new WatchDir(dir, true).processEvents((watchEvent, path) -> {
                        System.out.printf("Event " + watchEvent);
                        parseFile(path.toFile());
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


    }

    public static void parse() {
        File dir = new File(Constants.CONFIG_DIR);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".yaml") || name.endsWith(".yml"));
        if (files == null) {
            System.out.println("No files to parse");
            return;
        }
        for (File file : files) {
            parseFile(file);
        }
    }

    private static void parseFile(File file) {
        System.out.println("Parsing " + file);
        var config = $yaml(file);
        ImmutableList<var> jobs = config.$list();
        for (var job : jobs) {
            try {
                new VarBasedScheduler(job).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Config " + config.toJsonString());
    }
}
