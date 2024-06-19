package nl.hkstwk.concurrency.execurtorservice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ScheduledTask {

    private static AtomicInteger counter = new AtomicInteger(0);
    private ScheduledExecutorService service;

    public ScheduledTask() throws InterruptedException, ExecutionException {
        try {
            log.info("Available processors: {}", Runtime.getRuntime().availableProcessors());

            service = Executors.newScheduledThreadPool(10);

            Runnable task1 = () -> log.info("Executing runnable task");
            Callable<Double> task2 = () -> {
                log.info("Executing callable task");
                return Math.pow(9,2);
            };

            service.scheduleAtFixedRate((() -> log.info("{} ....", counter.getAndIncrement())), 0, 1, TimeUnit.SECONDS);
            service.schedule(task1, 6, TimeUnit.SECONDS);
            Future<Double> result2 = service.schedule(task2, 3, TimeUnit.SECONDS);

            Double power = result2.get(10, TimeUnit.SECONDS);
            log.info("Power : {}", power);
        } catch (TimeoutException e) {
            log.warn("Task timed out");
        } finally {
            log.info("Shutting down");
            service.shutdown();
        }

        if (service != null) {
            service.awaitTermination(1, TimeUnit.MINUTES);
            if (service.isTerminated()) {
                log.info("All done. Service terminated");
            } else {
                log.info("At least one task still running, service can't be terminated");
            }
        }
    }
}
