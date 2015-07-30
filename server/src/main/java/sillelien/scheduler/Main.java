package sillelien.scheduler;

import com.xeiam.sundial.SundialJobScheduler;

import java.util.concurrent.TimeUnit;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        SundialJobScheduler.startScheduler("sillelien.scheduler");
        EnvVarParser.parse();
    }
}
