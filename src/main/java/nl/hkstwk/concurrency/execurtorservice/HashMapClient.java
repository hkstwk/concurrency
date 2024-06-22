package nl.hkstwk.concurrency.execurtorservice;

import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.concurrency.ConcurrentHashMapManager;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class HashMapClient {
    private final ConcurrentHashMapManager manager;

    public HashMapClient(ConcurrentHashMapManager manager) {
        this.manager = manager;

        try (ExecutorService service = Executors.newFixedThreadPool(5)) {
            UUID uuid = UUID.randomUUID();
            for (int i = 0; i < 250; i++) {
                service.execute(() -> {
                    this.manager.addOrUpdateUUID(uuid);
                });
            }
        }
    }
}
