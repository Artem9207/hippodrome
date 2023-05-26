import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void mainExecutionTimeDoesNotExceed22Seconds() throws InterruptedException {
        Thread mainThread = new Thread(() -> {
            try {
                Main.main(new String[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        mainThread.start();
        mainThread.join();
    }
}