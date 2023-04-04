import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageGenerator {
    
    public void generate(InputStream inputStream, String fileName, String text) throws Exception{
        // InputStream fileInputStream = new FileInputStream(new File("input\\filme.jpg");
        // InputStream urlInputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();

        //Read image from a stream
        BufferedImage original = ImageIO.read(inputStream);

        //Create new image larger than the original
        BufferedImage newImage = new BufferedImage(original.getWidth(), original.getHeight() + 100, BufferedImage.TRANSLUCENT);
        Graphics2D newImageGraphics = newImage.createGraphics();
        newImageGraphics.drawImage(original, 0, 0, null);

        //Write something more into the image
        newImageGraphics.setFont(new Font("Impact", Font.BOLD, 64));
        newImageGraphics.drawString(
            text, 
            (newImage.getWidth() / 2) - (newImageGraphics.getFontMetrics().stringWidth(text)/ 2), 
            newImage.getHeight() - (newImageGraphics.getFontMetrics().getAscent() / 2)
        );

        //Write the buffered image to a file
        File file = new File("output");
        if (!file.exists()) {
            file.mkdirs();
        }
        ImageIO.write(newImage, "png", new File("output\\" + fileName));
    }
}
