package no.kristiania.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {

    @Test
    void shouldReturn404ForUnknownRequestTarget() throws IOException {
        HttpServer server = new HttpServer(2993);
        HttpClient client = new HttpClient("localhost", 2993, "/non-existing");
        assertEquals(404, client.getStatusCode());

    }


}