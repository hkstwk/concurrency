package nl.hkstwk.concurrency.execurtorservice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class PollResults {
    private static int counter = 0;

    public PollResults() throws InterruptedException, ExecutionException {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            log.info("ExecutorService PollResults started");
            Future<?> result = service.submit(() -> {
                log.info("counter start");
                for (int i = 0; i < 5000; i++) {
                    PollResults.counter++;
                    if (PollResults.counter % 100 == 0) log.info("counter = {}", PollResults.counter);
                }
                log.info("counter finish");
            });

            result.get(10, TimeUnit.SECONDS);
            log.info("ExecutorService PollResults finished in time");

        } catch (TimeoutException e) {
            log.info("not reached in time {}", e.getMessage());
        }
    }
}
