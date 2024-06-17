package nl.hkstwk.concurrency.execurtorservice;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class AddData {

    public AddData() {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Future<Integer> result = service.submit(() -> {
                log.info("Adding two numbers...");
                return 30 + 11;
            });
            log.info("ExecutorService AddData {}", result.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
