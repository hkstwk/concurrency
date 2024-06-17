package nl.hkstwk.concurrency.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadInventoryThread extends Thread {
    public void run() {
        super.run();
        log.info("Printing inventory ...");
    }
}
