package nl.hkstwk.concurrency.runnable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintData implements Runnable {

    @Override
    public void run() {
        for (int i=0; i<3; i++){
            log.info("Printing record {}", i*2);
        }
    }
}
