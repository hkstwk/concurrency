package nl.hkstwk.concurrency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Component
public class ConcurrentHashMapManager {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ValueObject {
        private AtomicInteger count;
        private LocalDateTime timestamp;
    }

    private final ConcurrentHashMap<UUID, ValueObject> map = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();

    public void addOrUpdateUUID(UUID uuid) {
//        lock.lock();
        try {
            map.compute(uuid, (key, existingValue) -> {
                if (existingValue == null) {
                    ValueObject newValue = new ValueObject(new AtomicInteger(1), LocalDateTime.now());
                    logMapEntry(uuid, newValue);
                    return newValue;
                } else {
                    existingValue.getCount().incrementAndGet();
                    existingValue.setTimestamp(LocalDateTime.now());
                    logMapEntry(uuid, existingValue);
                    return existingValue;
                }
            });
        } finally {
//            lock.unlock();
        }
    }

    private static void logMapEntry(UUID uuid, ValueObject newValue) {
        log.info("UUID: {}, Count: {}, Timestamp: {}", uuid, newValue.getCount(), newValue.getTimestamp());
    }

    public ValueObject getValue(UUID uuid) {
        return map.get(uuid);
    }
}

