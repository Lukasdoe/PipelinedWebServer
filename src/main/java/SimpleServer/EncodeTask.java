package SimpleServer;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.Socket;
import java.util.Optional;

public class EncodeTask extends Task{
    HTTPResponse response;

    public EncodeTask(Socket client, HTTPResponse response) {
        super(client);
        this.response = response;
    }

    @Override public Optional<Task> get() {
        ArrayList<String> responseStrings = new ArrayList<>();
        responseStrings.add("HTTP/1.1 200 OK");
        responseStrings.add("date: " + Date.from(Instant.now()).toString());
        response.headers.forEach((key, value) -> responseStrings.add("" + key + ": " + value));
        responseStrings.add("");
        responseStrings.add(response.body);
        responseStrings.add("");
        return Optional.of(new ClientWriteTask(client, responseStrings));
    }
}
