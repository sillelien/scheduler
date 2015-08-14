package sillelien.scheduler.tasks;

import com.sillelien.dollar.api.var;
import com.xeiam.sundial.Job;
import com.xeiam.sundial.exceptions.JobInterruptException;
import sillelien.scheduler.JobParseException;
import sillelien.scheduler.TaskFactory;
import sillelien.tutum.*;

import java.util.Map;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class DebugTask extends Job  {

    @Override
    public void doRun() throws JobInterruptException {
        Map<String,var> action = getJobContext().get("action");
        System.out.println(action);
    }
}
