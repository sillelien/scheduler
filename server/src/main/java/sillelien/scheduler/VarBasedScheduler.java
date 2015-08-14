package sillelien.scheduler;

import com.sillelien.dollar.api.var;
import com.xeiam.sundial.SundialJobScheduler;

import java.text.ParseException;

import static com.sillelien.dollar.api.DollarStatic.$;

/**
 * @author hello@neilellis.me
 */
public class VarBasedScheduler {
    private final String jobName;
    private var job;

    public VarBasedScheduler(var job) {
        this.job = job.getPairValue();
        this.jobName = job.getPairKey().toString();
    }

    public void start() {
        var action = job.$("action");
        if(!action.map()) {
            throw new JobParseException("Could not parse the action section");
        }
        var actionType = job.$("action").$("type").$default($("tutum_exec"));

        if (job.$has("cron").isTrue()) {
            if (SundialJobScheduler.getAllJobNames().contains(jobName)) {
                System.out.println("Removing job " + jobName + " prior to re-adding.");
                SundialJobScheduler.removeJob(jobName);
            }


            SundialJobScheduler.addJob(jobName, TaskFactory.task(actionType.toString()).getName(), job.toMap());
            String cronString = job.$("cron").toString();
            String triggerName = jobName + "-Trigger";
            if (SundialJobScheduler.getAllJobsAndTriggers().containsKey(triggerName)) {
                System.out.println("Removing trigger " + triggerName + " prior to re-adding.");
                SundialJobScheduler.removeTrigger(triggerName);
            }
            SundialJobScheduler.addCronTrigger(triggerName, jobName, CronUtil.convertCron(cronString));
        }else {
            throw new JobParseException("Sorry, only cron scheduled jobs supported at the moment.");
        }
    }
}
