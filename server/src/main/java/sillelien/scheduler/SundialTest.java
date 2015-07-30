package sillelien.scheduler;

import com.xeiam.sundial.exceptions.JobInterruptException;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class SundialTest extends com.xeiam.sundial.Job  {
    @Override
    public void doRun() throws JobInterruptException {
        System.out.println("Test Job");
    }
}
