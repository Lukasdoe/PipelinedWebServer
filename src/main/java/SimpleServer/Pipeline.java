package SimpleServer;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class Pipeline {
    private final LinkedBlockingQueue<Task> taskQueue;
    private final LinkedList<Worker> workerList;

    public Pipeline() {
        taskQueue = new LinkedBlockingQueue<>();
        workerList = new LinkedList<>();
    }

    public void addTask(Task t) {
        taskQueue.offer(t);
    }

    public void increaseWorkerCount(int n) {
        setWorkerCount(workerList.size() + n);
    }

    public void setWorkerCount(int n) {
        while (workerList.size() != n) {
            if (workerList.size() > n) {
                killWorker();
            } else {
                spawnWorker();
            }
        }
    }

    public int getWorkerCount() {
        return workerList.size();
    }

    public void reduceWorkerCount(int n) {
        setWorkerCount(Math.max(workerList.size() - n, 0));
    }

    private void spawnWorker() {
        workerList.push(new Worker(taskQueue));
        System.out.println("Spawning new worker " + workerList.peek().getId());
        workerList.peek().start();
    }

    private void killWorker() {
        Worker removedWorker = workerList.pop();
        System.out.println("Stopped worker " + removedWorker.getId());
        removedWorker.interrupt();
    }
}
