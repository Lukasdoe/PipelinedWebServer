package SimpleServer;

import java.util.HashMap;

public class HTTPRequest {
    HashMap<String, String> headers;
    String path;
    HTTPMethod method;

    public HTTPRequest() {
        headers = new HashMap<>();
    }
}
