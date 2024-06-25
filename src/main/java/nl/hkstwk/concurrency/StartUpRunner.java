package nl.hkstwk.concurrency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.concurrency.execurtorservice.AddData;
import nl.hkstwk.concurrency.execurtorservice.HashMapClient;
import nl.hkstwk.concurrency.execurtorservice.ScheduledTask;
import nl.hkstwk.concurrency.execurtorservice.SheepManager;
import nl.hkstwk.concurrency.runnable.PollResults;
import nl.hkstwk.concurrency.runnable.PrintData;
import nl.hkstwk.concurrency.runnable.ReadInventoryThread;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartUpRunner implements CommandLineRunner {

    private final ConcurrentHashMapManager manager;

    @Value("${concurrency.execute.runnable}")
    private boolean executeDoRunnable;

    @Value("${concurrency.execute.do-scheduled-tasks}")
    private boolean executeDoScheduledTasks;

    @Value("${concurrency.execute.do-polling}")
    private boolean executeDoPolling;

    @Value("${concurrency.execute.do-printdata-executor-service}")
    private boolean executeDoPrintDataES;

    @Value("${concurrency.execute.do-pollresults-executor-service}")
    private boolean executeDoPollResultsES;

    @Value("${concurrency.execute.do-adddata-executor-service}")
    private boolean executeDoAddDataES;

    @Value("${concurrency.execute.do-sheep-manager}")
    private boolean executeDoSheepManager;

    @Value("${concurrency.execute.do-hashmap-client}")
    private boolean executeDoHashmapClient;

    @Override
    public void run(String... args) throws Exception {
        log.info("cores: {}", Runtime.getRuntime().availableProcessors());
        if (executeDoRunnable) doRunnable();
        if (executeDoScheduledTasks) doScheduledTasks();
        if (executeDoPolling) doPolling();
        if (executeDoPrintDataES) doPrintDataExecutorService();
        if (executeDoPollResultsES) doPollResultsExecutorService();
        if (executeDoAddDataES) doAddDataExecutorService();
        if (executeDoSheepManager) new SheepManager();
        if (executeDoHashmapClient) new HashMapClient(manager);
    }

    private static void doPolling() throws InterruptedException {
        log.info("begin polling for results without sleep");
        new PollResults();
        log.info("end polling for results without sleep");
    }

    private static void doRunnable() {
        log.info("begin...");
        (new ReadInventoryThread()).start();
        (new Thread(new PrintData())).start();
        (new ReadInventoryThread()).start();
        log.info("...end");
    }

    private static void doPrintDataExecutorService() {
        new nl.hkstwk.concurrency.execurtorservice.PrintData();
    }

    private static void doPollResultsExecutorService() throws ExecutionException, InterruptedException {
        new nl.hkstwk.concurrency.execurtorservice.PollResults();
    }

    private void doAddDataExecutorService() {
        new AddData();
    }

    private void doScheduledTasks() throws ExecutionException, InterruptedException {
        new ScheduledTask();
    }
}
