package conexion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import clases.Clientes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ApiConsumer {

    private static final String API_ENDPOINT_DNI = "https://dniruc.apisperu.com/api/v1/dni/";
    private static final String API_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImRyYWdvb3ZvLjA4MDdAZ21haWwuY29tIn0.Q-t58G-oWuNHrHb4f8l1RZ5jQ9IIX7M02SqfEwUprQA";

    private HttpClient httpClient;
    private Gson gson;

    public ApiConsumer() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }
    public Optional<Clientes> obtenerClientePorDNI(String dni) {
        String uri = API_ENDPOINT_DNI + dni + "?token=" + API_TOKEN;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de la API (DNI): " + response.body()); // Depuración

            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

                if (jsonObject.has("dni")) {
                    Clientes cliente = gson.fromJson(jsonObject, Clientes.class);
                    return Optional.of(cliente);
                } else {
                    System.out.println("El JSON de respuesta no contiene los campos esperados.");
                    return Optional.empty();
                }
            } else {
                System.out.println("Error al obtener datos de la API: " + response.statusCode());
                return Optional.empty();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
