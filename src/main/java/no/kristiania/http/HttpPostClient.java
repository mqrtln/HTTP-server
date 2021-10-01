package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class HttpPostClient {
    private final int statusCode;

    public HttpPostClient(String host, int port, String requestTarget, String contentBody) throws IOException {

        Socket socket  = new Socket(host, port);

        String request = "POST " + requestTarget + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "Connection: close\r\n" +
                "Content-Length: " + contentBody.getBytes().length + "\r\n" +
                "\r\n" +
                contentBody;

        socket.getOutputStream().write(request.getBytes());

        HttpMessage httpMessage = new HttpMessage(socket);

        String [] statusLine = HttpMessage.startLine.split(" ");
        this.statusCode = Integer.parseInt(statusLine[1]);

    }
    public int getStatusCode(){
        return statusCode;
    }

}
