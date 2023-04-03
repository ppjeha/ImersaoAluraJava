import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        //Fazer o get do arquivo atraves de uma conexao HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        //Parse dos dados do JSON (titulo, poster, rating)
        List<Map<String, String>> listaDeFilmes = new JsonParser().parse(response.body());

        //Exibir e manipular os dados
        ImageGenerator imgGenerator = new ImageGenerator();
        for (Map<String,String> filme : listaDeFilmes) {
            InputStream is = new URL(filme.get("image")).openStream();
            String title = filme.get("title").replaceAll("[^a-zA-Z0-9 ]", "") + ".png";
            Double rating = Double.parseDouble(filme.get("imDbRating"));
            String text;

            if (rating >= 9.0) {
                text=  "TOPZERA";
            }else{
                text = "BEM OK";
            }
                    
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));

            imgGenerator.generate(is, title, text);
        }

    }
}
