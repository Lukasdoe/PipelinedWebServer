package SimpleServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

public class ClientReadTask extends Task{
    public ClientReadTask(Socket client) {
        super(client);
    }

    @Override public Optional<Task> get() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
            ArrayList<String> input = new ArrayList<>();
            String lastInput = in.readLine();
            while (lastInput != null && !lastInput.equals("")) {
                input.add(lastInput);
                lastInput = in.readLine();
            }
            if (input.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(new DecodeTask(client, input));
        } catch (IOException ignored) {
            return Optional.empty();
        }
    }
}
