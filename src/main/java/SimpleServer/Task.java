package SimpleServer;

import javax.swing.text.html.Option;
import java.net.Socket;
import java.util.Optional;
import java.util.function.Supplier;

public abstract class Task implements Supplier<Optional<Task>> {
    Socket client;

    public Task(Socket client) {
        this.client = client;
    }

    @Override public Optional<Task> get() {
        return Optional.empty();
    }
}
