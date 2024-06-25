package nl.hkstwk.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicIntegerTest {
    @Test
    void testAtomicInteger() {
        AtomicInteger ai1 = new AtomicInteger(1);
        int ai = ai1.incrementAndGet();
        log.info("ai = {}", ai);
        log.info("ai1 incrementAndGet = {}", ai1);
        AtomicInteger ai2 = new AtomicInteger(1);
        ai = ai2.getAndIncrement();
        log.info("ai = {}", ai);
        log.info("ai2 getAndincrement = {}", ai2);
    }
}
