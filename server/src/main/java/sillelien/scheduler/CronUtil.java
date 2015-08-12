package sillelien.scheduler;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

/**
 * @author hello@neilellis.me
 */
public class CronUtil {
    public static final String NUMERIZED_SCHEDULE = "^(\\w*)_CRON_SCHEDULE_([0-9]+)$";
    public static final Pattern COMPILED_NUM_SCHED = Pattern.compile(NUMERIZED_SCHEDULE);
    public static final String CRON_PATTERN = "^(\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S)+\\s+(.*)$";
    public static final Pattern COMPILED_CRON = Pattern.compile(CRON_PATTERN);

    @NotNull
    static String convertCron(String cron) {
        String[] splitCron = cron.split(" ");
        String dayOfWeek = splitCron[4];
        if (dayOfWeek.equals("*")) {
            dayOfWeek = "?";
        }
        return "0 " + splitCron[0] + " " + splitCron[1] + " " + splitCron[2] + " " + splitCron[3] + " " + dayOfWeek;
    }
}
