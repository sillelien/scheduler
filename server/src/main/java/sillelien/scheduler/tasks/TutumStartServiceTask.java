package sillelien.scheduler.tasks;

import com.sillelien.dollar.api.var;
import com.xeiam.sundial.Job;
import com.xeiam.sundial.exceptions.JobInterruptException;
import sillelien.scheduler.JobParseException;
import sillelien.tutum.*;

import java.util.Map;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class TutumStartServiceTask extends Job  {
    private static final Tutum api = TutumAPI.instance();


    @Override
    public void doRun() throws JobInterruptException {
        Map<String,var> action = getJobContext().get("action");
        var service = action.get("service");
        if(!service.string()) {
            throw new JobParseException("The action should have a service field and it's value should be a string.");
        }
        TutumService tutumService = api.getServiceByName(String.valueOf(service).trim());
        api.startService(tutumService.uuid());
    }
}
