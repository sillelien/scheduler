package sillelien.scheduler;

import com.google.common.base.CaseFormat;
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
        if(!action.string()) {
            throw new JobParseException("Could not parse the action section");
        }
        var actionType = job.$("action").$("type").$default($("tutum_exec"));

        if (job.$has("cron").isTrue()) {
            if (SundialJobScheduler.getAllJobNames().contains(jobName)) {
                System.out.println("Removing job " + jobName + " prior to re-adding.");
                SundialJobScheduler.removeJob(jobName);
            }

            String actionClass = "sillelien.scheduler.tasks." + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, actionType.toString());
            try {
                Class.forName(actionClass);
            } catch (ClassNotFoundException e) {
                throw new JobParseException("Unrecognized action type: "+actionType);

            }
            SundialJobScheduler.addJob(jobName, actionClass, job.toMap());
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
