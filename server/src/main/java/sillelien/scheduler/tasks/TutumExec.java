package sillelien.scheduler.tasks;

import com.xeiam.sundial.Job;
import com.xeiam.sundial.exceptions.JobInterruptException;
import com.sillelien.dollar.api.var;
import sillelien.scheduler.JobParseException;
import sillelien.scheduler.TaskFactory;
import sillelien.tutum.*;

import java.util.List;
import java.util.Map;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class TutumExec extends Job  {
    private static final Tutum api = TutumAPI.instance();


    @Override
    public void doRun() throws JobInterruptException {
        Map<String,var> action = getJobContext().get("action");
        var service = action.get("service");
        if(!service.string()) {
            throw new JobParseException("The action should have a service field and it's value should be a string.");
        }
        TutumService tutumService = api.getServiceByName(String.valueOf(service).trim());
        String containerUrl = tutumService.containers().get(0);
        TutumContainer container = api.getContainer(containerUrl);
        var command = action.get("command");
        if(!command.string()) {
            throw new JobParseException("The action should have a command field and it's value should be a string.");
        }
        TutumExecResponse response = api.exec(container, String.valueOf(command).trim());
        System.out.println(response);
    }
}
