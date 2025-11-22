import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Tasa {
    public static double consultarTasa(String monedaA, String monedaB){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/20072a6486556d6af4147986/pair/"+monedaA+"/"+monedaB);

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
