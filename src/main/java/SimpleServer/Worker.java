package SimpleServer;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;

public class Worker extends Thread {
    private LinkedBlockingQueue<Task> taskQueue;

    public Worker(LinkedBlockingQueue<Task> taskQueue) {
        super();
        this.taskQueue = taskQueue;
    }

    @Override public void run() {
        while (!isInterrupted()) {
            try {
                Task nextTask = taskQueue.take();
                if (nextTask.client.isClosed()) {
                    continue;
                }
                Optional<Task> newTask = nextTask.get();
                if (newTask.isPresent()) {
                    taskQueue.offer(newTask.get());
                } else {
                    System.out.println("Disconnected client " + nextTask.client + ". Served by worker " + getId());
                    nextTask.client.close();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
