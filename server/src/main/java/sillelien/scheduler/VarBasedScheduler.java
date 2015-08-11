package sillelien.scheduler;

import com.sillelien.dollar.api.var;
import com.xeiam.sundial.SundialJobScheduler;

import static com.sillelien.dollar.api.DollarStatic.$;

/**
 * @author hello@neilellis.me
 */
public class VarBasedScheduler {
    private final String scheduleName;
    private var schedule;

    public VarBasedScheduler(var schedule) {
        this.schedule = schedule.getPairValue();
        this.scheduleName = schedule.getPairKey().toString();
    }

    public void start() {
        var service = schedule.$("service");
        var action = schedule.$("action");
        var actionType = schedule.$("action").$("type");
        if (schedule.$has("cron").isTrue()) {
            SundialJobScheduler.addJob(scheduleName, "sillelien.scheduler.tasks."+actionType.$default($("TutumExec")), action.toMap());
            SundialJobScheduler.addCronTrigger(scheduleName + "-Trigger", scheduleName, schedule.$("cron").toString());
        }
    }
}
