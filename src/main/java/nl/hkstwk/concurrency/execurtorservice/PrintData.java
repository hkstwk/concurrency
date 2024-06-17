package nl.hkstwk.concurrency.execurtorservice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class PrintData {
    ExecutorService executorService = null;

    public PrintData() {
        try {
            executorService = Executors.newCachedThreadPool();

            log.info("begin (executorService) ...");
            executorService.execute(() -> log.info("Print inventory..."));
            executorService.execute(() -> {
                for (int i = 0; i < 3; i++) {
                    log.info("printing record {}", i);
                }
            });
            executorService.execute(() -> log.info("Print inventory..."));
            log.info("... end (executorService)");
        } finally {
            if (executorService != null) executorService.shutdown();
        }
    }


}
