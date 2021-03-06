package sillelien.scheduler;

import com.xeiam.sundial.SundialJobScheduler;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        while( ! new File(Constants.CONFIG_DIR).exists()) {
            Thread.sleep(500);
        }
        //Grace period for checkout process
        Thread.sleep(500);
        SundialJobScheduler.startScheduler("sillelien.scheduler");
        EnvVarParser.parse();
        YamlParser.parse();
        new CompletableFuture().get();
    }
}
