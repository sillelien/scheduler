package sillelien.scheduler;

import sillelien.scheduler.tasks.DebugTask;
import sillelien.scheduler.tasks.TutumExec;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hello@neilellis.me
 */
public class TaskFactory {

    private static Map<String,Class> map= new HashMap<>();
    static {
        addTask("debug", DebugTask.class);
        addTask("tutum_exec", TutumExec.class);
    }


    private static void addTask(String name, Class clazz) {
        map.put(name, clazz);
    }

    public static Class task(String name) {
        Class clazz = map.get(name);
        if(clazz == null) {
            throw new JobParseException("Unrecognized task type: "+name);
        }
        return clazz;
    }
}
