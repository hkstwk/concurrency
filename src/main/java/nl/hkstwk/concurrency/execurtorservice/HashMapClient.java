package nl.hkstwk.concurrency.execurtorservice;

import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.concurrency.ConcurrentHashMapManager;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class HashMapClient {
    private final ConcurrentHashMapManager manager;

    public HashMapClient(ConcurrentHashMapManager manager) {
        this.manager = manager;

        try (ExecutorService service = Executors.newFixedThreadPool(5)) {
            UUID uuid = UUID.randomUUID();
            for (int i = 0; i < 5; i++) {
                Future<?> result1 = service.submit(() -> {
                    this.manager.addOrUpdateUUID(uuid);
                });
            }
        }
    }
}
