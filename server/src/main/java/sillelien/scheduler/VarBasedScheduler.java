package sillelien.scheduler;

import com.sillelien.dollar.api.var;
import com.xeiam.sundial.SundialJobScheduler;

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
        String serviceString = job.$("service").toString().trim();
        var action = job.$("action");
        var actionType = job.$("action").$("type");
        if (job.$has("cron").isTrue()) {
            if(SundialJobScheduler.getAllJobNames().contains(jobName)) {
                System.out.println("Removing job "+ jobName +" prior to re-adding.");
                SundialJobScheduler.removeJob(jobName);
            }
            SundialJobScheduler.addJob(jobName, "sillelien.scheduler.tasks."+actionType.$default($("TutumExec")), job.toMap());
            String cronString = job.$("cron").toString();
            String triggerName = jobName + "-Trigger";
            if(SundialJobScheduler.getAllJobsAndTriggers().containsKey(triggerName)) {
                System.out.println("Removing trigger "+triggerName+" prior to re-adding.");
                SundialJobScheduler.removeTrigger(triggerName);
            }
            SundialJobScheduler.addCronTrigger(triggerName, jobName, CronUtil.convertCron(cronString));
        }
    }
}
