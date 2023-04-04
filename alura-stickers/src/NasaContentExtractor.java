import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class NasaContentExtractor implements IContentExtractor{

    public List<Content> extractContent(String json){
        List<Content> contentList = new ArrayList<>();
        List<Map<String, String>> buffer = new JsonParser().parse(json);
        
        for (Map<String, String> item : buffer) {
            String title = item.get("title");
            String urlImage = item.get("url");

            Content newItem = new Content(title, urlImage);
            contentList.add(newItem);            
        }

        return contentList;
    }
}
