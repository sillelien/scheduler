package sillelien.scheduler;

/**
 * (c) 2015 Cazcade Limited
 *
 * @author neil@cazcade.com
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        for(int i= 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println("Testing 1 2 3");
        }
    }
}
