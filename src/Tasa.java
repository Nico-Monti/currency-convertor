import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class Tasa {
    public double consultarTasa(String monedaA, String monedaB) throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String apiKey = props.getProperty("API_KEY");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/pair/"+monedaA+"/"+monedaB);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response = null;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            Moneda moneda = gson.fromJson(response.body(), Moneda.class);
            return moneda.conversion_rate();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error: "+ e.getMessage());
        }
    }
}
