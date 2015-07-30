package sillelien.scheduler;

import com.xeiam.sundial.exceptions.JobInterruptException;

import java.util.Map;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class TutumExec extends com.xeiam.sundial.Job  {
    @Override
    public void doRun() throws JobInterruptException {
        System.out.printf("Running");
        try {
            Map<String, Object> map = this.getJobContext().map;
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
