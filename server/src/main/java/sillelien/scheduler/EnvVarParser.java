package sillelien.scheduler;

import com.xeiam.sundial.SundialJobScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class EnvVarParser {

    public static final String NUMERIZED_SCHEDULE = "^(\\w*)_CRON_SCHEDULE_([0-9]+)$";
    public static final String CRON_PATTERN = "^(\\S+\\s+\\S+\\s+\\S+\\s+\\S+\\s+\\S)+\\s+(.*)$";

    public static final Pattern COMPILED_NUM_SCHED = Pattern.compile(NUMERIZED_SCHEDULE);
    public static final Pattern COMPILED_CRON = Pattern.compile(CRON_PATTERN);

    public static void parse() {
        Map<String, String> env = System.getenv();
        for (String key : env.keySet()) {
            String value = env.get(key);
            Matcher keyMatcher = COMPILED_NUM_SCHED.matcher(key);
            if (keyMatcher.matches()) {
                String service = keyMatcher.group(1);
                String count = keyMatcher.group(2);
                Matcher cronMatcher = COMPILED_CRON.matcher(value);
                if (cronMatcher.matches()) {
                    String action = cronMatcher.group(2);
                    String cron = cronMatcher.group(1);
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("service", service);
                    params.put("count", count);
                    params.put("action", action);
                    params.put("cron", cron);
                    String[] splitCron = cron.split(" ");
                    String dayOfWeek = splitCron[4];
                    if (dayOfWeek.equals("*")) {
                        dayOfWeek = "?";
                    }
                    String cronExpression = "0 " + splitCron[0] + " " + splitCron[1] + " " + splitCron[2] + " " + splitCron[3] + " " + dayOfWeek;
                    System.out.println(cron + " " + action);
                    params.put("cronUsed", cronExpression);
                    SundialJobScheduler.addJob(key, TutumExec.class.getCanonicalName(), params);
                    SundialJobScheduler.addCronTrigger(key + "-Trigger", key, cronExpression);
                } else {
                    System.out.println("Invalid CRON expression " + value);
                }
            }
        }
    }
}
