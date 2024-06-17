package nl.hkstwk.concurrency.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PollResults {
    private static int counter = 0;

    public PollResults() throws InterruptedException {
        new Thread(() -> {
            log.info("new thread...");
            for (int i = 0; i <5000 ; i++) {
                PollResults.counter++;
            }
        }).start();

        while(PollResults.counter < 100){
            log.info("not reached 100 yet...");
            Thread.sleep(1000);
        }
        log.info("reached 100!");
    }
}
