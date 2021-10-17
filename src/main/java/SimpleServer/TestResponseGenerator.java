package SimpleServer;

import java.net.Socket;
import java.util.Optional;

public class TestResponseGenerator extends Task {

    public TestResponseGenerator(Socket client) {
        super(client);
    }

    @Override public Optional<Task> get() {
        HTTPResponse response = new HTTPResponse();
        response.body = "Hello World.";
        return Optional.of(new EncodeTask(client, response));
    }
}
