package SimpleServer;

import java.util.HashMap;

public class HTTPResponse {
    HashMap<String, String> headers;
    String body;

    public HTTPResponse() {
        headers = new HashMap<>();
    }
}
