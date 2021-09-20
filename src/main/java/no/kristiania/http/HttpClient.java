package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {
    private final int statusCode;
    private final Map<String, String> headerFields = new HashMap<>();

    public HttpClient(String host, int port, String requestTarget) throws IOException {
        Socket socket  = new Socket(host, port);

        String request = "GET " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "Connection: close\r\n" +
                "\r\n";

        socket.getOutputStream().write(request.getBytes());

        String [] statusLine = readLine(socket).split(" ");
        this.statusCode = Integer.parseInt(statusLine[1]);

        String headerLine;
        while (!(headerLine = readLine(socket)).isBlank()){
            int colonPos = headerLine.indexOf(':');
            String headerField = headerLine.substring(0, colonPos);
            String headerValue = headerLine.substring(colonPos+1).trim();
            headerFields.put(headerField, headerValue);
        }
    }

    private String readLine(Socket socket) throws IOException {
        StringBuilder buffer = new StringBuilder();
        int c;
        while ((c = socket.getInputStream().read()) != '\r'){
            buffer.append((char)c);
        }
        socket.getInputStream().read();
        return buffer.toString();
    }

    public int getStatusCode(){
        return statusCode;
    }

    public String getHeader(String headerName) {
        return headerFields.get(headerName);
    }
}
