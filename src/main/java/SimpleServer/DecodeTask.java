package SimpleServer;

import java.net.Socket;
import java.util.List;
import java.util.Optional;

public class DecodeTask extends Task{
    List<String> requestMessage;

    public DecodeTask(Socket client, List<String> requestMessage) {
        super(client);
        this.requestMessage = requestMessage;
    }

    @Override public Optional<Task> get() {
        try {
            HTTPRequest request = new HTTPRequest();
            String[] head = requestMessage.get(0).split(" ");
            request.method = HTTPMethod.valueOf(head[0]);
            request.path = head[1];

            for (int i = 1; i < requestMessage.size(); i++) {
                if (!requestMessage.get(i).isBlank()) {
                    String[] headerValue = requestMessage.get(i).split(": ");
                    request.headers.put(headerValue[0], headerValue[1]);
                }
            }
            return Optional.of(new TestResponseGenerator(client));
        } catch (Exception e) {
            e.printStackTrace();
        };
        return Optional.empty();
    }
}
