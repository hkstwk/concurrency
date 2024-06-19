package nl.hkstwk.concurrency;

import lombok.extern.slf4j.Slf4j;
import nl.hkstwk.concurrency.execurtorservice.AddData;
import nl.hkstwk.concurrency.execurtorservice.ScheduledTask;
import nl.hkstwk.concurrency.execurtorservice.SheepManager;
import nl.hkstwk.concurrency.runnable.PollResults;
import nl.hkstwk.concurrency.runnable.PrintData;
import nl.hkstwk.concurrency.runnable.ReadInventoryThread;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class StartUpRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
//        doRunnable();
//        doPolling();
//        doPrintDataExecutorService();
//        doPollResultsExecutorService();
//        doAddDataExecutorService();
//        doScheduledTasks();
        new SheepManager();
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

    private static void doPrintDataExecutorService(){
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
