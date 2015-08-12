package sillelien.scheduler.tasks;

import com.xeiam.sundial.Job;
import com.xeiam.sundial.exceptions.JobInterruptException;
import com.sillelien.dollar.api.var;
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
        TutumService service = api.getServiceByName(String.valueOf(getJobContext().get("service")).trim());
        String containerUrl = service.containers().get(0);
        TutumContainer container = api.getContainer(containerUrl);
        TutumExecResponse response = api.exec(container, String.valueOf(getJobContext().get("command")).trim());
        System.out.println(response);
    }
}