package nl.hkstwk.concurrency.execurtorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager {

    private AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReportSheepCount() {
        synchronized (this) {
            System.out.print(sheepCount.incrementAndGet() + " ");
        }
    }

    private synchronized void incrementAndReportSheepCountSyncModifier() {
        System.out.print(sheepCount.getAndIncrement() + " ");
    }

    public SheepManager() throws InterruptedException {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);

            for (int i = 0; i < 50; i++) {
//                service.submit(() -> incrementAndReportSheepCount());
                service.submit(() -> incrementAndReportSheepCountSyncModifier());

            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
