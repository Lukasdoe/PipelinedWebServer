package SimpleServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class ClientWriteTask extends Task{

    List<String> message;

    public ClientWriteTask(Socket client, List<String> message) {
        super(client);
        this.message = message;
    }

    @Override public Optional<Task> get() {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            message.forEach(out::println);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
