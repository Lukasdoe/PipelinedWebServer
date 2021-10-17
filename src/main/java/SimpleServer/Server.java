package SimpleServer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private final int port;
    private final Pipeline pipeline;

    public Server(int port) {
        this.port = port;
        this.pipeline = new Pipeline();
    }

    @Override public void run() {
        pipeline.increaseWorkerCount(10);

        try (ServerSocket socket = new ServerSocket(port)) {
            while (!socket.isClosed()) {
                Socket client = socket.accept();
                System.out.println("Connected client " + client);
                pipeline.addTask(new ClientReadTask(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
