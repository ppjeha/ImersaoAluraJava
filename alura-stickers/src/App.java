import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {    
        // String urlImdb = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // //Fazer o get do arquivo atraves de uma conexao HTTP
        // String data = new AppHttpClient().getData(urlImdb);

        // String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        // //Fazer o get do arquivo atraves de uma conexao HTTP
        // String data = new AppHttpClient().getData(urlNasa);

        String urlMyApi = "http://localhost:8080/linguagens";
        String data = new AppHttpClient().getData(urlMyApi);

        //Parse dos dados do JSON (titulo, poster, rating)
        IContentExtractor extractor = new ImdbContentExtractor();
        List<Content> contentList = extractor.extractContent(data);

        //Exibir e manipular os dados
        ImageGenerator imgGenerator = new ImageGenerator();
        for (Content item : contentList) {
            InputStream is = new URL(item.getUrlImage()).openStream();
            String title = item.getTitle().replaceAll("[^a-zA-Z0-9 ]", "") + ".png";
            // Double rating = Double.parseDouble(item.get("imDbRating"));
            String text = "TOPZERA";

            // if (rating >= 9.0) {
            //     text=  "TOPZERA";
            // }else{
            //     text = "BEM OK";
            // }
                    
            System.out.println(item.getTitle());
            System.out.println(item.getUrlImage());
            // System.out.println(item.get("imDbRating"));

            imgGenerator.generate(is, title, text);
        }

    }
}
